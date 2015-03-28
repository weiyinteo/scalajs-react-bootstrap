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
object Panels {

  val basicSource =
    """
      |Panel("Basic panel example")
    """.stripMargin

  val basicContent = CodeContent.Content(basicSource,
    Panel("Basic panel example")
  )

  val headerSource =
    """
      |<.div(
      |  Panel.Panel(header = "Panel heading without title": ReactNode)("Panel content"),
      |  Panel.Panel(header = panelTitle)("Panel content"))
    """.stripMargin

  val panelTitle: ReactNode = <.h3("Panel title")
  val headerContent = CodeContent.Content(headerSource,
    <.div(
      Panel.Panel(header = "Panel heading without title": ReactNode)("Panel content"),
      Panel.Panel(header = panelTitle)("Panel content")
    )
  )
  val footerSource =
    """
      |<.div(
      |  Panel.Panel(footer = "Panel footer")("Panel content"))
    """.stripMargin

  val footerContent = CodeContent.Content(footerSource,
    Panel.Panel(footer = "Panel footer")("Panel content")
  )

  val contextualSource =
    """
      |<.div(
      |  Panel.Panel(header = panelTitle)("Panel content"),
      |  Panel.Panel(header = panelTitle, bsStyle = Styles.primary)("Panel content"),
      |  Panel.Panel(header = panelTitle, bsStyle = Styles.success)("Panel content"),
      |  Panel.Panel(header = panelTitle, bsStyle = Styles.info)("Panel content"),
      |  Panel.Panel(header = panelTitle, bsStyle = Styles.warning)("Panel content"),
      |  Panel.Panel(header = panelTitle, bsStyle = Styles.danger)("Panel content"))
    """.stripMargin

  val contextualContent = CodeContent.Content(contextualSource,
    <.div(
      Panel.Panel(header = panelTitle)("Panel content"),
      Panel.Panel(header = panelTitle, bsStyle = Styles.primary)("Panel content"),
      Panel.Panel(header = panelTitle, bsStyle = Styles.success)("Panel content"),
      Panel.Panel(header = panelTitle, bsStyle = Styles.info)("Panel content"),
      Panel.Panel(header = panelTitle, bsStyle = Styles.warning)("Panel content"),
      Panel.Panel(header = panelTitle, bsStyle = Styles.danger)("Panel content")
    )
  )

  val tablesSource =
    """
      |Panel.Panel(collapsable = true, defaultExpanded = true, header = "Panel heading": ReactNode)(
      |  ListGroup.ListGroup(fill = true)(
      |    ListGroupItem("Item 1"),
      |    ListGroupItem("Item 2"),
      |    ListGroupItem(<.span(^.dangerouslySetInnerHtml("&hellip;"))),
      |    "Some more panel content here"
      |  )
      |)
    """.stripMargin


  val tablesContent = CodeContent.Content(tablesSource,
    Panel.Panel(collapsable = true, defaultExpanded = true, header = "Panel heading": ReactNode)(
      ListGroup.ListGroup(fill = true)(
        ListGroupItem("Item 1"),
        ListGroupItem("Item 2"),
        ListGroupItem(<.span(^.dangerouslySetInnerHtml("&hellip;"))),
        "Some more panel content here"
      )
    )
  )

  val controlledSource =
    """
      |PanelGroup.withKey("controlledPanel")(PanelGroup.PanelGroup(activeKey = "1", accordion = true),
      |  Panel.Panel(header = "Panel 1": ReactNode, eventKey = "1")("Panel 1 content"),
      |  Panel.Panel(header = "Panel 2": ReactNode, eventKey = "2")("Panel 2 content"))
    """.stripMargin

  val controlledContent = CodeContent.Content(controlledSource,
    PanelGroup.withKey("controlledPanel")(PanelGroup.PanelGroup(activeKey = "1", accordion = true),
      Panel.Panel(header = "Panel 1": ReactNode, eventKey = "1")("Panel 1 content"),
      Panel.Panel(header = "Panel 2": ReactNode, eventKey = "2")("Panel 2 content"))
  )

