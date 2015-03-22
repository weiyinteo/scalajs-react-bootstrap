package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */
object Tooltip extends BootstrapComponent {
  override type P = Tooltip
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Tooltip()

  case class Tooltip(placement: UndefOr[Placements.Value] = Placements.right,
                   positionLeft: UndefOr[Int] = undefined,
                   positionTop: UndefOr[Int] = undefined,
                   arrowOffsetLeft: UndefOr[Int] = undefined,
                   arrowOffsetTop: UndefOr[Int] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.panel,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Tooltip] {

    def merge(t: Map[String, Any]): Tooltip = implicitly[Mergeable[Tooltip]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Tooltip]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Tooltip]("Tooltip")
    .render((P, C) => {
    val classes = Map(P.placement.get.toString -> true,
      "in" -> (P.positionLeft.isDefined || P.positionTop.isDefined)
    )

    <.div(^.classSet1M("tooltip", classes), ^.left := P.positionLeft, ^.top := P.positionTop,
      <.div(^.className := "tooltip-arrow", ^.left := P.arrowOffsetLeft, ^.top := P.arrowOffsetTop),
      <.div(^.className := "tooltip-inner", C)
    )
  })
    .build

}
