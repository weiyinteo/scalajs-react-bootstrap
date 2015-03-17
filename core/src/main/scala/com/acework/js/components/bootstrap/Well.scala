package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Well extends BootstrapMixin {

  case class Props(bsClass: UndefOr[Classes.Value] = Classes.well,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,

                   addClasses: String = "")
    extends BaseProps

  type PROPS = Props

  val Well = ReactComponentB[Props]("Well")
    .render { (P, C) =>

    // TODO spread props
    <.span(^.classSet1M(P.addClasses, getBsClassSet(P)))(C)

  }.build

  def apply(props: Props, children: ReactNode) = Well(props, children)

  def apply(children: ReactNode) = Well(Props(), children)
}
