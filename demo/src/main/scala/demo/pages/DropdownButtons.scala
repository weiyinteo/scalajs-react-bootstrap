package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 16/03/15.
 */
object DropdownButtons {
  val singleButtonSource =
    """
      |def renderButton(style: Styles.Value, key: Int): ReactNode = {
      |      DropdownButton.withKey(key)(DropdownButton.Props(bsStyle = style, title = style.toString: ReactNode),
      |        MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |        , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |        , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |        , MenuItem(MenuItem.Props(divider = true))
      |        , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |      )
      |}
      |ButtonToolbar(
      |  Seq(Styles.default, Styles.primary, Styles.success, Styles.info, Styles.warning, Styles.danger, Styles.link).zipWithIndex.map {
      |      case (style, index) => renderButton(style, index)
      |  })
    """.stripMargin

  val singleButtonContent = CodeContent.Content(singleButtonSource, {
    def renderButton(style: Styles.Value, key: Int): ReactNode = {
      DropdownButton.withKey(key)(DropdownButton.DropdownButton(bsStyle = style, title = style.toString: ReactNode),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
        , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
        , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
        , MenuItem(MenuItem.MenuItem(divider = true))
        , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
      )
    }
    ButtonToolbar(
      Seq(Styles.default, Styles.primary, Styles.success, Styles.info, Styles.warning, Styles.danger, Styles.link).zipWithIndex.map {
        case (style, index) => renderButton(style, index)
      }
    )
  })

  val splitButtonSource =
    """
      |def renderButton(style: Styles.Value, key: Int): ReactNode = {
      |      SplitButton.withKey(key)(SplitButton.Props(bsStyle = style, title = style.toString: ReactNode),
      |        MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |        , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |        , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |        , MenuItem(MenuItem.Props(divider = true))
      |        , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |      )
      |    }
      |
      |    ButtonToolbar(
      |      Seq(Styles.default, Styles.primary, Styles.success, Styles.info, Styles.warning, Styles.danger, Styles.link).zipWithIndex.map {
      |        case (style, index) => renderButton(style, index)
      |      }
      |    )
    """.stripMargin

  val splitButtonContent = CodeContent.Content(splitButtonSource, {
    def renderButton(style: Styles.Value, key: Int): ReactNode = {
      SplitButton.withKey(key)(SplitButton.SplitButton(bsStyle = style, title = style.toString: ReactNode),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
        , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
        , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
        , MenuItem(MenuItem.MenuItem(divider = true))
        , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
      )
    }

    ButtonToolbar(
      Seq(Styles.default, Styles.primary, Styles.success, Styles.info, Styles.warning, Styles.danger, Styles.link).zipWithIndex.map {
        case (style, index) => renderButton(style, index)
      }
    )
  })

  val sizingSource =
    """
      |<.div(
      |      ButtonToolbar(
      |        DropdownButton(DropdownButton.Props(bsSize = Sizes.lg, title = "Large Button": ReactNode),
      |          MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |          , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |          , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |          , MenuItem(MenuItem.Props(divider = true))
      |          , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |        )
      |      )
      |      , ButtonToolbar(
      |        DropdownButton(DropdownButton.Props(bsSize = Sizes.sm, title = "Small Button": ReactNode),
      |          MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |          , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |          , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |          , MenuItem(MenuItem.Props(divider = true))
      |          , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |        )
      |      )
      |      , ButtonToolbar(
      |        DropdownButton(DropdownButton.Props(bsSize = Sizes.xs, title = "Extra Small Button": ReactNode),
      |          MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |          , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |          , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |          , MenuItem(MenuItem.Props(divider = true))
      |          , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |        )
      |      )
      |    )
    """.stripMargin

  val sizingContent = CodeContent.Content(sizingSource,
    <.div(
      ButtonToolbar(
        DropdownButton(DropdownButton.DropdownButton(bsSize = Sizes.lg, title = "Large Button": ReactNode),
          MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
          , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
          , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
          , MenuItem(MenuItem.MenuItem(divider = true))
          , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
        )
      )
      , ButtonToolbar(
        DropdownButton(DropdownButton.DropdownButton(bsSize = Sizes.sm, title = "Small Button": ReactNode),
          MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
          , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
          , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
          , MenuItem(MenuItem.MenuItem(divider = true))
          , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
        )
      )
      , ButtonToolbar(
        DropdownButton(DropdownButton.DropdownButton(bsSize = Sizes.xs, title = "Extra Small Button": ReactNode),
          MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
          , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
          , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
          , MenuItem(MenuItem.MenuItem(divider = true))
          , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
        )
      )
    )
  )

