package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object ListGroups {

  val exampleSource =
    """
      |ListGroup(
      |  ListGroupItem("Item 1"),
      |  ListGroupItem("Item 2"),
      |  ListGroupItem("...")
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    ListGroup(
      ListGroupItem("Item 1"),
      ListGroupItem("Item 2"),
      ListGroupItem("...")
    )
  )

  val linkedSource =
    """
      |ListGroup(
      |  ListGroupItem.ListGroupItem(href = "#link1")("Link 1"),
      |  ListGroupItem.ListGroupItem(href = "#link2")("Link 2"),
      |  ListGroupItem.ListGroupItem(href = "#linkN")("...")
      |)
    """.stripMargin

  def linkedContent = CodeContent.Content(linkedSource,
    ListGroup(
      ListGroupItem.ListGroupItem(href = "#link1")("Link 1"),
      ListGroupItem.ListGroupItem(href = "#link2")("Link 2"),
      ListGroupItem.ListGroupItem(href = "#linkN")("...")
    )
  )

  def stylingStatusSource =
    """
      |ListGroup(
      |  ListGroupItem(ListGroupItem.Props(href = "#", active = true), "Link 1"),
      |  ListGroupItem(ListGroupItem.Props(href = "#"), "Link 2"),
      |  ListGroupItem(ListGroupItem.Props(href = "#", disabled = true), "Link 3")
      |)
    """.stripMargin

  def stylingStatusContent = CodeContent.Content(stylingStatusSource,
    ListGroup(
      ListGroupItem.ListGroupItem(href = "#", active = true)("Link 1"),
      ListGroupItem.ListGroupItem(href = "#")("Link 2"),
      ListGroupItem.ListGroupItem(href = "#", disabled = true)("Link 3")
    )
  )

  def stylingSource =
    """
      |ListGroup(
      |  ListGroupItem.ListGroupItem(bsStyle = Styles.success)("Success"),
      |  ListGroupItem.ListGroupItem(bsStyle = Styles.info)("Info"),
      |  ListGroupItem.ListGroupItem(bsStyle = Styles.warning)("Warning"),
      |  ListGroupItem.ListGroupItem(bsStyle = Styles.danger)("Danger")
      |)
    """.stripMargin

  def stylingContent = CodeContent.Content(stylingSource,
    ListGroup(
      ListGroupItem.ListGroupItem(bsStyle = Styles.success)("Success"),
      ListGroupItem.ListGroupItem(bsStyle = Styles.info)("Info"),
      ListGroupItem.ListGroupItem(bsStyle = Styles.warning)("Warning"),
      ListGroupItem.ListGroupItem(bsStyle = Styles.danger)("Danger")
    )
  )

  def headerSource =
    """
      |ListGroup(
      |  ListGroupItem.ListGroupItem(header = "Heading 1": ReactNode)("Some body text"),
      |  ListGroupItem.ListGroupItem(header = "Heading 2": ReactNode, href = "#")("Linked item"),
      |  ListGroupItem.ListGroupItem(header = "Heading 3": ReactNode, bsStyle = Styles.danger)("Danger styling")
      |)
    """.stripMargin

  def headerContent = CodeContent.Content(headerSource,
    ListGroup(
      ListGroupItem.ListGroupItem(header = "Heading 1": ReactNode)("Some body text"),
      ListGroupItem.ListGroupItem(header = "Heading 2": ReactNode, href = "#")("Linked item"),
      ListGroupItem.ListGroupItem(header = "Heading 3": ReactNode, bsStyle = Styles.danger)("Danger styling")
    )
  )

  val content = Section("listgroup", <.span("ListGroup ", <.small(" ListGroup, ListGroupItem"))
    , <.p("Quick previous and next links.")
    , SubSection("listgroup-example", "Default",
      <.p("Centers by default."),
      exampleContent())
    , SubSection("listgroup-linked", "Linked",
      <.p("Set the ", <.code("href"), " or ", <.code("onClick"), " prop on ListGroupItem, to create a linked or clickable element."),
      linkedContent())
    , SubSection("listgroup-styling", "Styling",
      <.p("Set the ", <.code("active"), " or ", <.code("disabled"), " prop to true to mark or disable the item."),
      stylingStatusContent(),
      <.p("Set the ", <.code("bsStyle"), " prop to style the item."),
      stylingContent())
    , SubSection("listgroup-header", "With header",
      <.p("Set the ", <.code("header"), " prop to create a structured item, with a heading and a body area."),
      headerContent())
  )
}
