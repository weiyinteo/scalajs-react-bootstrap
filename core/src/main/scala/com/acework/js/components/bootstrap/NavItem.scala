package com.acework.js.components.bootstrap

import Utils._
import com.acework.js.logger._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 09/03/15.
 */
object NavItem {

  case class Props(active: Boolean = false, disabled: Boolean = false,
                   href: UndefOr[String] = "#",
                   eventKey: UndefOr[String] = undefined,
                   title: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   key: UndefOr[JsNumberOrString] = undefined,
                   onSelect: UndefOr[(UndefOr[String], UndefOr[String], UndefOr[String]) => Unit] = undefined,
                   addClasses: String = "")

  val navItem = ReactComponentB[Props]("NavItem")
    .render((P, C) => {
    val handleClick: (ReactEvent) => Unit = { e =>
      P.onSelect.map { onSelect =>
        log.debug(s"navButton ${P.href}, clicked")
        e.preventDefault()
        if (!P.disabled)
          onSelect(P.eventKey, P.href, P.target)
      }
    }

    if (P.href.getOrElse("NA") == "#")
      1 // FIXME <a role="button">

    val classes = Map("active" -> P.active, "disabled" -> P.disabled)

    <.li(^.classSet1M(P.addClasses, classes),
      // FIXME handleClick
      <.a(^.href := P.href, ^.title := P.title, ^.target := P.target, ^.ref := "anchor", ^.onClick ==> handleClick)(C))
  }

    ).build

  def apply(props: Props, children: ReactNode*) = navItem(props, children)
}