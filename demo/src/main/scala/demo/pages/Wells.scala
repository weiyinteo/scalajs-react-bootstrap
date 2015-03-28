package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Wells {

  val defaultSource =
    """
      |Well("Look I'm in a well!")
    """.stripMargin

  def defaultContent = CodeContent.Content(defaultSource,
    Well("Look I'm in a well!")
  )

  val optionalSource =
    """
      |<.div(
      |  Well.Props(bsSize = Sizes.lg)("Look I'm in a well!"),
      |  Well.Props(bsSize = Sizes.sm)("Look I'm in a well!")
      |)
    """.stripMargin

  def optionalContent = CodeContent.Content(optionalSource,
    <.div(
      Well.Well(bsSize = Sizes.lg)("Look I'm in a well!"),
      Well.Well(bsSize = Sizes.sm)("Look I'm in a well!")
    )
  )

  val content = Section("wells", "Wells"
    , <.p("Use the well as a simple effect on an element to give it an inset effect.")
    , SubSection("wells-default", "Default Wells",
      defaultContent())
    , SubSection("wells-optional", "Optional classes",
      <.p("Control padding and rounded corners with two optional modifier classes."),
      optionalContent())
  )
}
