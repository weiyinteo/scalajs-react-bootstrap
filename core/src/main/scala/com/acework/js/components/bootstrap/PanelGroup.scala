package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object PanelGroup extends BootstrapComponent {
  override type P = PanelGroup
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = PanelGroup()

  case class State(activeKey: UndefOr[String])

  case class PanelGroup(activeKey: UndefOr[String] = undefined,
                        accordion: UndefOr[Boolean] = undefined,
                        collapsable: UndefOr[Boolean] = undefined,
                        onSelect: UndefOr[(UndefOr[String]) => Unit] = undefined,
                        defaultActiveKey: UndefOr[String] = undefined,
                        bsClass: UndefOr[Classes.Value] = Classes.`panel-group`,
                        bsStyle: UndefOr[Styles.Value] = undefined,
                        bsSize: UndefOr[Sizes.Value] = undefined,
                        addClasses: String = "") extends BsProps with MergeableProps[PanelGroup] {

    def merge(t: Map[String, Any]): PanelGroup = implicitly[Mergeable[PanelGroup]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[PanelGroup]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }


  class Backend($: BackendScope[PanelGroup, State]) {
    def children: js.Any = $.props.asInstanceOf[js.Dynamic].children

    var isChanging: Boolean = false

    def handleSelect(e: ReactEvent, key: UndefOr[String]): Unit = {
      e.preventDefault()

      if ($.props.onSelect.isDefined) {
        isChanging = true
        $.props.onSelect.get(key)
        isChanging = false
      }
      var newKey: UndefOr[String] = key
      if ($.state.activeKey == key)
        newKey = undefined

      $.modState(s => s.copy(activeKey = newKey))
    }

    def shouldComponentUpdate = {
      // defer any updates to this component during the onselect handler
      !isChanging
    }
  }

  override val component = ReactComponentB[PanelGroup]("PanelGroup")
    .initialStateP(P => State(P.defaultActiveKey))
    .backend(new Backend(_))
    .render {
    (P, C, S, B) =>

      def renderPanel(child: ReactNode, index: Int) = {
        val activeKey = if (P.activeKey.isDefined) P.activeKey else S.activeKey

        val childPropsAny = getChildProps[Any](child)

        val (eventKey, bsStyle) =
          childPropsAny match {
            case props: Panel.Panel =>
              (props.eventKey, props.bsStyle)

            case _ => (undefined, undefined)
          }

        var propsMap = Map[String, Any]("bsStyle" -> (if (bsStyle.isDefined) bsStyle else P.bsStyle))

        val keyAndRef = getChildKeyAndRef2(child, index)
        if (P.accordion.getOrElse(false)) {
          propsMap ++= Map(
            "collapsable" -> true,
            "expanded" -> (eventKey.getOrElse("NA1") == activeKey.getOrElse("NA2")),
            "onSelect" -> B.handleSelect _)
        }

        cloneWithProps(child, keyAndRef, propsMap)
      }

      <.div(^.classSet1M(P.addClasses, P.bsClassSet))(
        ValidComponentChildren.map(C, renderPanel)
      )
  }
    .shouldComponentUpdate((scope, _, _) => scope.backend.shouldComponentUpdate)
    .build

}
