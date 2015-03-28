package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 17/03/15.
 */
object Popovers {

  val exampleSource =
    """
      |<.div(^.height := 120,
      |  Popover.Popover(placement = Placements.right, positionLeft = 200, positionTop = 50, title = "Popover right": ReactNode),
      |    "And here's some ", <.strong("amazing"), " content. It's very engaging. right?")
      |)
    """.stripMargin

  val exampleContent = CodeContent.Content(exampleSource,
    <.div(^.height := 120,
      Popover.Popover(placement = Placements.right, positionLeft = 200, positionTop = 50, title = "Popover right": ReactNode)(
        "And here's some ", <.strong("amazing"), " content. It's very engaging. right?"
      )
    )
  )

  val positionedSource =
    """
      |ButtonToolbar(
      |  OverlayTrigger.OverlayTrigger(placement = Placements.left, trigger = Array("click"),
      |    overlay = Popover.Popover(title = "Popover left": ReactNode)(
      |      <.strong("Holy guacomole!"), " Check this info."
      |    )
      |  )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.top, trigger = Array("click"),
      |    overlay = Popover.Popover(title = "Popover top": ReactNode)(
      |      <.strong("Holy guacomole!"), " Check this info."
      |    )
      |  )(Button.Button(bsStyle = Styles.default)(Holy guacomole!")),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.bottom, trigger = Array("click"),
      |    overlay = Popover.Popover(title = "Popover bottom": ReactNode)(
      |      <.strong("Holy guacomole!"), " Check this info."
      |    )
      |  )(Button.Button(bsStyle = Styles.default)(Holy guacomole!")),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.right, trigger = Array("click"),
      |    overlay = Popover.Popover(title = "Popover right": ReactNode)(
      |      <.strong("Holy guacomole!"), " Check this info."
      |    )
      |  )(Button.Button(bsStyle = Styles.default)(Holy guacomole!"))
      |)
    """.stripMargin

  val positionedContent = CodeContent.Content(positionedSource,
    ButtonToolbar(
      OverlayTrigger.OverlayTrigger(placement = Placements.left, trigger = Array("click"),
        overlay = Popover.Popover(title = "Popover left": ReactNode)(
          <.strong("Holy guacomole!"), " Check this info."
        )
      )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      OverlayTrigger.OverlayTrigger(placement = Placements.top, trigger = Array("click"),
        overlay = Popover.Popover(title = "Popover top": ReactNode)(
          <.strong("Holy guacomole!"), " Check this info."
        )
      )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      OverlayTrigger.OverlayTrigger(placement = Placements.bottom, trigger = Array("click"),
        overlay = Popover.Popover(title = "Popover bottom": ReactNode)(
          <.strong("Holy guacomole!"), " Check this info."
        )
      )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      OverlayTrigger.OverlayTrigger(placement = Placements.right, trigger = Array("click"),
        overlay = Popover.Popover(title = "Popover right": ReactNode)(
          <.strong("Holy guacomole!"), " Check this info."
        )
      )(Button.Button(bsStyle = Styles.default)("Holy guacomole!"))
    )
  )

  val scollingSource =
    """
      |val containerRef: RefSimple[TopNode] = Ref[HTMLElement]("container")
      |<.div(^.ref := containerRef,
      |  ButtonToolbar(
      |    OverlayTrigger.OverlayTrigger(container = container, placement = Placements.left, trigger = Array("click"),
      |      overlay = Popover.Popover(title = "Popover left": ReactNode)(
      |        <.strong("Holy guacomole!"), " Check this info."
      |      )
      |    )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      |    OverlayTrigger.OverlayTrigger(container = container, placement = Placements.top, trigger = Array("click"),
      |      overlay = Popover.Popover(title = "Popover top": ReactNode)(
      |        <.strong("Holy guacomole!"), " Check this info.")
      |    )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      |    OverlayTrigger.OverlayTrigger(container = container, placement = Placements.bottom, trigger = Array("click"),
      |      overlay = Popover.Popover(title = "Popover bottom": ReactNode)(
      |        <.strong("Holy guacomole!"), " Check this info.")
      |        )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
      |    OverlayTrigger.OverlayTrigger(container = container, placement = Placements.right, trigger = Array("click"),
      |      overlay = Popover.Popover(title = "Popover right": ReactNode)(
      |        <.strong("Holy guacomole!"), " Check this info.")
      |    )(Button.Button(bsStyle = Styles.default)("Holy guacomole!"))
      |  )
      |)
    """.stripMargin

  val scrollingContent = CodeContent.Content(positionedSource, exampleClasses = "bs-example-scroll", el = {
    val containerRef: RefSimple[TopNode] = Ref[HTMLElement]("container")

    class Backend(val scope: BackendScope[Unit, Unit])

    val component = ReactComponentB[Unit]("PopoverScrolling")
      .stateless
      .backend(new Backend(_))
      .render((_, _, B) => {
      val container = new ReferencedContainer(containerRef, B.scope)

      <.div(^.ref := containerRef,
        ButtonToolbar(
          OverlayTrigger.OverlayTrigger(container = container, placement = Placements.left, trigger = Array("click"),
            overlay = Popover.Popover(title = "Popover left": ReactNode)(
              <.strong("Holy guacomole!"), " Check this info."
            )
          )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
          OverlayTrigger.OverlayTrigger(container = container, placement = Placements.top, trigger = Array("click"),
            overlay = Popover.Popover(title = "Popover top": ReactNode)(
              <.strong("Holy guacomole!"), " Check this info.")
          )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
          OverlayTrigger.OverlayTrigger(container = container, placement = Placements.bottom, trigger = Array("click"),
            overlay = Popover.Popover(title = "Popover bottom": ReactNode)(
              <.strong("Holy guacomole!"), " Check this info.")
          )(Button.Button(bsStyle = Styles.default)("Holy guacomole!")),
          OverlayTrigger.OverlayTrigger(container = container, placement = Placements.right, trigger = Array("click"),
            overlay = Popover.Popover(title = "Popover right": ReactNode)(
              <.strong("Holy guacomole!"), " Check this info.")
          )(Button.Button(bsStyle = Styles.default)("Holy guacomole!"))
        )
      )
    }).
      buildU
    component()
  })

  val content = Section("popovers", <.span("Popovers ", <.small("Popover"))
    , SubSection("popovers-examples", "Example popovers",
      <.p("Popover component"),
      exampleContent(),
      <.p("Popover positioned"),
      positionedContent(),
      <.p("Popover scrolling"),
      scrollingContent())
  )
}