  val noCaretSource =
    """
      |ButtonToolbar(
      |      DropdownButton(DropdownButton.Props(title = "No caret": ReactNode, noCaret = true),
      |        MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |        , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |        , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |        , MenuItem(MenuItem.Props(divider = true))
      |        , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |      )
      |)
    """.stripMargin

  val noCaretContent = CodeContent.Content(noCaretSource,
    ButtonToolbar(
      DropdownButton(DropdownButton.DropdownButton(title = "No caret": ReactNode, noCaret = true),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
        , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
        , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
        , MenuItem(MenuItem.MenuItem(divider = true))
        , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
      )
    )
  )

  val dropupSource =
    """
      | <.div(
      |      ButtonToolbar(
      |        SplitButton(SplitButton.Props(title = "dropup": ReactNode, dropup = true),
      |          MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |          , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |          , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |          , MenuItem(MenuItem.Props(divider = true))
      |          , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |        )
      |      )
      |      , ButtonToolbar(
      |        SplitButton(SplitButton.Props(bsStyle = Styles.primary, title = "Right dropup": ReactNode, dropup = true, pullRight = true),
      |          MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |          , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |          , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |          , MenuItem(MenuItem.Props(divider = true))
      |          , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |        )
      |      )
      |)
    """.stripMargin

  val dropupContent = CodeContent.Content(dropupSource,
    <.div(
      ButtonToolbar(
        SplitButton(SplitButton.SplitButton(title = "dropup": ReactNode, dropup = true),
          MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
          , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
          , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
          , MenuItem(MenuItem.MenuItem(divider = true))
          , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
        )
      )
      , ButtonToolbar(
        SplitButton(SplitButton.SplitButton(bsStyle = Styles.primary, title = "Right dropup": ReactNode, dropup = true, pullRight = true),
          MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
          , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
          , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
          , MenuItem(MenuItem.MenuItem(divider = true))
          , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
        )
      )
    )
  )

  val dropdownRightSource =
    """
      |ButtonToolbar(
      |      SplitButton(SplitButton.Props(title = "dropdown right": ReactNode, pullRight = true),
      |        MenuItem(MenuItem.Props(eventKey = "1"), "Action")
      |        , MenuItem(MenuItem.Props(eventKey = "2"), "Another action")
      |        , MenuItem(MenuItem.Props(eventKey = "3"), "Something else here")
      |        , MenuItem(MenuItem.Props(divider = true))
      |        , MenuItem(MenuItem.Props(eventKey = "4"), "Separated link")
      |      )
      |)
    """.stripMargin

  val dropdownRightContent = CodeContent.Content(dropdownRightSource,
    ButtonToolbar(
      SplitButton(SplitButton.SplitButton(title = "dropdown right": ReactNode, pullRight = true),
        MenuItem(MenuItem.MenuItem(eventKey = "1"), "Action")
        , MenuItem(MenuItem.MenuItem(eventKey = "2"), "Another action")
        , MenuItem(MenuItem.MenuItem(eventKey = "3"), "Something else here")
        , MenuItem(MenuItem.MenuItem(divider = true))
        , MenuItem(MenuItem.MenuItem(eventKey = "4"), "Separated link")
      )
    )
  )

  val content = Section("btn-dropdowns", <.span("Button dropdowns"),
    <.h3("Use ", <.code("<DropdownButton />"), " or ", <.code("<SplitButton />"), " components to display a button with a dropdown menu."),
    SubSection("btn-dropdowns-single", "Single button dropdowns",
      <.p("Create a dropdown button with the ", <.code("<DropdownButton />"), " component."),
      singleButtonContent())
    , SubSection("btn-dropdowns-split", "Split button dropdowns",
      <.p("Similarly, create split button dropdowns with the ", <.code("<SplitButton />"), " component."),
      splitButtonContent())
    , SubSection("btn-dropdowns-sizing", "Sizing",
      <.p("Button dropdowns work with buttons of all sizes."),
      sizingContent())
    , SubSection("btn-dropdowns-nocaret", "No caret variation",
      <.p("Remove the caret using the ", <.code("noCaret"), " prop."),
      noCaretContent())
    , SubSection("btn-dropdowns-dropup", "Dropup variation",
      <.p("Trigger dropdown menus that site above the button by adding the ", <.code("dropup"), " prop."),
      dropupContent())
    , SubSection("btn-dropdowns-right", "Dropup variation",
      <.p("Trigger dropdown menus that align to the right of the button using the ", <.code("pullRight"), " prop."),
      dropdownRightContent())
  )

}
