package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */
object Tooltip extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()

  case class Props(placement: UndefOr[Placements.Value] = Placements.right,
                   positionLeft: UndefOr[Int] = undefined,
                   positionTop: UndefOr[Int] = undefined,
                   arrowOffsetLeft: UndefOr[Int] = undefined,
                   arrowOffsetTop: UndefOr[Int] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.panel,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)
  }

  override val component = ReactComponentB[Props]("Tooltip")
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
