package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */
object Button extends BootstrapMixin {

  case class Props(
                    /*==  start react bootstraps  ==*/
                    active: Boolean = false,
                    disabled: Boolean = false,
                    block: Boolean = false,
                    navItem: Boolean = false,
                    navDropdown: Boolean = false,
                    componentClass: UndefOr[String] = undefined,
                    href: UndefOr[String] = undefined,
                    target: UndefOr[String] = undefined,
                    `type`: String = "button",
                    value: UndefOr[String] = undefined,
                    /*==  end react bootstraps  ==*/
                    onClick: UndefOr[(ReactEvent) => Unit] = undefined,
                    bsClass: UndefOr[Classes.Value] = Classes.btn,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    key: UndefOr[JsNumberOrString] = undefined,
                    ref: UndefOr[Ref] = undefined,
                    addClasses: String = "")
    extends BaseProps

  type PROPS = Props

  val component = ReactComponentB[Props]("Button")
    .render { (P, C) =>

    def renderNavItem(classes: Map[String, Boolean]) = {
      if (P.onClick.isDefined)
        <.li(^.classSet("active" -> P.active), ^.onClick ==> P.onClick.get)(renderAnchor(classes))
      else
        <.li(^.classSet("active" -> P.active))(renderAnchor(classes))
    }

    def renderAnchor(classes: Map[String, Boolean]) = {
      // TODO spread props
      val componentClass = P.componentClass.getOrElse("a").reactTag
      if (P.onClick.isDefined)
        componentClass(^.href := P.href.getOrElse("#"), ^.classSet1M(P.addClasses, classes + ("disabled" -> P.disabled)), ^.role := "button", ^.onClick ==> P.onClick.get)(C)
      else
        componentClass(^.href := P.href.getOrElse("#"), ^.classSet1M(P.addClasses, classes + ("disabled" -> P.disabled)), ^.role := "button")(C)
    }

    def renderButton(classes: Map[String, Boolean]) = {
      val componentClass = P.componentClass.getOrElse("button").reactTag

      // TODO spread props
      if (P.onClick.isDefined)
        componentClass(^.classSet1M(P.addClasses, classes), ^.tpe := P.`type`,
          ^.value := P.value, ^.onClick ==> P.onClick.get)(C)
      else
        componentClass(^.classSet1M(P.addClasses, classes), ^.tpe := P.`type`,
          ^.value := P.value)(C)
    }

    var classes = if (P.navDropdown) Map[String, Boolean]() else getBsClassSet(P)
    classes += ("active" -> P.active)
    classes += ("btn-block" -> P.block)

    if (P.navItem)
      renderNavItem(classes)
    else if (P.href.isDefined || P.target.isDefined || P.navDropdown)
      renderAnchor(classes)
    else
      renderButton(classes)
  }
    .build

  def apply(props: Props, children: ReactNode*) = component(props, children)

  def apply(children: ReactNode*) = component(Props(), children)

  def apply() = component
}
