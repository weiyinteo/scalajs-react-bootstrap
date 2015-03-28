package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 16/03/15.
 */
object Buttons {
  val optionsSource =
    """
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary)("Primary"),
      |  Button.Button(bsStyle = Styles.success)("Success"),
      |  Button.Button(bsStyle = Styles.info)("Info"),
      |  Button.Button(bsStyle = Styles.warning)("Warning"),
      |  Button.Button(bsStyle = Styles.danger)("Danger"),
      |  Button.Button(bsStyle = Styles.link)("Link"))
    """.stripMargin

  def optionsContent = CodeContent.Content(optionsSource,
    ButtonToolbar(
      //Button.Button()("Default"),
      Button.Button(bsStyle = Styles.primary)("Primary"),
      Button.Button(bsStyle = Styles.success)("Success"),
      Button.Button(bsStyle = Styles.info)("Info"),
      Button.Button(bsStyle = Styles.warning)("Warning"),
      Button.Button(bsStyle = Styles.danger)("Danger"),
      Button.Button(bsStyle = Styles.link)("Link")
    )
  )

  val sizesSource =
    """
      |<.div(
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg)("Large button"),
      |  Button.Button(bsSize = Sizes.lg)("Large button")),
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary)("Default button"),
      |  Button("Default button")),
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.sm)("Small button"),
      |  Button.Button(bsSize = Sizes.sm)("Small button")),
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.xs)("Extra small button"),
      |  Button.Button(bsSize = Sizes.xs)("Extra small button"))
      |)
    """.stripMargin

