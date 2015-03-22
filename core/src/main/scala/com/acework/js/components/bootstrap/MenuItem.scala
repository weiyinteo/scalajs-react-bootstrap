package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object MenuItem extends BootstrapComponent {
  override type P = MenuItem
  override type S = Unit
  override type B = Backend
  override type N = TopNode

  override def defaultProps = MenuItem()

  case class MenuItem(header: UndefOr[Boolean] = undefined,
                   divider: UndefOr[Boolean] = undefined,
                   href: String = "#",
                   title: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   onSelect: UndefOr[(String, String, String) => Unit] = undefined,
                   addClasses: String = "") extends MergeableProps[MenuItem] {

    def merge(t: Map[String, Any]): MenuItem = implicitly[Mergeable[MenuItem]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[MenuItem]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend($: BackendScope[MenuItem, Unit]) {
    def handleClick(e: ReactEvent): Unit = {
      if ($.props.onSelect.isDefined) {
        e.preventDefault()
        $.props.onSelect.get($.props.eventKey.get, $.props.href, $.props.target.get)
      }
    }
  }

  override val component = ReactComponentB[MenuItem]("MenuItem")
    .stateless
    .backend(new Backend(_))
    .render { (P, C, S, B) =>

    def renderAnchor(): ReactNode = {
      <.a(^.onClick --> B.handleClick _, ^.href := P.href, ^.target := P.target, ^.title := P.title, ^.tabIndex := -1)(C)
    }

    val classes = Map("dropdown-header" -> P.header.getOrElse(false), "divider" -> P.divider.getOrElse(false))

    val children: TagMod =
      if (P.header.getOrElse(false))
        C
      else if (!P.divider.getOrElse(false))
        renderAnchor()
      else
        EmptyTag

    // TODO spread props
    <.li(^.classSet1M(P.addClasses, classes), ^.role := "presentation")(children)
  }.build

}
