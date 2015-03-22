package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object ListGroupItem extends BootstrapComponent {
  override type P = ListGroupItem
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = ListGroupItem()

  case class ListGroupItem(active: UndefOr[Boolean] = undefined,
                   disabled: UndefOr[Boolean] = undefined,
                   header: UndefOr[ReactNode] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   href: UndefOr[String] = undefined,
                   target: UndefOr[String] = undefined,
                   onSelect: UndefOr[(UndefOr[String]) => Unit] = undefined,
                   onClick: UndefOr[(UndefOr[String]) => Unit] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.`list-group-item`,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with MergeableProps[ListGroupItem] {

    def merge(t: Map[String, Any]): ListGroupItem = implicitly[Mergeable[ListGroupItem]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[ListGroupItem]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[ListGroupItem]("ListGroupItem")
    .render { (P, C) =>

    def renderAnchor(classes: Map[String, Boolean]) = {
      // TODO spread props
      <.a(^.classSet1M(P.addClasses, classes))(
        if (P.header.isDefined) renderStructuredContent() else C
      )
    }

    def renderSpan(classes: Map[String, Boolean]) = {
      // TODO spread props
      <.span(^.classSet1M(P.addClasses, classes))(
        if (P.header.isDefined) renderStructuredContent() else C
      )
    }

    def renderStructuredContent(): Seq[ReactNode] = {
      val header: ReactNode =
        if (React.isValidElement(P.header.get)) {
          ReactCloneWithProps(P.header.get, Map("className" -> "list-group-item-heading"))
        }
        else {
          <.h4(^.className := "list-group-item-heading", P.header.get)
        }
      val content = <.p(^.className := "list-group-item-text")(C)
      // FIXME in js, it is {header: header, content: content}
      Seq(header, content)
    }

    var classes = P.bsClassSet
    classes += ("active" -> P.active.getOrElse(false))
    classes += ("disabled" -> P.disabled.getOrElse(false))

    if (P.href.isDefined || P.target.isDefined || P.onClick.isDefined)
      renderAnchor(classes)
    else
      renderSpan(classes)

  }.build

}
