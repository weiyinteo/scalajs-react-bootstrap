package demo.examples

import com.acework.js.components.bootstrap.{Button, Alert, Styles}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 13/03/15.
 */
object AlertAutoDismissable {

  case class State(alertVisible: Boolean = true)

  class Backend(scope: BackendScope[Unit, State]) {

    def handleAlertDismiss() = scope.modState(s => s.copy(alertVisible = false))

    def handleAlertShow() = scope.modState(s => s.copy(alertVisible = true))
  }


  val AlertAutoDismissable = ReactComponentB[Unit]("AlertAutoDismissable")
    .initialState(State(alertVisible = false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    if (S.alertVisible)
      Alert.Alert(bsStyle = Styles.danger, onDismiss = () => B.handleAlertDismiss(), dismissAfter = 2000)(
        <.h4("Oh snap! You got an error!"),
        <.p("But this will hide after 2 seconds."))
    else
      Button.Button(onClick = (e: ReactEvent) => B.handleAlertShow())("Show Alert")
  }).buildU

  def apply() = AlertAutoDismissable()

}
