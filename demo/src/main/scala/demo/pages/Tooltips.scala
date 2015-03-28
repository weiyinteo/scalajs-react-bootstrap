package demo.pages

import com.acework.js.components.bootstrap._
import com.acework.js.utils.{Mappable, Mergeable}
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 17/03/15.
 */
object Tooltips {

  val exampleSource =
    """
      |<.div(^.height := 50,
      |  Tooltip.Tooltip(placement = Placements.right, positionLeft = 150, positionTop = 50),
      |    <.strong("Holy guacomole!"), " Check this info."
      |  )
      |)
    """.stripMargin

  val exampleContent = CodeContent.Content(exampleSource,
    <.div(^.height := 50,
      Tooltip.Tooltip(placement = Placements.right, positionLeft = 150, positionTop = 50)(
        <.strong("Holy guacomole!"), " Check this info."
      )
    )
  )
  val positionedSource =
    """
      |ButtonToolbar(
      |  OverlayTrigger.OverlayTrigger(placement = Placements.left,
      |    overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
      |      Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
      |    ),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.top,
      |    overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
      |      Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
      |    ),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.bottom,
      |    overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
      |      Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
      |    ),
      |  OverlayTrigger.OverlayTrigger(placement = Placements.right,
      |    overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
      |      Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
      |    )
      |)
    """.stripMargin

  val positionedContent = CodeContent.Content(positionedSource,
    ButtonToolbar(
      OverlayTrigger.OverlayTrigger(placement = Placements.left,
        overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
          Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
        ),
      OverlayTrigger.OverlayTrigger(placement = Placements.top,
        overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
          Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
        ),
      OverlayTrigger.OverlayTrigger(placement = Placements.bottom,
        overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
          Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
        ),
      OverlayTrigger.OverlayTrigger(placement = Placements.right,
        overlay = Tooltip(<.strong("Holy guacomole!"), " Check this info."))(
          Button(Button.Button(bsStyle = Styles.default), "Holy guacomole!")
        )
    )
  )

  val linkedSource =
    """
      |case class LinkedTooltipProps(tooltip: ReactNode, href: String) extends MergeableProps[LinkedTooltipProps] {
      |    def merge(t: Map[String, Any]): LinkedTooltipProps = implicitly[Mergeable[LinkedTooltipProps]].merge(this, t)
      |    def asMap: Map[String, Any] = implicitly[Mappable[LinkedTooltipProps]].toMap(this)
      |}
      |
      |val LinkWithTooltip = ReactComponentB[LinkedTooltipProps]("LinkWithTooltip")
      |  .stateless
      |  .render((P, C, S) => {
      |  val tooltip = Tooltip(P.tooltip)
      |  OverlayTrigger.Props(placement = Placements.top,
      |    overlay = tooltip, delayShow = 300.0, delayHide = 150.0)(
      |    <.a(^.href := P.href, C)
      |  )
      |}).build
      |
      |<.p(^.className := "muted", ^.marginBottom := 0,
      |  "Tight pants next level keffiyeh ",
      |  LinkWithTooltip(LinkedTooltipProps(tooltip = "Default tooltip", href = "#"), "you probably "),
      |  " haven't heard of them. Photo booth beard raw denim letterpress vegan messenger bag stumptown. Farm-to-table seitan, mcsweeney's fixie sustainable quinoa 8-bit american apparel ",
      |  LinkWithTooltip(LinkedTooltipProps(tooltip = <.span("Another ", <.strong("tooltip")), href = "#"), "have a "),
      |  "terry richardson vinyl chambray. Beard stumptown, cardigans banh mi lomo thundercats. Tofu biodiesel williamsburg marfa, four loko mcsweeney's cleanse vegan chambray. A really ironic artisan ",
      |  LinkWithTooltip(LinkedTooltipProps(tooltip = "Another one here to", href = "#"), " whatever keytar"),
      |  " , scenester farm-to-table banksy Austin ",
      |  LinkWithTooltip(LinkedTooltipProps(tooltip = "The last tip!", href = "#"), "twitter handle"),
      |  "freegan cred raw denim single-origin coffee viral. ")
    """.stripMargin

  case class LinkedTooltipProps(tooltip: ReactNode, href: String) extends MergeableProps[LinkedTooltipProps] {

    def merge(t: Map[String, Any]): LinkedTooltipProps = implicitly[Mergeable[LinkedTooltipProps]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[LinkedTooltipProps]].toMap(this)
  }

  val linkedContent = CodeContent.Content(positionedSource, {

    val LinkWithTooltip = ReactComponentB[LinkedTooltipProps]("LinkWithTooltip")
      .stateless
      .render((P, C, S) => {
      val tooltip = Tooltip(P.tooltip)
      OverlayTrigger.OverlayTrigger(placement = Placements.top,
        overlay = tooltip, delayShow = 300.0, delayHide = 150.0)(
          <.a(^.href := P.href, C)
        )
    }).build

    <.p(^.className := "muted", ^.marginBottom := 0,
      "Tight pants next level keffiyeh ",
      LinkWithTooltip(LinkedTooltipProps(tooltip = "Default tooltip", href = "#"), "you probably "),
      " haven't heard of them. Photo booth beard raw denim letterpress vegan messenger bag stumptown. Farm-to-table seitan, mcsweeney's fixie sustainable quinoa 8-bit american apparel ",
      LinkWithTooltip(LinkedTooltipProps(tooltip = <.span("Another ", <.strong("tooltip")), href = "#"), "have a "),
      "terry richardson vinyl chambray. Beard stumptown, cardigans banh mi lomo thundercats. Tofu biodiesel williamsburg marfa, four loko mcsweeney's cleanse vegan chambray. A really ironic artisan ",
      LinkWithTooltip(LinkedTooltipProps(tooltip = "Another one here to", href = "#"), " whatever keytar"),
      " , scenester farm-to-table banksy Austin ",
      LinkWithTooltip(LinkedTooltipProps(tooltip = "The last tip!", href = "#"), "twitter handle"),
      "freegan cred raw denim single-origin coffee viral. ")
  })

  val content = Section("tooltips",
    <.span("Tooltips ",
      <.small("Tooltip"))
    , SubSection("panels-examples", "Example tooltips",
      <.p("Tooltip component"),
      exampleContent())
    , SubSection("panels-positioned", "Positioned tooltips",
      <.p("Positioned tooltip component."),
      positionedContent(),
      <.p("Positioned tooltips in copy"),
      linkedContent())
  )
}
