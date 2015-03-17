package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Alert extends BootstrapMixin {

  type PROPS = Props

  case class Props(dismissAfter: UndefOr[Int] = undefined,
                   onDismiss: UndefOr[() => Unit] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.alert,
                   bsStyle: UndefOr[Styles.Value] = Styles.info,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  class Backend($: BackendScope[_, _]) {
    var dismissTimer: js.UndefOr[js.timers.SetTimeoutHandle] =
      js.undefined

    def clearTimer() = {
      if (dismissTimer.isDefined)
        js.timers.clearTimeout(dismissTimer.get)
    }

    def start(dismissAfter: Int, onDismiss: () => Unit) =
      dismissTimer = js.timers.setTimeout(dismissAfter)(onDismiss())
  }

  val Alert = ReactComponentB[Props]("Alert")
    .stateless
    .backend(new Backend(_))
    .render { (P, C, S, B) =>
    var classes = getBsClassSet(P)
    val isDismissable = P.onDismiss.isDefined
    classes += ("alert-dismissable" -> isDismissable)

    def renderDismissButton() = {
      <.button(^.tpe := "button", ^.className := "close",
        ^.onClick --> P.onDismiss.get(), ^.aria.hidden := true, ^.dangerouslySetInnerHtml("&times;"))
    }

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, classes))(
      if (isDismissable) renderDismissButton() else EmptyTag,
      C
    )
  }
    .componentDidMount($ =>
    if ($.props.dismissAfter.isDefined && $.props.onDismiss.isDefined)
      $.backend.start($.props.dismissAfter.get, $.props.onDismiss.get)
    )
    .componentWillUnmount($ => $.backend.clearTimer())
    .build

  def apply(props: Props, children: ReactNode*): ReactComponentU[Props, Unit, Backend, TopNode] = Alert(props, children)
}
