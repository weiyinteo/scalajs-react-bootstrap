package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object ButtonGroup extends BootstrapMixin {

  type PROPS = Props

  case class Props(id: UndefOr[String] = undefined,
                   vertical: UndefOr[Boolean] = undefined, justified: UndefOr[Boolean] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`btn-group`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  val ButtonGroup = ReactComponentB[Props]("ButtonGroup")
    .render { (P, C) =>
    var classes = getBsClassSet(P)
    classes += ("btn-group" -> !P.vertical.getOrElse(false))
    classes += ("btn-group-vertical" -> P.vertical.getOrElse(false))
    classes += ("btn-group-justifed" -> P.justified.getOrElse(false))

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(C)
  }.build


  def apply(props: Props, children: ReactNode*) = ButtonGroup(props, children)

  def apply(children: ReactNode*) = ButtonGroup(Props(), children)
}
