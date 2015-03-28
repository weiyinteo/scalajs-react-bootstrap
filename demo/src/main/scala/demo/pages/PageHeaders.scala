package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object PageHeaders {

  val exampleSource =
    """
      |PageHeader("Example page header ", <.small("Subtext"), " for header")
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    PageHeader("Example page header ", <.small("Subtext for header"))
  )

  val content = Section("page-header", <.span("Page Header")
    , <.p("A simple shell for an ", <.code("h1"),
      " to appropriately space out and segment sections of content on a page. It can utilize the ",
      <.code("h1"), "’s default ",
      <.code("small"), " element, as well as most other components (with additional styles).")
    , SubSection("page-header-example", "Example",
      exampleContent())
  )
}
