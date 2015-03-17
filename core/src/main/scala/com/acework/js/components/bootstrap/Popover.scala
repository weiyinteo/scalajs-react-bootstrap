package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Popover {

  case class Props(title: UndefOr[String] = undefined,
                   placement: String = "right",
                   positionLeft: UndefOr[Int] = undefined,
                   positionTop: UndefOr[Int] = undefined,
                   arrowOffsetLeft: UndefOr[Int] = undefined,
                   arrowOffsetTop: UndefOr[Int] = undefined,
                   addClasses: String = "")

  val Popover = ReactComponentB[Props]("Popover")
    .render { (P, C) =>
    def renderTitle() = {
      <.h3(^.className := "popover-title")(P.title)
    }

    val classes = Map("popover" -> true,
      P.placement -> true,
      "in" -> (P.positionLeft.isDefined || P.positionTop.isDefined)
    )

    <.div(^.classSet1M(P.addClasses, classes), ^.left := P.positionLeft, ^.top := P.positionTop,
      ^.display := "block",
      <.div(^.className := "arrow", ^.left := P.arrowOffsetLeft, ^.top := P.arrowOffsetTop),
      if (P.title.isDefined) renderTitle() else EmptyTag,
      <.div(^.className := "popover-content")(C)
    )
  }.build

  def apply(props: Props, children: ReactNode*) = Popover(props, children)
}
