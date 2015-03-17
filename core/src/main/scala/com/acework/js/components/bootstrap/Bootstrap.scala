package com.acework.js.components.bootstrap

import com.acework.js.logger._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement

import scala.language.implicitConversions
import scala.scalajs.js

/**
 * Common Bootstrap components for scalajs-react
 */
object BootstrapDisabled {

  trait BootstrapJQuery extends JQuery {
    def modal(action: String): BootstrapJQuery = js.native

    def modal(options: js.Any): BootstrapJQuery = js.native
  }

  implicit def jq2bootstrap(jq: JQuery): BootstrapJQuery = jq.asInstanceOf[BootstrapJQuery]


  object TabbedArea {

    case class Tab(name: String, isActive: Boolean = false)

    object Tabs {

      case class Props(tabs: Seq[Tab], activateTab: (Tab) => Unit)

      val tabs = ReactComponentB[Props]("Tabs")
        .render(P => {
        def renderItem(tab: Tab) = {
          val active = if (tab.isActive) "active" else ""
          <.li(^.className := s"$active", ^.onClick --> P.activateTab(tab),
            <.a(tab.name)
          )
        }
        <.ul(^.className := "nav nav-pills nav-justified")(P.tabs map renderItem)
      }).build

      def apply(props: Props): ReactNode = tabs(props)
    }

    object Panes {

      case class Props(panes: Seq[Tab], addClasses: String = "")

      val panes = ReactComponentB[Props]("Panes")
        .render { (P, C) =>
        def renderPane(tab: Tab) = {
          val active = if (tab.isActive) "active" else ""
          <.div(^.key := tab.name, ^.className := s"tab-pane $active ${P.addClasses}", tab.name)
        }
        <.div(^.className := "tab-content")(P.panes map renderPane)
      }.build

      def apply(props: Props): ReactNode = panes(props)

    }

    case class State(tabs: Seq[Tab] = Seq.empty)

    case class Props(tabs: Seq[Tab], switchTab: () => Unit)

    class Backend(t: BackendScope[Props, State]) {
      def mounted(): Unit = {
        t.modState(s => s.copy(tabs = t.props.tabs))
      }

      def activateTab(selectedTab: Tab): Unit = {

        log.debug(s"click on $selectedTab")
        val newTabs: Seq[Tab] = t.state.tabs.map { tab =>
          tab.copy(isActive = selectedTab.name == tab.name)
        }
        val newState = t.state.copy(tabs = newTabs)
        log.debug(s"new state on $newState")
        t.modState(s => newState)
      }
    }

    val component = ReactComponentB[Props]("TabbedArea")
      .initialState(State())
      .backend(new Backend(_))
      .render((P, S, B) => {
      <.div(
        Tabs(Tabs.Props(P.tabs, B.activateTab)),
        Panes(Panes.Props(P.tabs)
        )
      )
    }
      )
      .componentDidMount(_.backend.mounted())
      .build

    def apply(props: Props) = component(props)
  }





  object TabbedArea2 {

    // Common Bootstrap tab styles
    object TabStyle extends Enumeration {
      val pill, tabs = Value
    }


    case class State(activeKey: String = "", previousActiveKey: Option[String] = None)

    case class Props(activeKey: String = "",
                     animation: Boolean = true, style: TabStyle.Value = TabStyle.tabs, addClasses: String = "")

    val panesRef = Ref[HTMLElement]("panes")
    val component = ReactComponentB[Props]("TabbedArea")
      .initialState(State())
      .render((P, C, S) => {
      val panesRef = Ref[HTMLElement]("panes")
      <.div(
        Nav(Nav.Props(activeKey = P.activeKey, navbar = true), C),
        <.div(^.className := "tab-content", ^.ref := panesRef)(C)
      )
    }
      )

  }

}
