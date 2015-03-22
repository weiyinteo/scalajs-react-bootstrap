package com.acework.js.components.bootstrap

import com.acework.js.utils.{TransitionEvent, Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}
import Utils._

/**
 * Created by weiyin on 22/03/15.
 */
object TabPane extends BootstrapComponent {
  override type P = TabPane
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = TabPane()

  case class TabPane(active: Boolean = false,
                   animation: Boolean = true,
                   tab: UndefOr[String] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   onAnimateOutEnd: UndefOr[() => Unit] = undefined,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   addClasses: String = "") extends MergeableProps[TabPane] {

    def merge(t: Map[String, Any]): TabPane = implicitly[Mergeable[TabPane]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[TabPane]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)

  }

  case class State(animateIn: Boolean, animateOut: Boolean)

  class Backend(scope: BackendScope[TabPane, State]) {

    def onComponentWillReceiveProps(nextProps: TabPane) = {
      if (scope.props.animation) {
        if (!scope.state.animateIn && nextProps.active && !scope.props.active)
          scope.modState(_.copy(animateIn = true))
        else if (!scope.state.animateIn && !nextProps.active && scope.props.active)
          scope.modState(_.copy(animateOut = true))
      }
    }

    def onComponentDidUpdate() = {
      if (scope.state.animateIn)
        js.timers.setTimeout(0)(startAnimateIn())
      if (scope.state.animateOut)
        TransitionEvent.addEndEventListener(
          scope.getDOMNode(), (_: Event) => stopAnimateOut()
        )
    }

    def startAnimateIn() = {
      if (scope.isMounted())
        scope.modState(_.copy(animateIn = false))
    }

    def stopAnimateOut() = {
      if (scope.isMounted())
        scope.modState(_.copy(animateOut = false))

      if (scope.props.onAnimateOutEnd.isDefined)
        scope.props.onAnimateOutEnd.get()
    }
  }

  override val component = ReactComponentB[TabPane]("TabPane")
    .initialState(State(animateIn = false, animateOut = false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    val classes = Map(
      "active" -> (P.active || S.animateOut),
      "in" -> (P.active && !S.animateIn)
    )
    // FIXME spread props
    <.div(^.classSet1M("tab-pane fade", classes), C)
  })
    .componentWillReceiveProps((scope, nextProps) => {
    scope.backend.onComponentWillReceiveProps(nextProps)
  })
    .componentDidUpdate((scope, prevProps, prevState) => scope.backend.onComponentDidUpdate())
    .build

}
