package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 11/03/15.
 */
object ModalTrigger {

  class Container extends OverlayContainer {
    override def getDOMNode = super.getDOMNode
  }

  case class ModalTrigger(modal: ReactElement, container: OverlayContainer = new OverlayContainer {}) extends OverlayProps {

    def apply(child: ReactNode) = component(this, child)

    def apply() = component(this)
  }

  case class State(isOverlayShown: Boolean = false)

  class Backend(val scope: BackendScope[ModalTrigger, State]) extends OverlayMixin[ModalTrigger, State] {

    def show() = scope.modState(_.copy(isOverlayShown = true))

    def hide() = scope.modState(_.copy(isOverlayShown = false))

    def toggle() = {
      scope.modState(s => s.copy(isOverlayShown = !s.isOverlayShown))
    }

    override def renderOverlay(): Option[ReactElement] = {
      if (scope.state.isOverlayShown)
        Some(cloneWithProps(scope.props.modal, (undefined, undefined),
          Map("container" -> scope.props.container,
            "onRequestHide" -> (() => hide()))))
      else
        Some(<.span())
    }
  }

  val component = ReactComponentB[ModalTrigger]("ModelTrigger")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    val child = React.Children.only(C)
    val childProps = getChildProps[Any](child).asInstanceOf[js.Dynamic]
    val childOnClick: UndefOr[js.Any] = childProps.onClick
    val jsChildOnClick: UndefOr[(ReactEvent) => (js.Any)] = if (childOnClick == null || childOnClick == undefined) undefined else childOnClick.asInstanceOf[(ReactEvent) => (js.Any)]
    val toggle = ((e: ReactEvent) => B.toggle()).asInstanceOf[(ReactEvent) => (js.Any)]
    val onClick = createChainedFunction1(jsChildOnClick, toggle)

    cloneWithProps(child, (undefined, undefined), Map("onClick" -> onClick))
  })
    .componentDidMount(scope => scope.backend.onComponentDidMount())
    .componentDidUpdate((scope, nextProps, state) => scope.backend.onComponentDidUpdate())
    .componentWillUnmount(scope => scope.backend.onComponentWillUnmount())
    .build

  def apply(props: ModalTrigger, child: ReactNode) = component(props, child)
}
