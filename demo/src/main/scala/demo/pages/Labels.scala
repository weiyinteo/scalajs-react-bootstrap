package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Labels {

  val exampleSource =
    """
      |<.div(
      |  <.h1("Label ", Label("New")),
      |  <.h2("Label ", Label("New")),
      |  <.h3("Label ", Label("New")),
      |  <.h4("Label ", Label("New")),
      |  <.h5("Label ", Label("New")),
      |  <.p("Label ", Label("New"))
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    <.div(
      <.h1("Label ", Label("New")),
      <.h2("Label ", Label("New")),
      <.h3("Label ", Label("New")),
      <.h4("Label ", Label("New")),
      <.h5("Label ", Label("New")),
      <.p("Label ", Label("New"))
    )
  )

  def variationSource =
    """
      |<.div(
      |  Label.Label(bsStyle = Styles.default)("Default"),
      |  Label.Label(bsStyle = Styles.primary)("Primary"),
      |  Label.Label(bsStyle = Styles.success)("Success"),
      |  Label.Label(bsStyle = Styles.info)("Info"),
      |  Label.Label(bsStyle = Styles.warning)("Warning")
      |)
    """.stripMargin

  def variationContent = CodeContent.Content(variationSource,
    <.div(
      Label.Label(bsStyle = Styles.default)("Default"),
      Label.Label(bsStyle = Styles.primary)("Primary"),
      Label.Label(bsStyle = Styles.success)("Success"),
      Label.Label(bsStyle = Styles.info)("Info"),
      Label.Label(bsStyle = Styles.warning)("Warning")
    )
  )

  val content = Section("labels", <.span("Labels")
    , SubSection("labels-example", "Example",
      <.p("Create a ", <.code("<Label>label</Label>"), " show highlight information"),
      exampleContent())
    , SubSection("labels-variations", "Available variations",
      <.p("Add any of the below mentioned modifier classes to change the appearance of a label."),
      variationContent())
  )
}
