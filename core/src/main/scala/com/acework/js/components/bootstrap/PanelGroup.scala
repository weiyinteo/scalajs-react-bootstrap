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
  override type P = Props
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = Props()

  case class State(activeKey: UndefOr[String])

  case class Props(activeKey: UndefOr[String] = undefined,
                   accordion: UndefOr[Boolean] = undefined,
                   collapsable: UndefOr[Boolean] = undefined,
                   onSelect: UndefOr[(UndefOr[String]) => Unit] = undefined,
                   defaultActiveKey: UndefOr[String] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`panel-group`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)
  }


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

  override val component = ReactComponentB[Props]("PanelGroup")
    .initialStateP(P => State(P.defaultActiveKey))
    .backend(new Backend(_))
    .render {
    (P, C, S, B) =>

      def renderPanel(child: ReactNode, index: Int) = {
        val activeKey = if (P.activeKey.isDefined) P.activeKey else S.activeKey

        val childPropsAny = getChildProps[Any](child)

        val eventKey =
          childPropsAny match {
            case props: Panel.Props =>
              props.eventKey
            case _ => undefined
          }

        val keyAndRef = getChildKeyAndRef2(child, index)
        if (P.accordion.getOrElse(false)) {
          val propsMap = Map(
            "collapsable" -> true,
            "expanded" -> (eventKey.getOrElse("NA1") == activeKey.getOrElse("NA2")),
            "onSelect" -> B.handleSelect _)
          cloneWithProps(child, keyAndRef, propsMap)
        }
        else
          cloneWithProps(child, keyAndRef)
      }

      <.div(^.classSet1M(P.addClasses, P.bsClassSet))(
        ValidComponentChildren.map(C, renderPanel)
      )
  }.build

}
