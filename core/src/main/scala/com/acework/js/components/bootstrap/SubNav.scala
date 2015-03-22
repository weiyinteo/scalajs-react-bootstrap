package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}


/**
 * Created by weiyin on 09/03/15.
 */

object SubNav extends BootstrapComponent {
  override type P = SubNav
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = SubNav()

  case class SubNav(active: Boolean = true,
                   disabled: Boolean = false,
                   href: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   title: UndefOr[String] = undefined,
                   text: UndefOr[String] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   onSelect: UndefOr[(String) => Unit] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.nav,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[SubNav] {

    def merge(t: Map[String, Any]): SubNav = implicitly[Mergeable[SubNav]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[SubNav]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  // TODO
  def getChildActiveProp(child: ReactNode): Boolean = true

  override val component = ReactComponentB[SubNav]("SubNav")
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

}

