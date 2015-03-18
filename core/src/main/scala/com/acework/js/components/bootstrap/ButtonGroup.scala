package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{Any => JAny, UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */


object ButtonGroup extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  case class Props(id: UndefOr[String] = undefined,
                   vertical: UndefOr[Boolean] = undefined, justified: UndefOr[Boolean] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`btn-group`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)
  }

  override val component = ReactComponentB[Props]("ButtonGroup")
    .render { (P, C) =>
    val classes = P.bsClassSet ++ Map(
      "btn-group" -> !P.vertical.getOrElse(false),
      "btn-group-vertical" -> P.vertical.getOrElse(false),
      "btn-group-justifed" -> P.justified.getOrElse(false))

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(C)
  }.build

  override def defaultProps = Props()
}
