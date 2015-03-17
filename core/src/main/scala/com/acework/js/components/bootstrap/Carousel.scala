package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement

import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Carousel extends BootstrapMixin {

  type PROPS = Props

  case class State(activeIndex: Int, previousActiveIndex: UndefOr[Int] = undefined, direction: UndefOr[Directions.Value] = undefined)

  case class Props(slide: UndefOr[Boolean] = true,
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
                   addClasses: String = "") extends BaseProps

  class Backend($: BackendScope[Props, State]) {
    var timer: js.UndefOr[js.timers.SetTimeoutHandle] = js.undefined
    var isPaused: Boolean = false

    def activeIndex: Int = {
      if ($.props.activeIndex.isDefined)
        $.props.activeIndex.get
      else
        $.state.activeIndex
    }

    def onMouseOver(e: ReactEvent): Unit = ???

    def onMouseOut(e: ReactEvent): Unit = ???

    def getDirection(i: Int, j: Int): Directions.Value = ???

    def handleSelect(index: Int)(direction: UndefOr[Directions.Value]): Unit = {
      js.timers.clearTimeout(timer.get)

      val previousActiveIndex = activeIndex
      val newDirection: Directions.Value = direction.getOrElse(getDirection(previousActiveIndex, index))

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
      if (isPaused && $.props.slide.getOrElse(false) && $.props.interval.isDefined && $.props.activeIndex.isEmpty)
        timer = js.timers.setTimeout($.props.interval.get)(next(_: ReactEvent))
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


  val Carousel = ReactComponentB[Props]("Carousel")
    .initialStateP(P => State(activeIndex = if (P.defaultActiveIndex.isEmpty) 0 else P.defaultActiveIndex.get))
    .backend(new Backend(_))
    .render { (P, C, S, B) =>


    def renderIndicator(child: ReactNode, index: Int) = {
      <.li(^.key := index, ^.classSet("active" -> (index == B.activeIndex)), ^.onClick --> B.handleSelect(index) _)
    }

    def renderIndicators() = {
      val indicators = ArrayBuffer[ReactNode]()

      ValidComponentChildren.forEach2(C, (child, index) => {
        indicators += renderIndicator(child, index)
        indicators += " "
      })
      <.ol(^.className := "carousel-indicators",
        indicators
      )
    }

    def renderPrev() = {
      <.a(^.className := "left carousel-control", ^.href := "#prev", ^.key := 0, ^.onClick --> B.prev _,
        <.span(^.className := "glyphicon glyphicon-chevron-left")
      )
    }

    def renderNext() = {
      <.a(^.className := "right carousel-control", ^.href := "#next", ^.key := 1, ^.onClick --> B.next _,
        <.span(^.className := "glyphicon glyphicon-chevron-right")
      )
    }

    def renderItem(child: ReactNode, index: Int) = {
      val isActive = B.activeIndex == index
      val isPreviousActive = S.previousActiveIndex.isDefined && S.previousActiveIndex.get == index && P.slide.get
      val onAnimatedOutEnd: UndefOr[() => Unit] = if (isPreviousActive) (B.handleItemAnimateOutEnd _) else undefined

      ReactCloneWithProps(child, getChildKeyAndRef(child, index) ++
        Map[String, js.Any]("active" -> isActive,
          "index" -> index,
          "animateOut" -> isPreviousActive,
          "animateIn" -> (isActive && S.previousActiveIndex.isDefined && P.slide.get),
          "direction" -> S.direction.toString,
          "onAnimateOutEnd" -> onAnimatedOutEnd
        ))
    }

    def renderControl() = {
      if (P.wrap.getOrElse(false)) {
        val count = ValidComponentChildren.numberOfValidComponents(C)
        Seq(
          if (B.activeIndex != 0) renderPrev() else EmptyTag,
          if (B.activeIndex != count - 1) renderNext() else EmptyTag
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
      ^.onMouseOver --> B.onMouseOver _,
      ^.onMouseOut --> B.onMouseOut _,
      renderIndicators(),
      <.div(^.className := "carousel-inner", ^.ref := innerRef)(ValidComponentChildren.map(C, renderItem)),
      renderControl()
    )

  }.build
}
