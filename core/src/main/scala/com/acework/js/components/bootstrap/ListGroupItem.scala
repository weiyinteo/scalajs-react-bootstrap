package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object ListGroupItem extends BootstrapMixin {
  type PROPS = Props

  case class Props(active: UndefOr[Boolean] = undefined,
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
                   addClasses: String = "") extends BaseProps


  val ListGroupItem = ReactComponentB[Props]("ListGroupItem")
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

    var classes = getBsClassSet(P)
    classes += ("active" -> P.active.getOrElse(false))
    classes += ("disabled" -> P.disabled.getOrElse(false))

    if (P.href.isDefined || P.target.isDefined || P.onClick.isDefined)
      renderAnchor(classes)
    else
      renderSpan(classes)

  }.build

  def apply(props: Props, children: ReactNode*) = ListGroupItem(props, children)

  def apply(children: ReactNode*) = ListGroupItem(Props(), children)
}
