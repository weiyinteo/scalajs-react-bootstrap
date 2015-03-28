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
      |<.div(
      |  ButtonToolbar(
      |    ButtonGroup(
      |      Button(Glyphicon.Glyphicon(glyph = "align-left")()),
      |      Button(Glyphicon.Glyphicon(glyph = "align-center")()),
      |      Button(Glyphicon.Glyphicon(glyph = "align-right")()),
      |      Button(Glyphicon.Glyphicon(glyph = "align-justify")())
      |    )
      |  ),
      |  ButtonToolbar(
      |    ButtonGroup(
      |      Button.Button(bsSize = Sizes.lg)(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
      |      Button.Button()(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
      |      Button.Button(bsSize = Sizes.sm)(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
      |      Button.Button(bsSize = Sizes.xs)(Glyphicon.Glyphicon(glyph = "star")(), "Star")
      |    )
      |  )
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(defaultSource,
    <.div(
      ButtonToolbar(
        ButtonGroup(
          Button(Glyphicon.Glyphicon(glyph = "align-left")()),
          Button(Glyphicon.Glyphicon(glyph = "align-center")()),
          Button(Glyphicon.Glyphicon(glyph = "align-right")()),
          Button(Glyphicon.Glyphicon(glyph = "align-justify")())
        )
      ),
      ButtonToolbar(
        ButtonGroup(
          Button.Button(bsSize = Sizes.lg)(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
          Button.Button()(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
          Button.Button(bsSize = Sizes.sm)(Glyphicon.Glyphicon(glyph = "star")(), "Star"),
          Button.Button(bsSize = Sizes.xs)(Glyphicon.Glyphicon(glyph = "star")(), "Star")
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
