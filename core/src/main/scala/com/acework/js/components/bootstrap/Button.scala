package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */

object Button extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()

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
                    onMouseOver: UndefOr[(ReactEvent) => Unit] = undefined,
                    onMouseOut: UndefOr[(ReactEvent) => Unit] = undefined,
                    onBlur: UndefOr[(ReactEvent) => Unit] = undefined,
                    onFocus: UndefOr[(ReactEvent) => Unit] = undefined,
                    bsClass: UndefOr[Classes.Value] = Classes.btn,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    addClasses: String = "") extends BsProps with MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Props]("Button")
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
      var componentClass = P.componentClass.getOrElse("button").reactTag

      // TODO spread props
      componentClass = componentClass(^.classSet1M(P.addClasses, classes), ^.tpe := P.`type`,
        ^.value := P.value)

      if (P.onClick.isDefined)
        componentClass = componentClass(^.onClick ==> P.onClick.get)

      if (P.onMouseOut.isDefined)
        componentClass = componentClass(^.onMouseOut ==> P.onMouseOut.get)

      if (P.onMouseOver.isDefined)
        componentClass = componentClass(^.onMouseOver ==> P.onMouseOver.get)

      if (P.onBlur.isDefined)
        componentClass = componentClass(^.onBlur ==> P.onBlur.get)

      if (P.onFocus.isDefined)
        componentClass = componentClass(^.onFocus ==> P.onFocus.get)

      componentClass(C)
    }

    var classes = if (P.navDropdown) Map[String, Boolean]() else P.bsClassSet
    classes ++= Map("active" -> P.active, "btn-block" -> P.block)

    if (P.navItem)
      renderNavItem(classes)
    else if (P.href.isDefined || P.target.isDefined || P.navDropdown)
      renderAnchor(classes)
    else
      renderButton(classes)
  }
    .build

}
