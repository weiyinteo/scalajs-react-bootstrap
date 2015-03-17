package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Label extends BootstrapMixin {

  // defaults are based on getDefaultProps
  case class Props(bsClass: UndefOr[Classes.Value] = Classes.label,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  type PROPS = Props

  val Label = ReactComponentB[Props]("Label")
    .render { (P, C) =>
    // TODO props spread
    <.span(^.classSet1M(P.addClasses, getBsClassSet(P)))(C)
  }.build

  def apply(props: Props, children: ReactNode*) = Label(props, children)

  def apply(children: ReactNode*) = Label(Props(), children)
}
