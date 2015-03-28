package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.{AlertAutoDismissable, AlertDismissable}
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Alerts {

  val exampleSource =
    """
      |Alert.Alert(bsStyle = Styles.warning)(<.strong("Holy guacamole!"), " Best check yo self, you're not looking too good."))
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Alert.Alert(bsStyle = Styles.warning)(<.strong("Holy guacamole!"), " Best check yo self, you're not looking too good."))

  val closableSource =
    """
      |object AlertDismissable {
      | case class State(alertVisible: Boolean = true)
      |
      |  class Backend(scope: BackendScope[Unit, State]) {
      |    def handleAlertDismiss() = scope.modState(s => s.copy(alertVisible = false))
      |
      |    def handleAlertShow() = scope.modState(s => s.copy(alertVisible = true))
      |  }
      |
      |
      |  val AlertDismissable = ReactComponentB[Unit]("AlertDismissable")
      |    .initialState(State(alertVisible = true))
      |    .backend(new Backend(_))
      |    .render((P, C, S, B) => {
      |
      |    if (S.alertVisible)
      |      Alert.Alert(bsStyle = Styles.danger, onDismiss = () => B.handleAlertDismiss())(
      |        <.h4("Oh snap! You got an error!"),
      |        <.p("Change this and that and try again. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum."),
      |        <.p(
      |          Button.Button(bsStyle = Styles.danger)("Take this action"),
      |          "or",
      |          Button.Button(onClick = (e: ReactEvent) => B.handleAlertDismiss())("Hide Alert")))
      |    else
      |      Button.Button(onClick = (e: ReactEvent) => B.handleAlertShow())("Show Alert")
      |  }).buildU
      |
      |  def apply() = AlertDismissable()
      |}
      |
      |AlertDismissable()
    """.stripMargin

  def closableContent = CodeContent.Content(closableSource, AlertDismissable())

  val autoClosableSource =
    """
      |object AlertAutoDismissable {
      |
      |  case class State(alertVisible: Boolean = true)
      |
      |  class Backend(scope: BackendScope[Unit, State]) {
      |
      |    def handleAlertDismiss() = scope.modState(s => s.copy(alertVisible = false))
      |
      |    def handleAlertShow() = scope.modState(s => s.copy(alertVisible = true))
      |  }
      |
      |
      |  val AlertAutoDismissable = ReactComponentB[Unit]("AlertAutoDismissable")
      |    .initialState(State(alertVisible = false))
      |    .backend(new Backend(_))
      |    .render((P, C, S, B) => {
      |
      |    if (S.alertVisible)
      |      Alert.Alert(bsStyle = Styles.danger, onDismiss = () => B.handleAlertDismiss(), dismissAfter = 2000)(
      |        <.h4("Oh snap! You got an error!"),
      |        <.p("But this will hide after 2 seconds."))
      |    else
      |      Button.Button(onClick = (e: ReactEvent) => B.handleAlertShow())("Show Alert")
      |  }).buildU
      |
      |  def apply() = AlertAutoDismissable()
      |}
      |
      |AlertAutoDismissable()
    """.stripMargin

  def autoClosableContent = CodeContent.Content(autoClosableSource, AlertAutoDismissable())

  val contextualSource =
    """
      |<.div(
      |  Alert.Alert(bsStyle = Styles.success)("Well done! You successfully read this important alert message."),
      |  Alert.Alert(bsStyle = Styles.info)("Heads up! This alert needs your attention, but it's not super important."),
      |  Alert.Alert(bsStyle = Styles.warning)("Warning! Better check yourself, you're not looking too good."),
      |  Alert.Alert(bsStyle = Styles.danger)("Oh snap! Change a few things up and try submitting again.")
      |)
    """.stripMargin

  def contextualContent = CodeContent.Content(contextualSource,
    <.div(
      Alert.Alert(bsStyle = Styles.success)("Well done! You successfully read this important alert message."),
      Alert.Alert(bsStyle = Styles.info)("Heads up! This alert needs your attention, but it's not super important."),
      Alert.Alert(bsStyle = Styles.warning)("Warning! Better check yourself, you're not looking too good."),
      Alert.Alert(bsStyle = Styles.danger)("Oh snap! Change a few things up and try submitting again.")
    )
  )

  val content = Section("alerts", <.span("Alert messages ", <.small("alert"))
    , SubSection("alerts-example", "Example Alerts",
      <.p("Basic alert styles."),
      exampleContent(),
      <.p("Closeable alerts, just pass in a ", <.code("onDismiss"), " function."),
      closableContent(),
      <.p("Auto close after a set time with ", <.code("dismissAfter"), " prop"),
      autoClosableContent())
    , SubSection("alerts-contextual", "Contextual alternatives",
      <.p("Like other components, easily make an alert more meaningful to a particular context by adding a ", <.code("bsStyle"), " prop."),
      contextualContent())
  )
}
