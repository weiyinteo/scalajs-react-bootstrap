package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 17/03/15.
 */
object Progressbars {

  val exampleSource =
    """
      |ProgressBar.Props(now = 60.0)()
    """.stripMargin

  val exampleContent = CodeContent.Content(exampleSource,
    ProgressBar.ProgressBar(now = 60.0)()
  )

  val withLabelSource =
    """
      |ProgressBar.ProgressBar(now = 60.0, label = "%(percent)s%": ReactNode)()
    """.stripMargin

  val withLabelContent = CodeContent.Content(withLabelSource,
    ProgressBar.ProgressBar(now = 60.0, label = "%(percent)s%": ReactNode)()
  )

  val srOnlySource =
    """
      |ProgressBar.ProgressBar(now = 60.0, label = "%(percent)s%": ReactNode, srOnly = true)()
    """.stripMargin

  val srOnlyContent = CodeContent.Content(srOnlySource,
    ProgressBar.ProgressBar(now = 60.0, label = "%(percent)s%": ReactNode, srOnly = true)()
  )

  val contextualSource =
    """
      |<.div(
      |  ProgressBar.ProgressBar(bsStyle = Styles.success, now = 40.0)(),
      |  ProgressBar.ProgressBar(bsStyle = Styles.info, now = 20.0)(),
      |  ProgressBar.ProgressBar(bsStyle = Styles.warning, now = 60.0)(),
      |  ProgressBar.ProgressBar(bsStyle = Styles.danger, now = 80.0)()
      |)
    """.stripMargin

  val contextualContent = CodeContent.Content(contextualSource,
    <.div(
      ProgressBar.ProgressBar(bsStyle = Styles.success, now = 40.0)(),
      ProgressBar.ProgressBar(bsStyle = Styles.info, now = 20.0)(),
      ProgressBar.ProgressBar(bsStyle = Styles.warning, now = 60.0)(),
      ProgressBar.ProgressBar(bsStyle = Styles.danger, now = 80.0)()
    )
  )

  val stripedSource =
    """
      |<.div(
      |  ProgressBar.ProgressBar(striped = true, bsStyle = Styles.success, now = 40.0)(),
      |  ProgressBar.ProgressBar(striped = true, bsStyle = Styles.info, now = 20.0)(),
      |  ProgressBar.ProgressBar(striped = true, bsStyle = Styles.warning, now = 60.0)(),
      |  ProgressBar.ProgressBar(striped = true, bsStyle = Styles.danger, now = 80.0)())
    """.stripMargin

  val stripedContent = CodeContent.Content(stripedSource,
    <.div(
      ProgressBar.ProgressBar(striped = true, bsStyle = Styles.success, now = 40.0)(),
      ProgressBar.ProgressBar(striped = true, bsStyle = Styles.info, now = 20.0)(),
      ProgressBar.ProgressBar(striped = true, bsStyle = Styles.warning, now = 60.0)(),
      ProgressBar.ProgressBar(striped = true, bsStyle = Styles.danger, now = 80.0)())
  )

  val animatedSource =
    """
      |ProgressBar.Props(active = true, now = 60.0)()
    """.stripMargin

  val animatedContent = CodeContent.Content(animatedSource,
    ProgressBar.ProgressBar(active = true, now = 60.0)()
  )

  val stackedSource =
    """
      |ProgressBar(
      |  ProgressBar.withKey(1)(ProgressBar.ProgressBar(bsStyle = Styles.success, now = 20.0)),
      |  ProgressBar.withKey(2)(ProgressBar.ProgressBar(bsStyle = Styles.warning, now = 60.0)),
      |  ProgressBar.withKey(3)(ProgressBar.ProgressBar(bsStyle = Styles.danger, now = 80.0))
      |)
    """.stripMargin

  val stackedContent = CodeContent.Content(stackedSource,
    ProgressBar(
      ProgressBar.withKey(1)(ProgressBar.ProgressBar(bsStyle = Styles.success, now = 35.0)),
      ProgressBar.withKey(2)(ProgressBar.ProgressBar(bsStyle = Styles.warning, now = 20.0)),
      ProgressBar.withKey(3)(ProgressBar.ProgressBar(bsStyle = Styles.danger, now = 10.0))
    )
  )


  val content = Section("progressbars", <.span("Progress bars ", <.small("ProgressBar"))
    , <.h3("Provide up-to-date feedback on the progress of a workflow or action with simple yet flexible progress bars.")
    , SubSection("progressbars-examples", "Basic example",
      <.p("Default progressbar"),
      exampleContent())
    , SubSection("progressbars-label", "With label",
      <.p("Add a ", <.code("label"), " prop to show a visible percentage. For low percentages, consider adding a min-width to ensure the label's text is fully visible."),
      <.p("The following keys are interpolated with the current values: ",
        <.code("%(min)s"), " ", <.code("%(max)s"), " ", <.code("%(now)s"), " ", <.code("%(percent)s"), " ", <.code("%(bsStyle)s")),
      withLabelContent())
    , SubSection("progressbars-sr-only", "Screenreader only label",
      <.p("Add a ", <.code("srOnly"), " prop to hide the label visually."),
      srOnlyContent())
    , SubSection("progressbars-contextual", "Contextual alternatives",
      <.p("Progress bars use some of the same button and alert classes for consistent styles."),
      contextualContent())
    , SubSection("progressbars-striped", "Striped",
      <.p("Uses a gradient to create a striped effect. Not available in IE8."),
      stripedContent())
    , SubSection("progressbars-animated", "Animated",
      <.p("Add ", <.code("active"), " prop to animate the stripes right to left. Not available in IE9 and below."),
      animatedContent())
    , SubSection("progressbars-stacked", "Stacked",
      <.p("Nest ", <.code("<ProgressBar />"), "s to stack them."),
      stackedContent())
  )
}
