package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}
import Utils._

/**
 * Created by weiyin on 22/03/15.
 */
object TabbedArea extends BootstrapComponent {
  override type P = TabbedArea
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = TabbedArea()


  case class TabbedArea(id: UndefOr[String] = undefined,
                   activeKey: UndefOr[String] = undefined,
                   animation: Boolean = true,
                   defaultActiveKey: UndefOr[String] = undefined,
                   onSelect: UndefOr[Seq[UndefOr[String]] => Unit] = undefined,
                   bsStyle: UndefOr[Styles.Value] = Styles.tabs,
                   addClasses: String = "") extends MergeableProps[TabbedArea] {

    def merge(t: Map[String, Any]): TabbedArea = implicitly[Mergeable[TabbedArea]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[TabbedArea]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  case class State(activeKey: UndefOr[String] = undefined, previousActiveKey: UndefOr[String] = undefined)

  case class Backend(scope: BackendScope[TabbedArea, State]) {
    var _isChanging = false

    def handlePaneAnimateOutEnd() = {
      scope.modState(_.copy(previousActiveKey = undefined))
    }

    def handleSelect(args: Seq[UndefOr[String]]): Unit = {
      val key = args.head
      if (scope.props.onSelect.isDefined) {
        _isChanging = true
        scope.props.onSelect.get(args)
        _isChanging = false
      } else if (key.get != getActiveKey) {
        scope.setState(State(key, getActiveKey))
      }
    }

    def onComponentWillReceiveProps(nextProps: TabbedArea): Unit = {
      if (nextProps.activeKey.isDefined && nextProps.activeKey.get != scope.props.activeKey.getOrElse(false))
        scope.modState(_.copy(previousActiveKey = scope.props.activeKey))
    }

    def getActiveKey: String = {
      if (scope.props.activeKey.isDefined)
        scope.props.activeKey.get
      else
        scope.state.activeKey.getOrElse("")
    }

    def shouldComponentUpdate(): Boolean = !_isChanging
  }

  override val component = ReactComponentB[TabbedArea]("PanelGroup")
    // FIXME get default from children
    .initialStateP(P => State(P.defaultActiveKey))
    .backend(new Backend(_))
    .render(
      (P, C, S, B) => {
        val activeKey = if (P.activeKey.isDefined) P.activeKey else S.activeKey

        def getChildEventKey(child: ReactNode): UndefOr[String] = {
          val childPropsAny = getChildProps[Any](child)

          childPropsAny match {
            case props: TabPane.TabPane =>
              props.eventKey
            case _ => undefined
          }
        }
        def getChildTab(child: ReactNode): UndefOr[String] = {
          val childPropsAny = getChildProps[Any](child)

          childPropsAny match {
            case props: TabPane.TabPane =>
              props.tab
            case _ => undefined
          }
        }

        def renderTab(child: ReactNode, childTab: ReactNode): TagMod = {
          val eventKey = getChildEventKey(child)
          NavItem.withRef(s"tab$eventKey")(NavItem.NavItem(eventKey = eventKey), childTab)
        }

        def renderPane(child: ReactNode, index: Int) = {

          val eventKey = getChildEventKey(child)

          val keyAndRef = getChildKeyAndRef2(child, index)
          val active = eventKey.getOrElse("NA1") == activeKey.getOrElse("NA2") &&
            (S.previousActiveKey.isEmpty || !P.animation)

          var propsMap = Map[String, Any](
            "active" -> active,
            "animation" -> P.animation
          )

          if (S.previousActiveKey.isDefined && eventKey.getOrElse("NA1") == S.previousActiveKey.get)
            propsMap += ("onAnimateOutEnd" -> B.handlePaneAnimateOutEnd _)

          cloneWithProps(child, keyAndRef, propsMap)
        }

        def renderTabIfSet(child: ReactNode, index: Int) = {
          val tab = getChildTab(child)
          if (tab.isDefined)
            renderTab(child, tab.get)
          else
            EmptyTag
        }

        val nav = Nav.withRef("tabs")(Nav.Nav(activeKey = activeKey,
          addClasses = s"nav-${P.bsStyle.toString}",
          onSelect = (args: Seq[UndefOr[String]]) => B.handleSelect(args)),
          ValidComponentChildren.map(C, renderTabIfSet)
        )

        val panesRef = Ref("panes")
        <.div(
          nav,
          <.div(^.id := P.id, ^.className := "tab-content", ^.ref := panesRef,
            ValidComponentChildren.map(C, renderPane)
          )
        )
      })
    .componentWillReceiveProps((scope, nextProps) => scope.backend.onComponentWillReceiveProps(nextProps))
    .shouldComponentUpdate((scope, _, _) => scope.backend.shouldComponentUpdate())
    .build
}
