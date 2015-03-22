package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object ButtonToolbar extends BootstrapComponent {
  override type P = ButtonToolBar
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = ButtonToolBar()

  case class ButtonToolBar(bsClass: UndefOr[Classes.Value] = Classes.`btn-toolbar`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[ButtonToolBar] {

    def merge(t: Map[String, Any]): ButtonToolBar = implicitly[Mergeable[ButtonToolBar]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[ButtonToolBar]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[ButtonToolBar]("ButtonToolbar")
    .render { (P, C) =>
    // TODO spread props
    <.div(^.role := "toolbar", ^.classSet1M(P.addClasses, P.bsClassSet))(C)
  }.build

}
