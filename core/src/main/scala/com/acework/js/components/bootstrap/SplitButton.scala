package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Ref

/**
 * Created by weiyin on 11/03/15.
 */

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

object SplitButton extends BootstrapComponent {
  override type P = SplitButton
  override type S = DropdownState
  override type B = Backend
  override type N = TopNode

  override def defaultProps = SplitButton()

  case class SplitButton(
                    /*==  start react bootstraps  ==*/
                    id: UndefOr[String] = undefined,
                    pullRight: UndefOr[Boolean] = undefined,
                    title: UndefOr[ReactNode] = undefined,
                    href: UndefOr[String] = undefined,
                    target: UndefOr[String] = undefined,
                    dropdownTitle: UndefOr[String] = "Toggle dropdown",
                    onClick: UndefOr[(ReactEvent) => Unit] = undefined,
                    onSelect: UndefOr[(String) => Unit] = undefined,
                    disabled: UndefOr[Boolean] = undefined,
                    dropup: UndefOr[Boolean] = undefined,
                    /*==  end react bootstraps  ==*/
                    bsClass: UndefOr[Classes.Value] = Classes.btn,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    addClasses: String = "")
    extends BsProps with MergeableProps[SplitButton] {

    def merge(t: Map[String, Any]): SplitButton = implicitly[Mergeable[SplitButton]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[SplitButton]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend(val scope: BackendScope[SplitButton, DropdownState]) extends DropdownStateMixin[SplitButton] {
    def handleButtonClick(e: ReactEvent) = {
      if (scope.state.open)
        setDropdownState(false)

      if (scope.props.onClick.isDefined)
        scope.props.onClick.get(e) // FIXME , scope.props.href, scope.props.target)
    }

    def handleOptionSelect(key: String): Unit = {
      if (scope.props.onSelect.isDefined)
        scope.props.onSelect.get(key)

      setDropdownState(false)
    }

    def handleDropdownClick(e: ReactEvent) = {
      e.preventDefault()
      setDropdownState(!scope.state.open)
    }
  }

  override val component = ReactComponentB[SplitButton]("SplitButton")
    .initialState(DropdownState(open = false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {


    val buttonRef = Ref("button")
    val button = Button.withRef("button")(Button.Button(bsStyle = P.bsStyle, bsClass = P.bsClass,
      onClick = (e: ReactEvent) => B.handleButtonClick(e)), P.title.getOrElse(""): ReactNode)

    val dropdownButtonRef = Ref("dropdownButton")
    val dropdownButton = Button.withRef("dropdownButton")(Button.Button(bsStyle = P.bsStyle, bsClass = P.bsClass, addClasses = s"${P.addClasses} dropdown-toggle",
      onClick = (e: ReactEvent) => B.handleDropdownClick(e)),
      <.span(^.className := "sr-only", P.title),
      <.span(^.className := "caret"))

    val menuRef = Ref("menu")
    var groupClasses = ""
    if (S.open)
      groupClasses += " open"
    if (P.dropup.getOrElse(false))
      groupClasses += " dropup"

    ButtonGroup(ButtonGroup.ButtonGroup(bsSize = P.bsSize, id = P.id, addClasses = groupClasses), button, dropdownButton,
      DropdownMenu.withRef("menu")(DropdownMenu.DropdownMenu(pullRight = P.pullRight,
        ariaLabelledBy = P.id, onSelect = (key: String) => B.handleOptionSelect(key)), C)
    )

  })
    .componentWillUnmount(_.backend.onComponentWillUnmount())
    .build

}
