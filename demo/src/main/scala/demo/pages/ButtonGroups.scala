package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._
import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 16/03/15.
 */
object ButtonGroups {
  val basicSource =
    """
      |ButtonGroup(
      |  Button("Left"),
      |  Button("Middle"),
      |  Button("Right"))
    """.stripMargin

  def basicContent = CodeContent.Content(basicSource,
    ButtonGroup(
      Button("Left"),
      Button("Middle"),
      Button("Right"))
  )

  val buttonToolbarSource =
    """
      |ButtonToolbar(
      |  ButtonGroup(
      |    Button("1"),
      |    Button("2"),
      |    Button("3"),
      |    Button("4")),
      |  ButtonGroup(
      |    Button("5"),
      |    Button("6"),
      |    Button("7")),
      |  ButtonGroup(
      |    Button("8"))
      |)
    """.stripMargin

  val buttonToolbarContent = CodeContent.Content(buttonToolbarSource,
    ButtonToolbar(
      ButtonGroup(
        Button("1"),
        Button("2"),
        Button("3"),
        Button("4")),
      ButtonGroup(
        Button("5"),
        Button("6"),
        Button("7")),
      ButtonGroup(
        Button("8")
      )
    )
  )

  val sizingSource =
    """
      |<.di(
      |  ButtonToolbar(
      |    ButtonGroup(ButtonGroup.Props(bsSize = Sizes.lg),
      |      Button("Left"),
      |      Button("Middle"),
      |      Button("Right"))),
      |  ButtonToolbar(
      |    ButtonGroup(
      |      Button("Left"),
      |      Button("Middle"),
      |      Button("Right"))),
      |  ButtonToolbar(
      |    ButtonGroup(ButtonGroup.Props(bsSize = Sizes.sm),
      |      Button("Left"),
      |      Button("Middle"),
      |      Button("Right"))),
      |  ButtonToolbar(
      |    ButtonGroup(ButtonGroup.Props(bsSize = Sizes.xs),
      |      Button("Left"),
      |      Button("Middle"),
      |      Button("Right")))
      |)
    """.stripMargin

  val sizingContent = CodeContent.Content(sizingSource,
    <.div(
      ButtonToolbar(
        ButtonGroup(ButtonGroup.ButtonGroup(bsSize = Sizes.lg),
          Button("Left"),
          Button("Middle"),
          Button("Right"))),
      ButtonToolbar(
        ButtonGroup(
          Button("Left"),
          Button("Middle"),
          Button("Right"))),
      ButtonToolbar(
        ButtonGroup(ButtonGroup.ButtonGroup(bsSize = Sizes.sm),
          Button("Left"),
          Button("Middle"),
          Button("Right"))),
      ButtonToolbar(
        ButtonGroup(ButtonGroup.ButtonGroup(bsSize = Sizes.xs),
          Button("Left"),
          Button("Middle"),
          Button("Right")))
    )
  )

  val nestingSource =
    """
      |ButtonGroup(
      |  Button("1"),
      |  Button("2"),
      |  DropdownButton(DropdownButton.Props(title = "Dropdown"),
      |    MenuItem(MenuItem.Props(eventKey = 1), "Dropdown link"),
      |    MenuItem(MenuItem.Props(eventKey = 2), "Dropdown link")
      |  )
      |)
    """.stripMargin

  val nestingContent = CodeContent.Content(nestingSource,
    ButtonGroup(
      Button("1"),
      Button("2"),
      DropdownButton(DropdownButton.DropdownButton(title = reactNodeInhabitableS("Dropdown")),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Dropdown link"),
        MenuItem(MenuItem.MenuItem(eventKey = "2"), "Dropdown link")
      )
    )
  )

