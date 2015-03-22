package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Jumbotrons {

  val exampleSource =
    """
      |Jumbotron(
      |  <.h1("Hello, world!"),
      |  <.p("This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information."),
      |  <.p(Button(Button.Props(bsStyle = Styles.primary), "Learn more"))
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Jumbotron(
      <.h1("Hello, world!"),
      <.p("This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information."),
      <.p(Button(Button.Button(bsStyle = Styles.primary), "Learn more"))
    )
  )

  val content = Section("jumbotron", <.span("Jumbotron")
    , <.p("A lightweight, flexible component that can optionally extend the entire viewport to showcase key content on your site.")
    , SubSection("jumbotron-example", "Example",
      exampleContent())
  )
}
