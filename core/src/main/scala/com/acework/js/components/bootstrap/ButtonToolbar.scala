package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object ButtonToolbar extends BootstrapMixin {

  type PROPS = Props

  case class Props(bsClass: UndefOr[Classes.Value] = Classes.`btn-toolbar`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  val ButtonToolbar = ReactComponentB[Props]("ButtonToolbar")
    .render { (P, C) =>
    // TODO spread props
    <.div(^.role := "toolbar", ^.classSet1M(P.addClasses, getBsClassSet(P)))(C)
  }.build

  def apply(props: Props, children: ReactNode*) = ButtonToolbar(props, children)

  def apply(children: ReactNode*) = ButtonToolbar(Props(), children)
}
