package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 10/03/15.
 */
object Row {

  case class Props(componentClass: String = "div", addClasses: String = "")

  val Row = ReactComponentB[Props]("Row")
    .render { (P, C) =>
    val componentClass = P.componentClass.reactTag
    componentClass(^.classSet1(P.addClasses, "row" -> true))(C)
  }.build


  def apply(props: Props, children: ReactNode*) = Row(props, children)

  def apply(children: ReactNode*) = Row(Props(), children)
}
