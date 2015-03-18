package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 10/03/15.
 */
object Row extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()

  case class Props(componentClass: String = "div", addClasses: String = "")

  override val component = ReactComponentB[Props]("Row")
    .render { (P, C) =>
    val componentClass = P.componentClass.reactTag
    componentClass(^.classSet1(P.addClasses, "row" -> true))(C)
  }.build

}
