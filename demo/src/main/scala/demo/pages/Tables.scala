package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Tables {

  val exampleSource =
    """
      |Table.Table(striped = true, bordered = true, condensed = true, hover = true)(
      |  <.thead(
      |    <.tr(
      |      <.th("#"),
      |      <.th("First Name"),
      |      <.th("Last Name"),
      |      <.th("User Name")
      |    )
      |  ),
      |  <.tbody(
      |    <.tr(
      |      <.td(1),
      |      <.td("Mark"),
      |      <.td("Otto"),
      |      <.td("@mdo")
      |    ),
      |    <.tr(
      |      <.td(2),
      |      <.td("Jacob"),
      |      <.td("Thornton"),
      |      <.td("@fat")
      |    ),
      |    <.tr(
      |      <.td(3),
      |      <.td(^.colSpan := 2, "Larry the Bird"),
      |      <.td("@twitter")
      |    )
      |  )
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Table.Table(striped = true, bordered = true, condensed = true, hover = true)(
      <.thead(
        <.tr(
          <.th("#"),
          <.th("First Name"),
          <.th("Last Name"),
          <.th("User Name")
        )
      ),
      <.tbody(
        <.tr(
          <.td(1),
          <.td("Mark"),
          <.td("Otto"),
          <.td("@mdo")
        ),
        <.tr(
          <.td(2),
          <.td("Jacob"),
          <.td("Thornton"),
          <.td("@fat")
        ),
        <.tr(
          <.td(3),
          <.td(^.colSpan := 2, "Larry the Bird"),
          <.td("@twitter")
        )
      )
    )
  )

  val responsiveSource =
    """
      |Table.Table(responsive = true)(
      |  <.thead(
      |    <.tr(
      |      <.th("#"),
      |      <.th("Table heading"),
      |      <.th("Table heading"),
      |      <.th("Table heading"),
      |      <.th("Table heading"),
      |      <.th("Table heading"),
      |      <.th("Table heading")
      |    )
      |  ),
      |  <.tbody(
      |    <.tr(
      |      <.td(1),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell")
      |    ),
      |    <.tr(
      |      <.td(2),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell")
      |    ),
      |    <.tr(
      |      <.td(3),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell"),
      |      <.td("Table cell")
      |    )
      |  )
      |)
    """.stripMargin

  def responsiveContent = CodeContent.Content(responsiveSource,
    Table.Table(responsive = true)(
      <.thead(
        <.tr(
          <.th("#"),
          <.th("Table heading"),
          <.th("Table heading"),
          <.th("Table heading"),
          <.th("Table heading"),
          <.th("Table heading"),
          <.th("Table heading")
        )
      ),
      <.tbody(
        <.tr(
          <.td(1),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell")
        ),
        <.tr(
          <.td(2),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell")
        ),
        <.tr(
          <.td(3),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell"),
          <.td("Table cell")
        )
      )
    )
  )

  val content = Section("tables", "Tables"
    , SubSection("tables-default", "Default Wells",
      <.p("Use the ", <.code("striped"), ", ", <.code("bordered"), ", ", <.code("condensed"),
        " and ", <.code("hover"), " props to customise the table."),
      exampleContent())
    , SubSection("tables-responsive", "Responsive",
      <.p("Add ", <.code("responsive"), " prop to make them scroll horizontally up to small devices (under 768px). When viewing on anything larger than 768px wide, you will not see any difference in these tables."),
      responsiveContent())
  )
}
