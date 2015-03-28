package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.ReactComponentC.ReqProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */

object Button extends BootstrapComponent {
  override type P = Button
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Button()

  case class Button(
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
                     addClasses: String = "") extends BsProps with MergeableProps[Button] {

    def merge(t: Map[String, Any]): Button = implicitly[Mergeable[Button]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Button]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Button]("Button")
    .render { (P, C) =>

    def renderNavItem(classes: Map[String, Boolean]) = {
      var componentClass = <.li(^.classSet("active" -> P.active))

      componentClass = spreadEventHandlers(componentClass)
      componentClass(renderAnchor(classes))
    }

    def spreadEventHandlers(node: ReactTag): ReactTag = {
      var componentClass = node
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

      componentClass
    }

    def renderAnchor(classes: Map[String, Boolean]) = {
      // TODO spread props
      var componentClass = P.componentClass.getOrElse("a").reactTag
      componentClass = componentClass(^.href := P.href.getOrElse("#"),
        ^.target := P.target, ^.tpe := P.`type`,
        ^.classSet1M(P.addClasses, classes + ("disabled" -> P.disabled)),
        ^.role := "button")

      componentClass = spreadEventHandlers(componentClass)
      componentClass(C)
    }

    def renderButton(classes: Map[String, Boolean]) = {
      var componentClass = P.componentClass.getOrElse("button").reactTag

      // TODO spread props
      componentClass = componentClass(^.classSet1M(P.addClasses, classes), ^.tpe := P.`type`,
        ^.value := P.value, ^.disabled := P.disabled)

      componentClass = spreadEventHandlers(componentClass)
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