  val sizesContent = CodeContent.Content(sizesSource,
    <.div(
      ButtonToolbar(
        Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg)("Large button"),
        Button.Button(bsSize = Sizes.lg)("Large button")),
      ButtonToolbar(
        Button.Button(bsStyle = Styles.primary)("Default button"),
        Button("Default button")),
      ButtonToolbar(
        Button.Button(bsStyle = Styles.primary, bsSize = Sizes.sm)("Small button"),
        Button.Button(bsSize = Sizes.sm)("Small button")),
      ButtonToolbar(
        Button.Button(bsStyle = Styles.primary, bsSize = Sizes.xs)("Extra small button"),
        Button.Button(bsSize = Sizes.xs)("Extra small button"))
    )
  )

  val blockSource =
    """
      |<.div(^.className := "well", ^.maxWidth := 400, ^.margin := "0 auto 10px",
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, block = true)("Block level button"),
      |  Button.Button(bsSize = Sizes.lg, block = true)("Block level button")
      |)
    """.stripMargin

  val blockContent = CodeContent.Content(blockSource,
    <.div(^.className := "well", ^.maxWidth := 400, ^.margin := "0 auto 10px",
      Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, block = true)("Block level button"),
      Button.Button(bsSize = Sizes.lg, block = true)("Block level button")
    )
  )

  val activeStateSource =
    """
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, active = true)("Primary button"),
      |  Button.Button(bsSize = Sizes.lg, active = true)("Button"))
    """.stripMargin

  val activeStateContent = CodeContent.Content(activeStateSource,
    ButtonToolbar(
      Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, active = true)("Primary button"),
      Button.Button(bsSize = Sizes.lg, active = true)("Button")
    )
  )

  val disabledStateSource =
    """
      |ButtonToolbar(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, disabled = true)("Primary button"),
      |  Button.Button(bsSize = Sizes.lg, disabled = true)("Button"))
    """.stripMargin

  val disabledStateContent = CodeContent.Content(disabledStateSource,
    ButtonToolbar(
      Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg, disabled = true)("Primary button"),
      Button.Button(bsSize = Sizes.lg, disabled = true)("Button")
    )
  )
  val tagsSource =
    """
      |ButtonToolbar(
      |  Button.Button(href = "#")("Link"),
      |  Button("Button"))
    """.stripMargin

  val tagsContent = CodeContent.Content(tagsSource,
    ButtonToolbar(
      Button(Button.Button(href = "#"), "Link"),
      Button("Button")
    )
  )

  val loadingSource =
    """
      |case class LoadingState(isLoading: Boolean)
      |
      |class LoadingStateBackend(scope: BackendScope[Unit, LoadingState]) {
      |  def handleClick(): Unit = {
      |    scope.modState(_.copy(isLoading = true))
      |
      |    timers.setTimeout(2000)(scope.modState(_.copy(isLoading = false)))
      |  }
      |}
      |
      |val LoadingButton = ReactComponentB[Unit]("Loading Button")
      |  .initialState(LoadingState(isLoading = false))
      |  .backend(new LoadingStateBackend(_))
      |  .render((_, S, B) => {
      |  val handleClick: UndefOr[(ReactEvent) => Unit] = if (S.isLoading)
      |    undefined
      |  else {
      |    (e: ReactEvent) => B.handleClick()
      |  }
      |
      |  Button.Button(bsStyle = Styles.primary, disabled = S.isLoading, onClick = handleClick)(
      |    if (S.isLoading) "Loading..." else "Loading State"
      |  )
      |}).buildU
      |
      |LoadingButton()
    """.stripMargin

  case class LoadingState(isLoading: Boolean)

  class LoadingStateBackend(scope: BackendScope[Unit, LoadingState]) {
    def handleClick(): Unit = {
      scope.modState(_.copy(isLoading = true))

      timers.setTimeout(2000)(scope.modState(_.copy(isLoading = false)))
    }
  }

  val LoadingButton = ReactComponentB[Unit]("Loading Button")
    .initialState(LoadingState(isLoading = false))
    .backend(new LoadingStateBackend(_))
    .render((_, S, B) => {
    val handleClick: UndefOr[(ReactEvent) => Unit] = if (S.isLoading)
      undefined
    else {
      (e: ReactEvent) => B.handleClick()
    }

    Button.Button(bsStyle = Styles.primary, disabled = S.isLoading, onClick = handleClick)(
      if (S.isLoading) "Loading..." else "Loading State"
    )
  }).buildU

  val loadingContent = CodeContent.Content(loadingSource, LoadingButton())

  val content = Section("buttons", <.span("Buttons ", <.small("Button"))
    , SubSection("buttons-options", "Options",
      <.p("Use any of the available button style types to quickly create a styled button. Just modify the ", <.code("bsStyle"), "prop."),
      optionsContent())
    , SubSection("buttons-sizes", "Sizes",
      <.p("Fancy larger or smaller buttons? Add ", <.code("bsSize"), "=\"large\", ",
        <.code("bsSize"), "=\"small\", or ", <.code("bsSize"), "=\"xsmall\" for additional sizes."),
      sizesContent(),
      <.p("Create block level buttons—those that span the full width of a parent— by adding the ", <.code("block"), " prop."),
      blockContent()
    )
    , SubSection("buttons-active", "Active state",
      <.p("To set a buttons active state simply set the components ", <.code("active"), " prop"),
      activeStateContent()
    )
    , SubSection("buttons-disabled", "Disable state",
      <.p("Make buttons look unclickable by fading them back 50%. To do this add the ", <.code("disable"), " attribute"),
      disabledStateContent()
    )
    , SubSection("buttons-tags", "Button tags",
      <.p("The DOM element tag is choosen automaticly for you based on the props you supply. Passing ahref will result in the button using a ", <.code("<a />"), " element otherwise a ", <.code("<button />"), " element will be used."),
      tagsContent()
    )
    , SubSection("buttons-loading", "Button loading state",
      <.p("When activating an asynchronous action from a button it is a good UX pattern to give the user feedback as to the loading state, this can easily be done by updating your ", <.code("<Button />"), "’s props from a state change like below."),
      loadingContent()
    )
  )

}
