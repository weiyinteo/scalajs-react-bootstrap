package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object CarouselItem {


  case class Props(index: Int,
                   active: UndefOr[Boolean] = true,
                   animation: UndefOr[Boolean] = true,
                   animateIn: UndefOr[Boolean] = false,
                   animateOut: UndefOr[Boolean] = false,
                   direction: Directions.Value = Directions.next,
                   onAnimationOutEnd: UndefOr[(Int) => Unit] = undefined,
                   caption: UndefOr[String] = undefined,
                   addClasses: String = "")

  case class State(direction: UndefOr[Directions.Value] = undefined)

  class Backend($: BackendScope[Props, State]) {
    var animationTimer: js.UndefOr[js.timers.SetTimeoutHandle] =
      js.undefined

    def startAnimation(i: Int) = {
      animationTimer = js.timers.setTimeout(i)(beginAnimation())
    }

    def endAnimation() = {
      if ($.isMounted() && $.props.onAnimationOutEnd.isDefined)
        $.props.onAnimationOutEnd.get($.props.index)
    }

    def beginAnimation() = {
      if ($.isMounted())
        $.modState(s => s.copy(direction = if ($.props.direction == Directions.prev) Directions.right else Directions.left))
    }
  }

  val CarouselItem = ReactComponentB[Props]("CarouselItem")
    .initialState(State())
    .backend(new Backend(_))
    .render { (P, C, S, B) =>

    def renderCaption() = {
      if (P.caption.isDefined) {
        <.div(^.className := "carousel-caption")(P.caption.get)
      }
      else
        EmptyTag
    }

    var classes = Map("item" -> true,
      "active" -> (P.active.getOrElse(false) && !P.animateIn.getOrElse(false) || P.animateOut.getOrElse(false)),
      "next" -> (P.active.getOrElse(false) && P.animateIn.getOrElse(false) && P.direction == Directions.next)
    )

    if (S.direction.isDefined && (P.animateIn.getOrElse(false) || P.animateOut.getOrElse(false)))
      classes += (S.direction.get.toString -> true)

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(C, renderCaption())
  }.componentWillReceiveProps(($, newProps) =>
    if ($.props.active.getOrElse(false) != newProps.active.getOrElse(false))
      $.modState(s => s.copy(direction = undefined))
    )
    .componentDidUpdate { ($, previousProps, S) =>
    if ($.props.active.getOrElse(false) && previousProps.active.getOrElse(false)) {
      // FIXME transition event handling
    }

    if ($.props.active.getOrElse(false) != previousProps.active.getOrElse(false)) {
      $.backend.startAnimation(20)

    }
  }

    .build
}
