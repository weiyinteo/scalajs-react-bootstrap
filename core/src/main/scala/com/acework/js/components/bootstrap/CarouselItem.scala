package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable, TransitionEvent}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.Event

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object CarouselItem extends BootstrapComponent {
  override type P = CarouselItem
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = CarouselItem()

  case class CarouselItem(index: Int = 0,
                   active: UndefOr[Boolean] = undefined,
                   animation: UndefOr[Boolean] = true,
                   animateIn: UndefOr[Boolean] = false,
                   animateOut: UndefOr[Boolean] = false,
                   direction: UndefOr[Directions.Value] = Directions.next,
                   onAnimateOutEnd: UndefOr[(Int) => Unit] = undefined,
                   caption: UndefOr[String] = undefined,
                   addClasses: String = "") extends MergeableProps[CarouselItem] {

    def merge(t: Map[String, Any]): CarouselItem = implicitly[Mergeable[CarouselItem]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[CarouselItem]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  case class State(direction: UndefOr[Directions.Value] = undefined)

  class Backend($: BackendScope[CarouselItem, State]) {

    def startAnimation() = {
      if ($.isMounted())
        $.modState(s => s.copy(direction = if ($.props.direction.getOrElse(Directions.next) == Directions.prev) Directions.right else Directions.left))
    }

    def onAnimateOutEnd(e: Any) = {
      if ($.isMounted() && $.props.onAnimateOutEnd.isDefined)
        $.props.onAnimateOutEnd.get($.props.index)
    }
  }

  val component = ReactComponentB[CarouselItem]("CarouselItem")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def renderCaption() = {
      if (P.caption.isDefined) {
        <.div(^.className := "carousel-caption")(P.caption.get)
      }
      else
        EmptyTag
    }

    var classes = Map("item" -> true,
      "active" -> (P.active.getOrElse(false) && !P.animateIn.getOrElse(false) || P.animateOut.getOrElse(false)),
      "next" -> (P.active.getOrElse(false) && P.animateIn.getOrElse(false) && P.direction.getOrElse(Directions.next) == Directions.next),
      "prev" -> (P.active.getOrElse(false) && P.animateIn.getOrElse(false) && P.direction.getOrElse(Directions.next) == Directions.prev)
    )

    if (S.direction.isDefined && (P.animateIn.getOrElse(false) || P.animateOut.getOrElse(false)))
      classes += (S.direction.get.toString -> true)

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(C, renderCaption())
  })
    .componentWillReceiveProps(($, newProps) =>
    if ($.props.active.getOrElse(false) != newProps.active.getOrElse(false))
      $.modState(_.copy(direction = undefined))
    )
    .componentDidUpdate(($, previousProps, S) => {
    if (!$.props.active.getOrElse(false) && previousProps.active.getOrElse(false)) {
      TransitionEvent.addEndEventListener($.getDOMNode(), (e: Event) => $.backend.onAnimateOutEnd(e))
    }

    if ($.props.active.getOrElse(false) != previousProps.active.getOrElse(false)) {
      js.timers.setTimeout(20)($.backend.startAnimation())
    }
  })
    .build
}
