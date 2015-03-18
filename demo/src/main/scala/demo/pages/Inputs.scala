package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Inputs {

  def exampleSource =
    """
      |
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    <.div()
    //Input(Input.Props(`type` = "text", ))
  )

  def typesSource =
    """
      |
    """.stripMargin

  def typesContent = CodeContent.Content(typesSource,
    <.form(
      Input(Input.Props(`type` = "text", label = "Text", defaultValue = "Enter text")),
      Input(Input.Props(`type` = "email", label = "Email Address", defaultValue = "Enter text")),
      Input(Input.Props(`type` = "password", label = "Password", defaultValue = "secret")),
      Input(Input.Props(`type` = "file", label = "File", help = "[Optinal] Block level help text")),
      Input(Input.Props(`type` = "checkbox", label = "Checkbox", checked = true, readOnly = true)),
      Input(Input.Props(`type` = "radio", label = "Radio", checked = true, readOnly = true)),
      Input(Input.Props(`type` = "select", label = "Select", defaultValue = "select"),
        <.option(^.value := "select", "select"),
        <.option(^.value := "other", "...")),

      Input(Input.Props(`type` = "select", label = "Multiple Select", multiple = true),
        <.option(^.value := "select", "select"),
        <.option(^.value := "other", "...")),
      Input(Input.Props(`type` = "textarea", label = "Text Area", defaultValue = "textarea")),
      Input(Input.Props(`type` = "static", value = "Static Text")),
      Input(Input.Props(`type` = "submit", value = "Submit button"))
    ))

  val addonsSource =
    """
      |
    """.stripMargin

  def addonsContent = CodeContent.Content(addonsSource,
    <.form(
      Input(Input.Props(`type` = "text", addonBefore = "@": ReactNode)),
      Input(Input.Props(`type` = "text", addonAfter = ".00": ReactNode)),
      Input(Input.Props(`type` = "text", addonBefore = "$": ReactNode, addonAfter = ".00": ReactNode)),
      Input(Input.Props(`type` = "text", addonAfter = Glyphicon("music"))),
      Input(Input.Props(`type` = "text", buttonBefore = Button(Button.Props(), "Before"))),
      Input(Input.Props(`type` = "text", buttonAfter =
        DropdownButton(DropdownButton.Props(title = "Action": ReactNode),
          MenuItem.withKey(1)(MenuItem.Props(), "Item")
        )))
    )
  )

  val validationSource =
    """
      |
    """.stripMargin

  def validationContent = CodeContent.Content(validationSource,
    <.form(
      Input(Input.Props(`type` = "text", bsStyle = Styles.success, label = "Success", hasFeedback = true)),
      Input(Input.Props(`type` = "text", bsStyle = Styles.warning, label = "Warning", hasFeedback = true)),
      Input(Input.Props(`type` = "text", bsStyle = Styles.error, label = "Error", hasFeedback = true))
    )
  )

  val horizontalSource =
    """
      |
    """.stripMargin

  def horizontalContent = CodeContent.Content(horizontalSource,
    <.form(^.className := "form-horizontal",
      Input(Input.Props(`type` = "text", label = "Text", labelClassName = "col-xs-2", wrapperClassName = "col-xs-10")),
      Input(Input.Props(`type` = "textarea", label = "Textarea", labelClassName = "col-xs-2", wrapperClassName = "col-xs-10")),
      Input(Input.Props(`type` = "checkbox", label = "Checkbox", labelClassName = "col-xs-offset-2 col-xs-10", help = "offset is applied to wrapper"))
    )
  )

  val wrapperSource =
    """
      |
    """.stripMargin

  def wrapperContent = CodeContent.Content(wrapperSource,
    Input(Input.Props(label = "Input wrapper", help = "Use this when you need something other than the available input types.", wrapperClassName = "wrapper"),
      Row(
        Col(Col.Props(xs = 6),
          Input(Input.Props(`type` = "text", addClasses = "form-control"))),
        Col(Col.Props(xs = 6),
          Input(Input.Props(`type` = "text", addClasses = "form-control")))
      )
    )
  )

  val content = Section("inputs", "Input"
    , <.p("Renders an input in bootstrap wrappers. Supports label, help, text input add-ons, validation and use as wrapper. Use ",
      <.code("getValue()", " or ", <.code("getChecked()"), " to get the current state. The helper method getInputDOMNode() returns the internal input element."),
      exampleContent())
    , SubSection("inputs-types", "Types",
      <.p("Supports ", <.code("select"), <.code("textarea"), ", ", <.code("static"),
        " as well as standard HTML input types. ", <.code("getValue()"),
        " returns an array for multiple select."),
      typesContent())
    , SubSection("inputs-addons", "Add-ons",
      <.p("Use addonBefore and addonAfter for normal addons, buttonBefore and buttonAfter for button addons. Exotic configurations may require some css on your side."),
      addonsContent())
    , SubSection("inputs-validation", "Validation",
      <.p("Set bsStyle to one of success, warning or error. Add hasFeedback to show glyphicon. Glyphicon may need additional styling if there is an add-on or no label."),
      validationContent())
    , SubSection("inputs-horizontal", "Horizontal forms",
      <.p("Use ", <.code("labelClassName"), " and ", <.code("wrapperClassName"),
        " properties to add col classes manually. ", <.code("checkbox"), " and ", <.code("radio"),
        " types need special treatment because label wraps input."),
      horizontalContent())
    , SubSection("inputs-wrapper", "Use as a wrapper",
      <.p("If ", <.code("type"), " is not set, child element(s) will be rendered instead of an input element. ",
        <.code("getValue()"), " will not work when used this way."),
      wrapperContent())
  )
}
