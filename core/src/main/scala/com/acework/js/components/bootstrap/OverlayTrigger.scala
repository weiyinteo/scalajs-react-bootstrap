package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.DefaultReusabilityOverlay.autoLiftHtml
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 21/03/15.
 */
object OverlayTrigger extends BootstrapComponent {
  override type P = OverlayTrigger
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = OverlayTrigger(overlay = <.span(""))

  case class OverlayTrigger(overlay: ReactElement,
                   placement: Placements.Value = Placements.right,
                   delay: UndefOr[Double] = undefined,
                   delayHide: UndefOr[Double] = undefined,
                   delayShow: UndefOr[Double] = undefined,
                   defaultOverlayShown: UndefOr[Boolean] = undefined,
                   onClick: UndefOr[(ReactEvent) => Unit] = undefined,
                   onBlur: UndefOr[(ReactEvent) => Unit] = undefined,
                   onFocus: UndefOr[(ReactEvent) => Unit] = undefined,
                   onMouseOver: UndefOr[(ReactEvent) => Unit] = undefined,
                   onMouseOut: UndefOr[(ReactEvent) => Unit] = undefined,
                   trigger: Array[String] = Array("hover", "focus"),
                   container: OverlayContainer = new OverlayContainer {}) extends OverlayProps with MergeableProps[OverlayTrigger] {

    def merge(t: Map[String, Any]): OverlayTrigger = implicitly[Mergeable[OverlayTrigger]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[OverlayTrigger]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  case class State(isOverlayShown: Boolean, overlayLeft: UndefOr[Double], overlayTop: UndefOr[Double])

  class Backend(val scope: BackendScope[OverlayTrigger, State]) extends OverlayMixin[OverlayTrigger, State] {

    var hoverDelayTimer: js.UndefOr[js.timers.SetTimeoutHandle] = undefined

    def clearTimeout() = {
      if (hoverDelayTimer.isDefined) {
        js.timers.clearTimeout(hoverDelayTimer.get)
        hoverDelayTimer = undefined
      }
    }

    def show() = {
      scope.modState(_.copy(isOverlayShown = true), () => updateOverlayPosition())
    }

    def hide() = {
      scope.modState(_.copy(isOverlayShown = false), () => updateOverlayPosition())
    }

    def toggle() = {
      if (scope.state.isOverlayShown)
        hide()
      else
        show()
    }

    override def renderOverlay(): Option[ReactElement] = {
      if (scope.state.isOverlayShown) {
        Some(cloneWithProps(scope.props.overlay, (undefined, undefined),
          Map(
            "placement" -> scope.props.placement,
            "positionLeft" -> scope.state.overlayLeft,
            "positionTop" -> scope.state.overlayTop,
            "onRequestHide" -> (() => hide()))))
      }
      else
        Some(<.span())
    }

    def handleDelayedShow() = {
      if (hoverDelayTimer.isDefined) {
        clearTimeout()
      }
      else {
        val delay = scope.props.delayShow.getOrElse(scope.props.delay.getOrElse(0.0))
        if (delay == 0)
          show()
        else
          hoverDelayTimer = js.timers.setTimeout(delay)({
            hoverDelayTimer = undefined
            show()
          })

      }
    }

    def handleDelayedHide() = {
      if (hoverDelayTimer.isDefined) {
        clearTimeout()
      }
      else {
        val delay = scope.props.delayHide.getOrElse(scope.props.delay.getOrElse(0.0))
        if (delay == 0)
          hide()
        else
          hoverDelayTimer = js.timers.setTimeout(delay)({
            hoverDelayTimer = undefined
            hide()
          })
      }
    }

    def updateOverlayPosition() = {
      if (scope.isMounted()) {
        val (top, left) = calcOverlayPosition()
        scope.modState(s => s.copy(overlayLeft = left, overlayTop = top))
      }
    }

    // return tuple of (top, left)
    def calcOverlayPosition(): (Double, Double) = {
      val childOffset = getPosition
      val overlayNode = getOverlayDOMNode.get
      val overlayHeight = overlayNode.offsetHeight
      val overlayWidth = overlayNode.offsetWidth

      scope.props.placement match {
        case Placements.right =>
          (childOffset.top + childOffset.height / 2 - overlayHeight / 2,
            childOffset.left + childOffset.width)
        case Placements.left =>
          (childOffset.top + childOffset.height / 2 - overlayHeight / 2,
            childOffset.left - overlayWidth)
        case Placements.top =>
          (childOffset.top - overlayHeight,
            childOffset.left + childOffset.width / 2 - overlayWidth / 2)
        case Placements.bottom =>
          (childOffset.top + overlayHeight,
            childOffset.left + childOffset.width / 2 - overlayWidth / 2)
      }
    }

    case class Position(left: Double, top: Double, width: Double, height: Double)

    def getPosition = {
      val node = scope.getDOMNode()
      val container = getContainerDOMNode

      val offset =
        if (container.tagName == "BODY") {
          jQuery(node).offset().asInstanceOf[js.Dynamic]
        }
        else
          jQuery(node).position().asInstanceOf[js.Dynamic]

      Position(offset.left.asInstanceOf[Double], offset.top.asInstanceOf[Double], node.offsetWidth, node.offsetHeight)
    }
  }

  val component = ReactComponentB[OverlayTrigger]("OverlayTrigger")
    .initialStateP(P => State(isOverlayShown = P.defaultOverlayShown.getOrElse(false), undefined, undefined))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    if (P.trigger.contains("manual")) {
      cloneWithProps(React.Children.only(C), (undefined, undefined), Map.empty)
    }
    else {
      var propsMap = Map.empty[String, Any]
      if (P.trigger.contains("click")) {
        propsMap += ("onClick" -> createChainedFunction1((_: ReactEvent) => B.toggle(), P.onClick))
      }
      else if (P.trigger.contains("hover")) {
        propsMap += ("onMouseOver" -> createChainedFunction1((x: ReactEvent) => B.handleDelayedShow(), P.onMouseOver))
        propsMap += ("onMouseOut" -> createChainedFunction1((x: ReactEvent) => B.handleDelayedHide(), P.onMouseOut))
      }
      else if (P.trigger.contains("focus")) {
        propsMap += ("onFocus" -> createChainedFunction1((x: ReactEvent) => B.handleDelayedShow(), P.onFocus))
        propsMap += ("onBlur" -> createChainedFunction1((x: ReactEvent) => B.handleDelayedHide(), P.onBlur))
      }

      def renderChild(child: ReactNode, index: Int) = {
        cloneWithProps(child, (undefined, undefined), propsMap)
      }

      // FIXME
      var c: ReactElement = null
      C.forEach((n, idx) => c = renderChild(n, idx))
      c
    }

  })
    .componentDidMount(scope => {
    scope.backend.onComponentDidMount()
    if(scope.props.defaultOverlayShown.getOrElse(false))
      scope.backend.updateOverlayPosition()
  })
    .componentDidUpdate((scope, nextProps, state) => scope.backend.onComponentDidUpdate())
    .componentWillUnmount(scope => {
    scope.backend.onComponentWillUnmount()
    scope.backend.clearTimeout()
  })
    .build

}
