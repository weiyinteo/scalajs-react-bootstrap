package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._


/**
 * Created by weiyin on 10/03/15.
 */
object Table extends BootstrapComponent {
  override type P = Table
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Table()

  case class Table(striped: UndefOr[Boolean] = undefined,
                   bordered: UndefOr[Boolean] = undefined,
                   condensed: UndefOr[Boolean] = undefined,
                   hover: UndefOr[Boolean] = undefined,
                   responsive: UndefOr[Boolean] = undefined,
                   addClasses: String = "") {

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  val component = ReactComponentB[Table]("Table")
    .stateless
    .render { (P, C, _) =>
    val classes = Map(
      "table" -> true,
      "table-striped" -> P.striped.getOrElse(false),
      "table-bordered" -> P.bordered.getOrElse(false),
      "table-condensed" -> P.condensed.getOrElse(false),
      "table-hover" -> P.hover.getOrElse(false)
    )

    val table = <.table(^.classSet1M(P.addClasses, classes))(C)

    if (P.responsive.getOrElse(false))
      <.div(^.className := "table-responsive")(table)
    else
      table
  }.build

}
