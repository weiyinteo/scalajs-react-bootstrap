package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Badges {

  val exampleSource =
    """
      |<.p("Badges ", Badge(42))
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    <.p("Badges ", Badge(42))
  )

  val content = Section("badges", <.span("Badges")
    , <.p("Easily highlight new or unread items by adding a ", <.code("Badge"), " to links, Bootstrap navs, and more")
    , SubSection("badges-example", "Example",
      exampleContent())
  )
}
