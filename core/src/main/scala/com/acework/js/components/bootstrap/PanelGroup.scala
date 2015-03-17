package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object PanelGroup extends BootstrapMixin {
  type PROPS = Props

  case class State(activeKey: UndefOr[String])

  case class Props(activeKey: UndefOr[String] = undefined,
                   accordion: UndefOr[Boolean] = undefined,
                   collapsable: UndefOr[Boolean] = undefined,
                   onSelect: UndefOr[(UndefOr[String]) => Unit] = undefined,
                   defaultActiveKey: UndefOr[String] = undefined,
                   key: UndefOr[JsNumberOrString] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`panel-group`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  class Backend($: BackendScope[Props, State]) {
    def children: js.Any = $._props.asInstanceOf[js.Dynamic].children

    var isChanging: Boolean = false

    def handleSelect(key: UndefOr[String]): Unit = {
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
  }

  val PanelGroup = ReactComponentB[Props]("PanelGroup")
    .initialStateP(P => State(P.defaultActiveKey))
    .backend(new Backend(_))
    .render {
    (P, C, S, B) =>

      def renderPanel(child: ReactNode, index: Int) = {
        val activeKey = if(P.activeKey.isDefined) P.activeKey else S.activeKey

        // FIXME how to deal with the case where child is not Panel?
        val el = child.asInstanceOf[ReactDOMElement]
        val childProps: Panel.Props = el.props.asInstanceOf[WrapObj[Panel.Props]]
        var props = if (!childProps.key.isDefined) childProps.copy(key = index) else childProps

        if (!childProps.bsStyle.isDefined && P.bsStyle.isDefined)
          props.copy(bsStyle = P.bsStyle)

        if (P.accordion.isDefined) {
          val eventKey = childProps.eventKey
          val childExpanded = eventKey.isDefined && eventKey.getOrElse(false) == activeKey.getOrElse(false)
          props = props.copy(collapsable = true, expanded = childExpanded, onSelect = B.handleSelect _)
        }
        // FIXME ref
        val newChild = ReactCloneWithProps(child, Map()) //,  Map[String, js.Any]("key" -> props.key))
        val nc = newChild.asInstanceOf[js.Dynamic]
        val ncProps = nc.props
        ncProps.updateDynamic("v")(props.asInstanceOf[js.Any])
        // unfortunately this does not work, as props.children will be lost
        //nc.updateDynamic("props")(WrapObj(props))
        newChild
      }

      <.div(^.classSet1M(P.addClasses, getBsClassSet(P)))(
        ValidComponentChildren.map(C, renderPanel)
      )
  }.build

  def apply(props: Props, children: ReactNode*) = PanelGroup(props, children)
}