  val uncontrolledSource =
    """
      |PanelGroup.withKey("controlledPanel")(PanelGroup.PanelGroup(defaultActiveKey = "2", accordion = true),
      |  Panel.withKey(1)(Panel.Panel(header = "Panel 1": ReactNode, eventKey = "1"), "Panel 1 content"),
      |  Panel.withKey(2)(Panel.Panel(header = "Panel 2": ReactNode, eventKey = "2"), "Panel 2 content"))
    """.stripMargin

  val uncontrolledContent = CodeContent.Content(uncontrolledSource,
    PanelGroup.withKey("controlledPanel")(PanelGroup.PanelGroup(defaultActiveKey = "2", accordion = true),
      Panel.withKey(1)(Panel.Panel(header = "Panel 1": ReactNode, eventKey = "1"), "Panel 1 content"),
      Panel.withKey(2)(Panel.Panel(header = "Panel 2": ReactNode, eventKey = "2"), "Panel 2 content"))
  )

  val accordionSource =
    """
      |Accordion(
      |  Panel.Panel(header = "Collapsible Group Item #1": ReactNode, eventKey = "1")(
      |    "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."),
      |  Panel.Panel(header = "Collapsible Group Item #2": ReactNode, eventKey = "2")(
      |    "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."),
      |  Panel.Panel(header = "Collapsible Group Item #3": ReactNode, eventKey = "3")(
      |    "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.")
      |)
    """.stripMargin

  val accordionContent = CodeContent.Content(accordionSource,
    Accordion(
      Panel.Panel(header = "Collapsible Group Item #1": ReactNode, eventKey = "1")(
        "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."),
      Panel.Panel(header = "Collapsible Group Item #2": ReactNode, eventKey = "2")(
        "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."),
      Panel.Panel(header = "Collapsible Group Item #3": ReactNode, eventKey = "3")(
        "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.")
    )
  )

  val content = Section("panels", <.span("Panels ", <.small("Panel, PanelGroup, Accordion"))
    , SubSection("panels-basic", "Basic example",
      <.p("By default, all the ", <.code("<Panel />"), " does is apply some basic border and padding to contain some content."),
      basicContent())
    , SubSection("panels-heading", "Panel with heading",
      <.p("Easily add a heading container to your panel with the ", <.code("header"), " prop."),
      headerContent())
    , SubSection("panels-footer", "Panel with footer",
      <.p("Pass buttons or secondary text in the ", <.code("footer"), " prop. Note that panel footers do not inherit colors and borders when using contextual variations as they are not meant to be in the foreground."),
      footerContent())
    , SubSection("panels-contextual", "Contextual alternatives",
      <.p("Like other components, easily make a panel more meaningful to a particular context by adding a ", <.code("bsStyle"), " prop."),
      contextualContent())
    , SubSection("panels-table", "With tables and list groups",
      <.p("Add the ", <.code("fill"), " prop to ", <.code("<Table />"), " or ", <.code("<ListGroup />"), " elements to make them fill the panel."),
      tablesContent())
    , SubSection("panels-controlled", "Controlled panel",
      <.p(<.code("PanelGroups"), " can be controlled by a parent component. The ", <.code("defaultActiveKey"), " prop dictates which panel is open."),
      controlledContent())
    , SubSection("panels-uncontrolled", "Uncontrolled panel",
      <.p(<.code("PanelGroup"), "s can also be uncontrolled where they manage their own state. The ", <.code("defaultActiveKey"), " prop dictates which panel is open when initially."),
      uncontrolledContent())
    , SubSection("panels-accordion", "Accordion",
      <.p(<.code("<Accordion />"), " aliases ", <.code("<PanelGroup accordion />")),
      accordionContent())
  )
}
