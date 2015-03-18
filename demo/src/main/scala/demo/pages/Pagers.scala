package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Pagers {

  val exampleSource =
    """
      |Pager.Pager()(
      |  PageItem.PageItem(href = "#")("Previous"),
      |  PageItem.PageItem(href = "#")("Next")
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Pager.Pager()(
      PageItem.PageItem(href = "#")("Previous"),
      PageItem.PageItem(href = "#")("Next")
    )
  )

  val alignedSource =
    """
      |Pager.Pager()(
      |  PageItem.PageItem(previous = true, href = "#")("Previous"),
      |  PageItem.PageItem(next = true, href = "#")("Next")
      |)
    """.stripMargin

  def alignedContent = CodeContent.Content(alignedSource,
    Pager.Pager()(
      PageItem.PageItem(previous = true, href = "#")("Previous"),
      PageItem.PageItem(next = true, href = "#")("Next")
    )
  )

  val disabledSource =
    """
      |Pager.Pager()(
      |  PageItem.PageItem(previous = true, href = "#")("Previous"),
      |  PageItem.PageItem(next = true, disabled = true, href = "#")("Next")
      |)
    """.stripMargin

  def disabledContent = CodeContent.Content(disabledSource,
    Pager.Pager()(
      PageItem.PageItem(previous = true, href = "#")("Previous"),
      PageItem.PageItem(next = true, disabled = true, href = "#")("Next")
    )
  )

  val content = Section("pagers", <.span("Pager ", <.small("Pager, PageItem"))
    , <.p("quick previous and next links")
    , SubSection("grids-default", "Default",
      <.p("centered by default"),
      exampleContent())
    , SubSection("grids-aligned", "Aligned",
      <.p("Set the ", <.code("previous"), " or ", <.code("next"), " props to ", <.code("true"), " to align left or right"),
      alignedContent())
    , SubSection("grids-disabled", "Disabled",
      <.p("Set the ", <.code("disabled"), " props to ", <.code("true"), " to disable the link"),
      disabledContent())
  )
}
