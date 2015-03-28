package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 17/03/15.
 */
object Navs {

  val exampleSource =
    """
      |val handleSelect = (selectedKey: String) => Console.println(s"selected $selectedKey")
      |Nav(Nav.Props(bsStyle = Styles.pills, activeKey = "1", onSelect = handleSelect),
      |      NavItem(NavItem.Props(eventKey = "1", href = "/home"), "NavItem 1 content"),
      |      NavItem(NavItem.Props(eventKey = "2", title = "Item"), "NavItem 1 content"),
      |      NavItem(NavItem.Props(eventKey = "3", disabled = true), "NavItem 3 content")
      |)
    """.stripMargin

  val handleSelect = (selectedKey: Seq[UndefOr[String]]) => Console.println(s"selected $selectedKey")

  val exampleContent = CodeContent.Content(exampleSource, {
    Nav.Nav(bsStyle = Styles.pills, activeKey = "1", onSelect = handleSelect)(
      NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
    )
  }
  )

  val dropdownSource =
    """
      |val handleSelect = (selectedKey: String) => Console.println(s"selected $selectedKey")
      |Nav.Nav(bsStyle = Styles.tabs, activeKey = "1", onSelect = handleSelect)(
      |  NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      |  NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      |  NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content"),
      |  DropdownButton.DropdownButton(eventKey = "4", title = "Dropdown": ReactNode, navItem = true)(
      |    MenuItem.MenuItem(eventKey = "4.1")("Action"),
      |    MenuItem.MenuItem(eventKey = "4.2")("Another Action"),
      |    MenuItem.MenuItem(eventKey = "4.3")("Something else here"),
      |    MenuItem.MenuItem(divider = true)(),
      |    MenuItem.MenuItem(eventKey = "4.4")("Separated link"))
      |)
    """.stripMargin

  val dropdownContent = CodeContent.Content(dropdownSource,
    Nav.Nav(bsStyle = Styles.tabs, activeKey = "1", onSelect = handleSelect)(
      NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content"),
      DropdownButton.DropdownButton(eventKey = "4", title = "Dropdown": ReactNode, navItem = true)(
        MenuItem.MenuItem(eventKey = "4.1")("Action"),
        MenuItem.MenuItem(eventKey = "4.2")("Another Action"),
        MenuItem.MenuItem(eventKey = "4.3")("Something else here"),
        MenuItem.MenuItem(divider = true)(),
        MenuItem.MenuItem(eventKey = "4.4")("Separated link"))
    )
  )
  val stackedSource =
    """
      |val handleSelect = (selectedKey: String) => Console.println(s"selected $selectedKey")
      |Nav.Nav(bsStyle = Styles.pills, stacked = true, activeKey = "1", onSelect = handleSelect)(
      |  NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      |  NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      |  NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
      |)
    """.stripMargin

  val stackedContent = CodeContent.Content(stackedSource,
    Nav.Nav(bsStyle = Styles.pills, stacked = true, activeKey = "1", onSelect = handleSelect)(
      NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
    )
  )

  val justifiedSource =
    """
      |<.div(
      |  Nav.Nav(bsStyle = Styles.tabs, justified = true, activeKey = "1", onSelect = handleSelect)(
      |    NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      |    NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      |    NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
      |  ),
      |  <.br(),
      |  Nav.Nav(bsStyle = Styles.pills, justified = true, activeKey = "1", onSelect = handleSelect)(
      |    NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
      |    NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
      |    NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
      |  )
      |)
    """.stripMargin

  val justifiedContent = CodeContent.Content(justifiedSource,
    <.div(
      Nav.Nav(bsStyle = Styles.tabs, justified = true, activeKey = "1", onSelect = handleSelect)(
        NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
        NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
        NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
      ),
      <.br(),
      Nav.Nav(bsStyle = Styles.pills, justified = true, activeKey = "1", onSelect = handleSelect)(
        NavItem.NavItem(eventKey = "1", href = "/home")("NavItem 1 content"),
        NavItem.NavItem(eventKey = "2", title = "Item")("NavItem 1 content"),
        NavItem.NavItem(eventKey = "3", disabled = true)("NavItem 3 content")
      )
    )
  )

  val content = Section("navs", <.span("Navs ", <.small("Nav, NavItem"))
    , SubSection("navs-examples", "Example navs",
      <.p("Navs come in two styles, ", <.code("pills"), " and ", <.code("tabs"), ". Disable a tab by adding disabled"),
      exampleContent())
    , SubSection("navs-dropdown", "Dropdown",
      <.p("Add dropdowns using the ", <.code("DropdownButton"), " component. Just make sure to set ", <.code("navItem"), " property to true."),
      dropdownContent())
    , SubSection("navs-stacked", "Stacked",
      <.p("They can also be ", <.code("stacked"), " vertically"),
      stackedContent())
    , SubSection("navs-justified", "Justified",
      <.p("They can also be ", <.code("justified"), "  to take the full width of their parent."),
      justifiedContent())
  )
}
