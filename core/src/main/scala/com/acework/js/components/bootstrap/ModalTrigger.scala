package com.acework.js.components.bootstrap

import Utils._
import com.acework.js.logger._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 11/03/15.
 */
object ModalTrigger {

  case class Props(modal: ReactNode)

  case class State(isOverlayShown: Boolean = false)

  class Backend(scope: BackendScope[Props, State]) {
    def show() = scope.modState(s => s.copy(isOverlayShown = true))

    def hide() = scope.modState(s => s.copy(isOverlayShown = false))

    def toggle() = {
      log.debug("toggle called")
      scope.modState(s => s.copy(isOverlayShown = !s.isOverlayShown))
    }
  }

  val ModalTrigger = ReactComponentB[Props]("ModelTrigger")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def renderOverlay() = {
      if (!S.isOverlayShown)
        <.span()

      ReactCloneWithProps(P.modal, Map("onRequestHide" -> B.hide))
    }

    val child = React.Children.only(C)
    val dynChild = child.asInstanceOf[js.Dynamic]
    val childProps = dynChild.props

    val childOnClick = childProps.onClick
    val jsChildOnClick: UndefOr[() => (js.Any)] = if (childOnClick == null || childOnClick == js.undefined) js.undefined else childOnClick.asInstanceOf[() => (js.Any)]
    val toggle = (() => B.toggle()).asInstanceOf[() => (js.Any)]
    val res = ReactCloneWithProps(child, Map("onClick" -> createChainedFunction0(jsChildOnClick, toggle)))
    res
  }).build

  def apply(props: Props, children: ReactNode) = ModalTrigger(props, children)
}
