package com.acework.js.components.bootstrap

import com.acework.js.logger._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */
object NavItem extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()

  case class Props(active: Boolean = false, disabled: Boolean = false,
                   href: UndefOr[String] = "#",
                   eventKey: UndefOr[String] = undefined,
                   title: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   onSelect: UndefOr[Seq[UndefOr[String]] => Unit] = undefined,
                   addClasses: String = "") extends MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)
  }

  override val component = ReactComponentB[Props]("NavItem")
    .render((P, C) => {

    def handleClick(e: ReactEvent): Unit = {
      P.onSelect.map { onSelect =>
        e.preventDefault()
        if (!P.disabled)
          onSelect(Seq(P.eventKey, P.href, P.target))
      }
    }

    val classes = Map("active" -> P.active, "disabled" -> P.disabled)

    var link = <.a(^.href := P.href, ^.title := P.title, ^.target := P.target,
      ^.ref := "anchor", ^.onClick ==> handleClick)

    if (P.href.getOrElse("NA") == "#")
      link = link(^.role := "button")

    <.li(^.classSet1M(P.addClasses, classes),
      link(C)
    )
  }

    ).build

}