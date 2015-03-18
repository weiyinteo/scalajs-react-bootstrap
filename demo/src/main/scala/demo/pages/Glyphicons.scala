package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Glyphicons {

  val defaultSource =
    """
      |PageHeader("Example page header ", <.small("Subtext"), " for header")
    """.stripMargin

  def exampleContent = CodeContent.Content(defaultSource,
    <.div(
      ButtonToolbar(
        ButtonGroup(
          Button(Glyphicon(Glyphicon.Props(glyph = "align-left"))),
          Button(Glyphicon(Glyphicon.Props(glyph = "align-center"))),
          Button(Glyphicon(Glyphicon.Props(glyph = "align-right"))),
          Button(Glyphicon(Glyphicon.Props(glyph = "align-justify")))
        )
      ),
      ButtonToolbar(
        ButtonGroup(
          Button(Button.Props(bsSize = Sizes.lg), Glyphicon(Glyphicon.Props(glyph = "star")), "Star"),
          Button(Glyphicon(Glyphicon.Props(glyph = "star")), "Star"),
          Button(Button.Props(bsSize = Sizes.sm), Glyphicon(Glyphicon.Props(glyph = "star")), "Star"),
          Button(Button.Props(bsSize = Sizes.xs), Glyphicon(Glyphicon.Props(glyph = "star")), "Star")
        )
      )
    )
  )

  val content = Section("glyphicons", "Glyphicons"
    , <.p("Use them in buttons, button groups for a toolbar, navigation, or prepended form inputs.")
    , SubSection("glyphicons-example", "Example",
      exampleContent())
  )
}
