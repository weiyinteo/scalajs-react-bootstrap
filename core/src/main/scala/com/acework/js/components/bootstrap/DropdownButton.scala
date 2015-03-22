package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react.Ref

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

object DropdownButton extends BootstrapComponent {
  override type P = DropdownButton
  override type S = DropdownState
  override type B = Backend
  override type N = TopNode

  override def defaultProps: P = DropdownButton()

  case class DropdownButton(
                    /*==  start react bootstraps  ==*/
                    id: UndefOr[String] = undefined,
                    pullRight: UndefOr[Boolean] = undefined,
                    dropup: UndefOr[Boolean] = undefined,
                    title: UndefOr[ReactNode] = undefined,
                    href: UndefOr[String] = undefined,
                    eventKey: UndefOr[String] = undefined,
                    navItem: UndefOr[Boolean] = undefined,
                    noCaret: UndefOr[Boolean] = undefined,
                    onClick: () => Unit = () => (),
                    onSelect: UndefOr[(String) => Unit] = undefined,
                    /*==  end react bootstraps  ==*/
                    bsClass: UndefOr[Classes.Value] = Classes.btn,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    addClasses: String = "")
    extends BsProps with MergeableProps[DropdownButton] {

    def merge(t: Map[String, Any]): DropdownButton = implicitly[Mergeable[DropdownButton]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[DropdownButton]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend(val scope: BackendScope[DropdownButton, DropdownState]) extends DropdownStateMixin[DropdownButton] {

    def handleOptionSelect(key: String): Unit = {
      if (scope.props.onSelect.isDefined)
        scope.props.onSelect.get(key)
    }

    def handleDropdownClick(e: ReactEvent) = {
      e.preventDefault()
      setDropdownState(!scope.state.open)
    }
  }

  override val component = ReactComponentB[DropdownButton]("DropdownButton")
    .initialState(DropdownState(open = false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    def renderButtonGroup(children: ReactNode*) = {
      var addClasses = "dropdown"
      if (S.open)
        addClasses += " open"
      ButtonGroup(ButtonGroup.ButtonGroup(addClasses = addClasses), children)
    }
    def renderNavItem(children: ReactNode*) = {
      <.li(^.classSet1("dropdown", "open" -> S.open, "dropup" -> P.dropup.getOrElse(false)))(children)
    }

    def renderMenuItem(child: ReactNode, index: Int) = {
      // Only handle the option selection if an onSelect prop has been set on the
      // component or it's child, this allows a user not to pass an onSelect
      // handler and have the browser preform the default action.
      val handleOptionSelect: js.Any = if (P.onSelect.isDefined) // FIXME || child.props.onSelect)
        B.handleOptionSelect _
      else () => ()

      val keyAndRef = getChildKeyAndRef(child, index)
      ReactCloneWithProps(child, keyAndRef ++
        Map[String, js.Any](
          "onSelect" -> handleOptionSelect) // FIXME createChainedFunction0(childProps.onSelect, handleOptionSelect)
      )
    }

    val buttonRef = Ref("dropdownButton")
    val buttonProps = Button.Button(
      addClasses = "dropdown-toggle",
      onClick = (e: ReactEvent) => B.handleDropdownClick(e),
      navDropdown = P.navItem.getOrElse(false),
      bsStyle = P.bsStyle,
      bsSize = P.bsSize,
      bsClass = P.bsClass
    )

    val menuRef = Ref("menu")
    val dropdownMenu = DropdownMenu.withKey(1).withRef("menu")(DropdownMenu.DropdownMenu(ariaLabelledBy = P.id,
      pullRight = P.pullRight),
      ValidComponentChildren.map(C, renderMenuItem)
    )

    val button = if (P.noCaret.getOrElse(false))
      Button.withKey(0).withRef("dropdownButton")(buttonProps, P.title.get)
    else
      Button.withKey(0).withRef("dropdownButton")(buttonProps, P.title.get, " ", <.span(^.className := "caret"))

    if (P.navItem.getOrElse(false))
      renderNavItem(button, dropdownMenu)
    else
      renderButtonGroup(button, dropdownMenu)
  })
    .componentWillUnmount(_.backend.onComponentWillUnmount())
    .build

}