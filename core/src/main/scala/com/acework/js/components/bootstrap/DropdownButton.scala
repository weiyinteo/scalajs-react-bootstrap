package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react.Ref

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

object DropdownButton extends BootstrapMixin {

  case class Props(
                    /*==  start react bootstraps  ==*/
                    id: UndefOr[String] = undefined,
                    pullRight: UndefOr[Boolean] = undefined,
                    dropup: UndefOr[Boolean] = undefined,
                    title: UndefOr[ReactNode] = undefined,
                    href: UndefOr[String] = undefined,
                    navItem: UndefOr[Boolean] = undefined,
                    noCaret: UndefOr[Boolean] = undefined,
                    onClick: () => Unit = () => (),
                    onSelect: UndefOr[(String) => Unit] = undefined,
                    /*==  end react bootstraps  ==*/
                    bsClass: UndefOr[Classes.Value] = Classes.btn,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    key: UndefOr[String] = undefined,
                    ref: UndefOr[Ref] = undefined,
                    addClasses: String = "")
    extends BaseProps

  type PROPS = Props

  class Backend(val scope: BackendScope[Props, DropdownState]) extends DropdownStateMixin[Props] {

    def handleOptionSelect(key: String): Unit = {
      if (scope.props.onSelect.isDefined)
        scope.props.onSelect.get(key)
    }

    def handleDropdownClick(e: ReactEvent) = {
      e.preventDefault()
      setDropdownState(!scope.state.open)
    }
  }

  val DropdownButton = ReactComponentB[Props]("DropdownButton")
    .initialState(DropdownState(open = false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    def renderButtonGroup(children: ReactNode*) = {
      var addClasses = "dropdown"
      if (S.open)
        addClasses += " open"
      ButtonGroup(ButtonGroup.Props(addClasses = addClasses), children)
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
    val buttonProps = Button.Props(ref = buttonRef,
      addClasses = "dropdown-toggle",
      onClick = (e: ReactEvent) => B.handleDropdownClick(e),
      key = 0,
      navDropdown = P.navItem.getOrElse(false),
      bsStyle = P.bsStyle,
      bsSize = P.bsSize,
      bsClass = P.bsClass
    )

    val menuRef = Ref("menu")
    val dropdownMenu = DropdownMenu(DropdownMenu.Props(ref = menuRef, ariaLabelledBy = P.id,
      pullRight = P.pullRight, key = 1),
      ValidComponentChildren.map(C, renderMenuItem)
    )

    val button = if (P.noCaret.getOrElse(false))
      Button(buttonProps, P.title.get)
    else
      Button(buttonProps, P.title.get, " ", <.span(^.className := "caret"))

    if (P.navItem.getOrElse(false))
      renderNavItem(button, dropdownMenu)
    else
      renderButtonGroup(button, dropdownMenu)
  })
    .componentWillUnmount(_.backend.onComponentWillUnmount())
    .build

  def apply(props: Props, children: ReactNode*) = DropdownButton(props, children)
}