  val verticalSource =
    """
      |ButtonGroup(ButtonGroup.Props(vertical = true),
      |  Button("Button"),
      |  Button("Button"),
      |  DropdownButton(DropdownButton.Props(title = reactNodeInhabitableS("Dropdown")),
      |    MenuItem(MenuItem.Props(eventKey = "1"), "Dropdown link"),
      |    MenuItem(MenuItem.Props(eventKey = "2"), "Dropdown link")),
      |  Button("Button"),
      |  Button("Button"),
      |  DropdownButton(DropdownButton.Props(title = reactNodeInhabitableS("Dropdown")),
      |    MenuItem(MenuItem.Props(eventKey = "1"), "Dropdown link"),
      |    MenuItem(MenuItem.Props(eventKey = "2"), "Dropdown link")),
      |  Button("Button"),
      |  Button("Button"),
      |  DropdownButton(DropdownButton.Props(title = reactNodeInhabitableS("Dropdown")),
      |    MenuItem(MenuItem.Props(eventKey = "1"), "Dropdown link"),
      |    MenuItem(MenuItem.Props(eventKey = "2"), "Dropdown link"))
      |)
    """.stripMargin

  val verticalContent = CodeContent.Content(verticalSource,
    ButtonGroup(ButtonGroup.ButtonGroup(vertical = true),
      Button("Button"),
      Button("Button"),
      DropdownButton(DropdownButton.DropdownButton(title = reactNodeInhabitableS("Dropdown")),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Dropdown link"),
        MenuItem(MenuItem.MenuItem(eventKey = "2"), "Dropdown link")),
      Button("Button"),
      Button("Button"),
      DropdownButton(DropdownButton.DropdownButton(title = reactNodeInhabitableS("Dropdown")),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Dropdown link"),
        MenuItem(MenuItem.MenuItem(eventKey = "2"), "Dropdown link")),
      Button("Button"),
      Button("Button"),
      DropdownButton(DropdownButton.DropdownButton(title = reactNodeInhabitableS("Dropdown")),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Dropdown link"),
        MenuItem(MenuItem.MenuItem(eventKey = "2"), "Dropdown link"))
    )
  )

  val justifiedSource =
    """
      |ButtonGroup(ButtonGroup.Props(justified = true),
      |  Button(Button.Props(href = "#"), "Left"),
      |  Button(Button.Props(href = "#"), "Middle"),
      |  DropdownButton(DropdownButton.Props(title = reactNodeInhabitableS("Dropdown")),
      |    MenuItem(MenuItem.Props(eventKey = "1"), "Dropdown link"),
      |    MenuItem(MenuItem.Props(eventKey = "2"), "Dropdown link"))
      |)
    """.stripMargin

  val justifiedContent = CodeContent.Content(justifiedSource,
    ButtonGroup(ButtonGroup.ButtonGroup(justified = true),
      Button(Button.Button(href = "#"), "Left"),
      Button(Button.Button(href = "#"), "Middle"),
      DropdownButton(DropdownButton.DropdownButton(title = reactNodeInhabitableS("Dropdown")),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Dropdown link"),
        MenuItem(MenuItem.MenuItem(eventKey = "2"), "Dropdown link"))
    )
  )

  val content = Section("btn-groups", <.span("Button groups ", <.small("ButtonGroup, ButtonToolbar")),
    <.h3("Group a series of buttons together on a single line with the button group."),
    SubSection("btn-groups-single", "Basic example",
      <.p("Wrap a series of ", <.code("<Button />"), "’s in a ", <.code("<ButtonGroup />"), "."),
      basicContent())
    , SubSection("btn-groups-toolbar", "Button toolbar",
      <.p("Combine sets of ", <.code("<ButtonGroup />"), "’s into a ", <.code("<ButtonToolbar />"), " for more complex components."),
      buttonToolbarContent())
    , SubSection("btn-groups-sizing", "Sizing",
      <.p("TODO: Not working yet. Instead of applying button sizing props to every button in a group, just add ", <.code("bsSize"), " prop to the ", <.code("<ButtonGroup />"), "."),
      sizingContent())
    , SubSection("btn-groups-nested", "Nesting",
      <.p("You can place other button types within the ", <.code("<ButtonGroup />"), " like ", <.code("<DropdownButton />"), "’s."),
      nestingContent())
    , SubSection("btn-groups-vertical", "Vertical variation",
      <.p("Make a set of buttons appear vertically stacked rather than horizontally.", <.strong(^.className := "text-danger",
        "Split button dropdowns are not supported here")),
      <.p("Just add vertical to the ", <.code("<ButtonGroup />"), "."),
      verticalContent())
    , SubSection("btn-groups-justified", "Justified button groups",
      <.p("Just add ", <.code("justified"), " to the ", <.code("<ButtonGroup />"), "."),
      justifiedContent())
  )

}
