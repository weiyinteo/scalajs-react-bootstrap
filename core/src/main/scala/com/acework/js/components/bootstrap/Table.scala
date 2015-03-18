package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._


/**
 * Created by weiyin on 10/03/15.
 */
object Table {

  case class Props(striped: UndefOr[Boolean] = undefined,
                   bordered: UndefOr[Boolean] = undefined,
                   condensed: UndefOr[Boolean] = undefined,
                   hover: UndefOr[Boolean] = undefined,
                   responsive: UndefOr[Boolean] = undefined,
                   addClasses: String = "")

  val Table = ReactComponentB[Props]("Table")
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

  def apply(props: Props, children: ReactNode*) = Table(props, children)

  def apply(children: ReactNode*) = Table(Props(), children)

  def apply() = Table(Props())
}
