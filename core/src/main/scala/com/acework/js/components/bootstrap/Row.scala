package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 10/03/15.
 */
object Row extends BootstrapComponent {
  override type P = Row
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Row()

  case class Row(componentClass: String = "div", addClasses: String = "") {

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Row]("Row")
    .render { (P, C) =>
    val componentClass = P.componentClass.reactTag
    componentClass(^.classSet1(P.addClasses, "row" -> true))(C)
  }.build

}
