package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Popover extends BootstrapComponent {
  override type P = Popover
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Popover()

  case class Popover(title: UndefOr[ReactNode] = undefined,
                   placement: Placements.Value = Placements.right,
                   positionLeft: UndefOr[Int] = undefined,
                   positionTop: UndefOr[Int] = undefined,
                   arrowOffsetLeft: UndefOr[Int] = undefined,
                   arrowOffsetTop: UndefOr[Int] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.btn,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Popover] {

    def merge(t: Map[String, Any]): Popover = implicitly[Mergeable[Popover]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Popover]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Popover]("Popover")
    .render { (P, C) =>
    def renderTitle() = {
      <.h3(^.className := "popover-title", P.title)
    }

    val classes = Map("popover" -> true,
      P.placement.toString -> true,
      "in" -> (P.positionLeft.isDefined || P.positionTop.isDefined)
    )

    <.div(^.classSet1M(P.addClasses, classes), ^.left := P.positionLeft, ^.top := P.positionTop,
      ^.display := "block",
      <.div(^.className := "arrow", ^.left := P.arrowOffsetLeft, ^.top := P.arrowOffsetTop),
      if (P.title.isDefined) renderTitle() else EmptyTag,
      <.div(^.className := "popover-content")(C)
    )
  }.build

}
