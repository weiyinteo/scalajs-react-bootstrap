package demo.examples

import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import com.acework.js.components.bootstrap._
import demo.examples._

/**
 * Created by weiyin on 16/03/15.
 */
object Alerts {

  def exampleAlerts = CodeContent.Content(exampleAlertSource,
    Alert(Alert.Props(bsStyle = Styles.warning), <.strong("Holy guacamole!"), " Best check yo self, you're not looking too good."))

  val exampleAlertSource =
    """
      |Alert(Alert.Props(bsStyle = Styles.warning, <.strong("Holy guacamole!"), " Best check yo self, you're not looking too good."))
    """.stripMargin

  val content = <.div(
    PageHeader(<.h1("Alert messages ", <.small("alert"))),
    <.h3("Example Alerts"),
    exampleAlerts(),
    <.h3("closable alert"),
    AlertDismissable(),
    <.h3("Auto hide alert"),
    AlertAutoDismissable(),
    Alert(Alert.Props(bsStyle = Styles.success), "Well done! You successfully read this important alert message."),
    Alert(Alert.Props(bsStyle = Styles.info), "Heads up! This alert needs your attention, but it's not super important."),
    Alert(Alert.Props(bsStyle = Styles.warning), "Warning! Better check yourself, you're not looking too good."),
    Alert(Alert.Props(bsStyle = Styles.danger), "Oh snap! Change a few things up and try submitting again.")
  )
}
