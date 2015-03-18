package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object PageItem extends BootstrapComponent {
  override type P = PageItem
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = PageItem()

  case class PageItem(href: UndefOr[String] = "#",
                   title: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   disabled: UndefOr[Boolean] = undefined,
                   previous: UndefOr[Boolean] = undefined,
                   next: UndefOr[Boolean] = undefined,
                   eventKey: UndefOr[js.Any] = undefined,
                   onSelect: UndefOr[(js.Any, String, String) => Unit] = undefined, addClasses: String = "")
    extends MergeableProps[PageItem] {

    def merge(t: Map[String, Any]): PageItem = implicitly[Mergeable[PageItem]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[PageItem]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[PageItem]("PageItem")
    .render { (P, C) =>

    def handleSelect(e: ReactEvent) = {
      if (P.onSelect.isDefined) {
        e.preventDefault()

        if (!P.disabled.getOrElse(false)) {
          P.onSelect.get(P.eventKey.get, P.href.get, P.target.get)
        }
      }
    }

    val classes = Map(
      "disabled" -> P.disabled.getOrElse(false),
      "previous" -> P.previous.getOrElse(false),
      "next" -> P.next.getOrElse(false)
    )

    <.li(^.classSet1M(P.addClasses, classes),
      <.a(^.href := P.href, ^.title := P.title, ^.target := P.target, ^.onClick ==> handleSelect,
      C)
    )
  }.build
}
