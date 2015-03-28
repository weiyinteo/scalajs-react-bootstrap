package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Tabs {

  val exampleSource =
    """
      |TabbedArea.TabbedArea(defaultActiveKey = "2")(
      |  TabPane.TabPane(eventKey = "1", tab = "Tab 1")("TabPane 1 content"),
      |  TabPane.TabPane(eventKey = "2", tab = "Tab 2")("TabPane 2 content"))
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource, exampleClasses = "bs-example-tabs", el =
    TabbedArea.TabbedArea(defaultActiveKey = "2")(
      TabPane.TabPane(eventKey = "1", tab = "Tab 1")("TabPane 1 content"),
      TabPane.TabPane(eventKey = "2", tab = "Tab 2")("TabPane 2 content"))
  )

  val noanimationSource =
    """
      |TabbedArea.TabbedArea(defaultActiveKey = "2", animation = false)(
      |  TabPane.TabPane(eventKey = "1", tab = "Tab 1")("TabPane 1 content"),
      |  TabPane.TabPane(eventKey = "2", tab = "Tab 2")("TabPane 2 content"))
    """.stripMargin

  def noanimationContent = CodeContent.Content(noanimationSource, exampleClasses = "bs-noanimation-tabs", el =
    TabbedArea.TabbedArea(defaultActiveKey = "2", animation = false)(
      TabPane.TabPane(eventKey = "1", tab = "Tab 1")("TabPane 1 content"),
      TabPane.TabPane(eventKey = "2", tab = "Tab 2")("TabPane 2 content"))
  )


  val content = Section("tabs", <.span("Togglable tabs ", <.small("TabbedArea, TabPane"))
    , SubSection("grids-example", "Example tabs",
      <.p("Add quick, dynamic tab functionality to transition through panes of local content, even via dropdown menus."),
      <.h3("Uncontrolled"),
      <.p("Allow the component to control its state"),
      exampleContent(),
      <.h3("No Animation"),
      <.p("Set the ", <.code("animation"), " prop to ", <.code("false")),
      noanimationContent()
    )
  )
}
