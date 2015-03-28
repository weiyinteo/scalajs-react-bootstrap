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
object Navbars {

  val handleSelect = (selectedKey: Seq[UndefOr[String]]) => Console.println(s"selected $selectedKey")
  val exampleSource =
    """
      |handleSelect = (selectedKey: Seq[UndefOr[String]]) => Console.println(s"selected $selectedKey")
      |NavBar.NavBar(brand = "React-Bootstrap": ReactNode, componentClass = "nav")(
      |  Nav.Nav()(
      |    NavItem.NavItem(eventKey = "1", href = "#")("Link"),
      |    NavItem.NavItem(eventKey = "2", href = "#")("Link"),
      |    DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
      |      MenuItem.MenuItem(eventKey = "1")("Action"),
      |      MenuItem.MenuItem(eventKey = "2")("Action action"),
      |      MenuItem.MenuItem(eventKey = "3")("Something else here"),
      |      MenuItem.MenuItem(divider = true)(),
      |      MenuItem.MenuItem(eventKey = "4")("Separated link")
      |    )
      |  )
      |)
    """.stripMargin


  val exampleContent = CodeContent.Content(exampleSource,
    NavBar.NavBar(brand = "React-Bootstrap": ReactNode, componentClass = "nav")(
      Nav.Nav()(
        NavItem.NavItem(eventKey = "1", href = "#")("Link"),
        NavItem.NavItem(eventKey = "2", href = "#")("Link"),
        DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
          MenuItem.MenuItem(eventKey = "1")("Action"),
          MenuItem.MenuItem(eventKey = "2")("Action action"),
          MenuItem.MenuItem(eventKey = "3")("Something else here"),
          MenuItem.MenuItem(divider = true)(),
          MenuItem.MenuItem(eventKey = "4")("Separated link")
        )
      )
    )
  )

  val mobileSource =
    """
      |val handleSelect = (selectedKey: String) => Console.println(s"selected $selectedKey")
      |NavBar.NavBar(brand = "React-Bootstrap": ReactNode, inverse = true, toggleNavKey = "0")(
      |  Nav.Nav(right = true, eventKey = "0")(/*This is the eventKey referenced */
      |    NavItem.NavItem(eventKey = "1", href = "#")("Link"),
      |    NavItem.NavItem(eventKey = "2", href = "#")("Link"),
      |    DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
      |      MenuItem.MenuItem(eventKey = "1")("Action"),
      |      MenuItem.MenuItem(eventKey = "2")("Action action"),
      |      MenuItem.MenuItem(eventKey = "3")("Something else here"),
      |      MenuItem.MenuItem(divider = true)(),
      |      MenuItem.MenuItem(eventKey = "4")("Separated link")
      |    )
      |  )
      |)
    """.stripMargin

  val mobileContent = CodeContent.Content(mobileSource,
    NavBar.NavBar(brand = "React-Bootstrap": ReactNode, inverse = true, toggleNavKey = "0")(
      Nav.Nav(right = true, eventKey = "0")(/*This is the eventKey referenced */
        NavItem.NavItem(eventKey = "1", href = "#")("Link"),
        NavItem.NavItem(eventKey = "2", href = "#")("Link"),
        DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
          MenuItem.MenuItem(eventKey = "1")("Action"),
          MenuItem.MenuItem(eventKey = "2")("Action action"),
          MenuItem.MenuItem(eventKey = "3")("Something else here"),
          MenuItem.MenuItem(divider = true)(),
          MenuItem.MenuItem(eventKey = "4")("Separated link")
        )
      )
    )
  )
  val mobileMultipleSource =
    """
      |NavBar.NavBar(brand = "React-Bootstrap": ReactNode, toggleNavKey = "0")(
      |  CollapsableNav.CollapsableNav(eventKey = "0")(/*This is the eventKey referenced */
      |    Nav.Nav(navbar = true)(
      |      NavItem.NavItem(eventKey = "1", href = "#")("Link"),
      |      NavItem.NavItem(eventKey = "2", href = "#")("Link"),
      |      DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
      |        MenuItem.MenuItem(eventKey = "1")("Action"),
      |        MenuItem.MenuItem(eventKey = "2")("Action action"),
      |        MenuItem.MenuItem(eventKey = "3")("Something else here"),
      |        MenuItem.MenuItem(divider = true)(),
      |        MenuItem.MenuItem(eventKey = "4")("Separated link")
      |      )
      |    ),
      |    Nav.Nav(right = true, navbar = true)(
      |      NavItem.NavItem(eventKey = "1", href = "#")("Link Right"),
      |      NavItem.NavItem(eventKey = "2", href = "#")("Link Right")
      |    )
      |  )
      |)
    """.stripMargin

  val mobileMultipleContent = CodeContent.Content(mobileMultipleSource,
    NavBar.NavBar(brand = "React-Bootstrap": ReactNode, toggleNavKey = "0")(
      CollapsableNav.CollapsableNav(eventKey = "0")(/*This is the eventKey referenced */
        Nav.Nav(navbar = true)(
          NavItem.NavItem(eventKey = "1", href = "#")("Link"),
          NavItem.NavItem(eventKey = "2", href = "#")("Link"),
          DropdownButton.DropdownButton(eventKey = "3", title = "Dropdown": ReactNode)(
            MenuItem.MenuItem(eventKey = "1")("Action"),
            MenuItem.MenuItem(eventKey = "2")("Action action"),
            MenuItem.MenuItem(eventKey = "3")("Something else here"),
            MenuItem.MenuItem(divider = true)(),
            MenuItem.MenuItem(eventKey = "4")("Separated link")
          )
        ),
        Nav.Nav(right = true, navbar = true)(
          NavItem.NavItem(eventKey = "1", href = "#")("Link Right"),
          NavItem.NavItem(eventKey = "2", href = "#")("Link Right")
        )
      )
    )
  )


  val content = Section("navbars", <.span("Navbars ", <.small("Navbar, Nav, NavItem"))
    , SubSection("navbars-examples", "Example navbars",
      <.p("You can specify a brand by passing a renderable component or string in ", <.code("brand")),
      <.p("Navbars are by default accessible and will provide ", <.code("role=\"navigation\")", ".")),
      <.p("They also supports all the different Bootstrap classes as properties. Just camelCase the css class and remove navbar from it. For example ",
        <.code("navbar-fixed-top"), " becomes the property ", <.code("fixedTop"),
        ". The different properties are ", <.code("fixedTop"), ", ", <.code("fixedBottom"),
        ", ", <.code("staticTop"), ", ", <.code("inverse"), ", ", <.code("fluid"), "."),
      <.p("You can drag elements to the ", <.code("right"), " by specifying the right property on a nav group."),
      exampleContent())
    , SubSection("navbars-mobile", "Mobile Friendly",
      <.p("To have a mobile friendly Navbar, specify the property ", <.code("toggleNavKey"),
        " on the Navbar with a value corresponding to an ", <.code("eventKey"),
        " of one of his Nav children. This child will be the one collapsed."),
      <.p("By setting the property ", <.code("defaultNavExpanded=true"), " the Navbar will start expanded by default."),
      mobileContent())
    , SubSection("navbars-mobile-multiple", "Mobile Friendly (Multiple Nav Components)",
      <.p("To have a mobile friendly Navbar that handles multiple ", <.code("Nav"),
        " components use ", <.code("CollapsableNav"), ". The ", <.code("toggleNavKey"),
        " must still be set, however, the corresponding ", <.code("eventKey"),
        " must now be on the ", <.code("CollapsableNav"), " component."),
      mobileMultipleContent())
  )
}
