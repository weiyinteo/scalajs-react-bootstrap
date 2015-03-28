package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement

import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Carousel extends BootstrapComponent {
  override type P = Carousel
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = Carousel()

  case class State(activeIndex: Int, previousActiveIndex: UndefOr[Int] = undefined, direction: UndefOr[Directions.Value] = undefined)

  case class Carousel(slide: UndefOr[Boolean] = true,
                   indicators: UndefOr[Boolean] = true,
                   controls: UndefOr[Boolean] = true,
                   pauseOnHover: UndefOr[Boolean] = true,
                   wrap: UndefOr[Boolean] = true,
                   onSelect: UndefOr[(Int, UndefOr[Directions.Value]) => Unit] = undefined,
                   onSlideEnd: UndefOr[() => Unit] = undefined,
                   interval: UndefOr[Int] = 5000,
                   activeIndex: UndefOr[Int] = undefined,
                   defaultActiveIndex: UndefOr[Int] = undefined,
                   direction: UndefOr[Directions.Value] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`btn-group`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[Carousel] {

    def merge(t: Map[String, Any]): Carousel = implicitly[Mergeable[Carousel]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Carousel]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend($: BackendScope[Carousel, State]) {
    var timer: js.UndefOr[js.timers.SetTimeoutHandle] = js.undefined
    var isPaused: Boolean = false

    def activeIndex: Int = {
      if ($.props.activeIndex.isDefined)
        $.props.activeIndex.get
      else
        $.state.activeIndex
    }

    def clearTimeout() = {
      if (timer.isDefined) {
        js.timers.clearTimeout(timer.get)
        timer = undefined
      }
    }

    def pause() = {
      isPaused = true
      clearTimeout()
    }

    def play() = {
      isPaused = false
      waitForNext()
    }

    def onMouseOver(e: ReactEvent): Unit = {
      if ($.props.pauseOnHover.getOrElse(false))
        pause()
    }

    def onMouseOut(e: ReactEvent): Unit = {
      if (isPaused)
        play()
    }

    def getDirection(prevIndex: Int, index: Int): UndefOr[Directions.Value] = {
      if (prevIndex == index)
        undefined
      else if (prevIndex > index)
        Directions.prev
      else
        Directions.next
    }

    def handleSelect(index: Int)(direction: UndefOr[Directions.Value]): Unit = {
      clearTimeout()

      val previousActiveIndex = activeIndex
      val newDirection = if (direction.isDefined) direction else getDirection(previousActiveIndex, index)

      if ($.props.onSelect.isDefined)
        $.props.onSelect.get(index, direction)

      if ($.props.activeIndex.isEmpty && index != previousActiveIndex) {
        if ($.state.previousActiveIndex.isDefined) {
          // if currently animating don't activate the new index
        }
        else {
          $.modState(s => s.copy(activeIndex = index, previousActiveIndex = previousActiveIndex, direction = newDirection))
        }
      }
    }

    def waitForNext() = {
      if (!isPaused && $.props.slide.getOrElse(false) && $.props.interval.isDefined && $.props.activeIndex.isEmpty) {
        timer = js.timers.setTimeout($.props.interval.get)(next(null))
      }
    }

    def handleItemAnimateOutEnd(): Unit = {
      val cb = () => {
        waitForNext()
        if ($.props.onSlideEnd.isDefined)
          $.props.onSlideEnd.get()
      }

      $.modState(s => s.copy(previousActiveIndex = undefined, direction = undefined), cb)
    }

    def prev(e: ReactEvent): Unit = {
      if (e != null)
        e.preventDefault()

      var index = activeIndex - 1
      if (index < 0)
        if ($.props.wrap.getOrElse(false))
          index = ValidComponentChildren.numberOfValidComponents($.propsChildren) - 1
        else
          index = -1

      if (index >= 0)
        handleSelect(index)(Directions.prev)
    }

    def next(e: ReactEvent): Unit = {
      if (e != null)
        e.preventDefault()

      var index = activeIndex + 1
      val count = ValidComponentChildren.numberOfValidComponents($.propsChildren)
      if (index > count - 1)
        if ($.props.wrap.getOrElse(false))
          index = 0
        else
          index = -1

      if (index >= 0)
        handleSelect(index)(Directions.next)
    }
  }

  override val component = ReactComponentB[Carousel]("Carousel")
    .initialStateP(P => State(activeIndex = P.defaultActiveIndex.getOrElse(0)))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def renderIndicator(child: ReactNode, index: Int) = {
      <.li(^.key := index, ^.classSet("active" -> (index == B.activeIndex)), ^.onClick ==> B.handleSelect(index))
    }

    def renderIndicators() = {
      val indicators = ArrayBuffer[ReactNode]()

      ValidComponentChildren.forEach2(C, (child, index) => {
        indicators += renderIndicator(child, index)
        // Force whitespace between indicator elements, bootstrap
        // requires this for correct spacing of elements.
        indicators += " "
      })
      <.ol(^.className := "carousel-indicators",
        indicators
      )
    }

    def renderPrev() = {
      <.a(^.className := "left carousel-control", ^.href := "#prev", ^.key := 0, ^.onClick ==> B.prev,
        <.span(^.className := "glyphicon glyphicon-chevron-left")
      )
    }

    def renderNext() = {
      <.a(^.className := "right carousel-control", ^.href := "#next", ^.key := 1, ^.onClick ==> B.next,
        <.span(^.className := "glyphicon glyphicon-chevron-right")
      )
    }

    def renderItem(child: ReactNode, index: Int) = {
      val isActive = B.activeIndex == index
      val isPreviousActive = S.previousActiveIndex.isDefined && S.previousActiveIndex.get == index && P.slide.get
      val onAnimatedOutEnd: UndefOr[(Int) => Unit] = if (isPreviousActive) (x: Int) => B.handleItemAnimateOutEnd() else (x: Int) => ()

      val keyAndRef = getChildKeyAndRef2(child, index)
      val propsMap = Map[String, Any](
        "active" -> isActive,
        "index" -> index,
        "animateOut" -> isPreviousActive,
        "animateIn" -> (isActive && S.previousActiveIndex.isDefined && P.slide.getOrElse(false)),
        "direction" -> S.direction,
        "onAnimateOutEnd" -> onAnimatedOutEnd
      )
      cloneWithProps(child, keyAndRef, propsMap)
    }

    def renderControl() = {
      if (P.wrap.getOrElse(false)) {
        val activeIndex = B.activeIndex
        val count = ValidComponentChildren.numberOfValidComponents(C)
        Seq(
          if (activeIndex != 0) renderPrev() else EmptyTag,
          if (activeIndex != count - 1) renderNext() else EmptyTag
        )
      }
      else {
        Seq(renderPrev(), renderNext())
      }
    }

    val classes = Map("carousel" -> true, "slide" -> P.slide.get)
    val innerRef = Ref[HTMLElement]("inner")
    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes),
      ^.onMouseOver ==> B.onMouseOver,
      ^.onMouseOut ==> B.onMouseOut,
      renderIndicators(),
      <.div(^.className := "carousel-inner", ^.ref := innerRef,
        ValidComponentChildren.map(C, renderItem)),
      renderControl()
    )
  })
    .componentWillReceiveProps(($, nextProps) => {
    val activeIndex = $.backend.activeIndex
    if (nextProps.activeIndex.isDefined && nextProps.activeIndex.get != activeIndex) {
      val nextDirection: UndefOr[Directions.Value] =
        if (nextProps.direction.isDefined)
          nextProps.direction
        else
          $.backend.getDirection(activeIndex, nextProps.activeIndex.getOrElse(0))
      $.backend.clearTimeout()
      $.modState(s => s.copy(previousActiveIndex = activeIndex: UndefOr[Int], direction = nextDirection))
    }
  })
    .componentDidMount($ => {
    $.backend.waitForNext()
  })
    .componentWillUnmount($ => {
    $.backend.clearTimeout()
  })
    .build
}
