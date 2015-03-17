package com.acework.js.components.bootstrap

import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react.Ref

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

object DropdownMenu {

  case class Props(
                    /*==  start react bootstraps  ==*/
                    ariaLabelledBy: UndefOr[String] = undefined,
                    pullRight: UndefOr[Boolean] = undefined,
                    onSelect: UndefOr[(String) => Unit] = undefined,
                    /*==  end react bootstraps  ==*/
                    key: UndefOr[JsNumberOrString] = undefined,
                    ref: UndefOr[Ref] = undefined,
                    addClasses: String = "")

  val DropdownMenu = ReactComponentB[Props]("DropdownMenu")
    .render((P, C) => {

    def renderMenuItem(child: ReactNode, index: Int) = {
      val keyAndRef = getChildKeyAndRef(child, index)
      if (P.onSelect.isDefined)
        ReactCloneWithProps(child, keyAndRef ++
          Map[String, js.Any](
            "onSelect" -> P.onSelect.get) // FIXME createChainedFunction0(childProps.onSelect, handleOptionSelect)
        )
      else
        ReactCloneWithProps(child, keyAndRef)
    }

    <.ul(^.classSet1("dropdown-menu", "dropdown-menu-right" -> P.pullRight.getOrElse(false)),
      ^.role := "menu", ^.aria.labelledby := P.ariaLabelledBy)(
      ValidComponentChildren.map(C, renderMenuItem)
    )
  })
    .build

  def apply(props: Props, children: ReactNode*) = DropdownMenu(props, children)
}
