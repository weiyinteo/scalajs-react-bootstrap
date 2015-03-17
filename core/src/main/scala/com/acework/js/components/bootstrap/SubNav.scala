package com.acework.js.components.bootstrap

import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._
import Utils._


/**
 * Created by weiyin on 09/03/15.
 */

object SubNav extends BootstrapMixin {

  type PROPS = Props

  case class Props(active: Boolean = true,
                   disabled: Boolean = false,
                   href: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   title: UndefOr[String] = undefined,
                   text: UndefOr[String] = undefined,
                   key: UndefOr[JsNumberOrString] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   ref: UndefOr[Ref] = undefined,
                   onSelect: UndefOr[(String) => Unit] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.nav,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BaseProps

  // TODO
  def getChildActiveProp(child: ReactNode): Boolean = true

  val SubNav = ReactComponentB[Props]("SubNav")
    .render((P, C) => {

    val handleClick = (e: ReactEvent) => {
      if (P.onSelect.isDefined) {
        e.preventDefault()
        if (!P.disabled)
          P.onSelect.get(P.eventKey.getOrElse("")) // FIXME , P.href, P.target)
      }

    }

    def renderNavItem(child: ReactNode, idx: Int): ReactNode = {
      val keyAndRef = getChildKeyAndRef(child, idx)
      ReactCloneWithProps(child, keyAndRef ++ Map[String, js.Any](
        "active" -> getChildActiveProp(child),
        "onSelect" -> P.onSelect.getOrElse(null) // FIXME create chain function
      )
      )
    }

    val classes = Map("active" -> P.active, "disabled" -> P.disabled)
    val anchorRef = Ref("anchor")
    <.li(^.classSet1M(P.addClasses, classes),
      <.a(^.href := P.href, ^.title := P.title, ^.target := P.target,
        ^.onClick ==> handleClick, ^.ref := anchorRef, P.text),
      <.ul(^.className := "nav",
        ValidComponentChildren.map(C, renderNavItem)
      )
    )
  }
    ).build

  def apply(props: Props, children: ReactNode*) = SubNav(props, children)
}

