package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{Any => JAny, UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */


object ButtonGroup extends BootstrapComponent {
  override type P = ButtonGroup
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps: P = ButtonGroup()

  case class ButtonGroup(id: UndefOr[String] = undefined,
                         vertical: UndefOr[Boolean] = undefined,
                         justified: UndefOr[Boolean] = undefined,
                         bsClass: UndefOr[Classes.Value] = Classes.`btn-group`,
                         bsStyle: UndefOr[Styles.Value] = undefined,
                         bsSize: UndefOr[Sizes.Value] = undefined,
                         addClasses: String = "") extends BsProps with MergeableProps[ButtonGroup] {

    def merge(t: Map[String, Any]): ButtonGroup = implicitly[Mergeable[ButtonGroup]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[ButtonGroup]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[ButtonGroup]("ButtonGroup")
    .render { (P, C) =>
    val classes = P.bsClassSet ++ Map(
      "btn-group" -> !P.vertical.getOrElse(false),
      "btn-group-vertical" -> P.vertical.getOrElse(false),
      "btn-group-justifed" -> P.justified.getOrElse(false))

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(C)
  }.build

}
