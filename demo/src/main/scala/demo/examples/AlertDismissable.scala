package demo.examples

import com.acework.js.components.bootstrap.{Alert, Button, Styles}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 13/03/15.
 */
object AlertDismissable {

  case class State(alertVisible: Boolean = true)

  class Backend(scope: BackendScope[Unit, State]) {
    def handleAlertDismiss() = scope.modState(s => s.copy(alertVisible = false))

    def handleAlertShow() = scope.modState(s => s.copy(alertVisible = true))
  }


  val AlertDismissable = ReactComponentB[Unit]("AlertDismissable")
    .initialState(State(alertVisible = true))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    if (S.alertVisible)
      Alert.Alert(bsStyle = Styles.danger, onDismiss = () => B.handleAlertDismiss())(
        <.h4("Oh snap! You got an error!"),
        <.p("Change this and that and try again. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum."),
        <.p(
          Button.Button(bsStyle = Styles.danger)("Take this action"),
          "or",
          Button.Button(onClick = (e: ReactEvent) => B.handleAlertDismiss())("Hide Alert")))
    else
      Button.Button(onClick = (e: ReactEvent) => B.handleAlertShow())("Show Alert")
  }).buildU

  def apply() = AlertDismissable()

}
