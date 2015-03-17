package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._


/**
 * Created by weiyin on 10/03/15.
 */
object Jumbotron {

  case class Props(addClasses: String = "")

  val Jumbotron = ReactComponentB[Props]("Jumbotron")
    .render { (P, C) =>
    <.div(^.classSet1(P.addClasses, "jumbotron" -> true))(C)
  }.build

  def apply(props: Props, children: ReactNode*) = Jumbotron(props, children)

  def apply(children: ReactNode*) = Jumbotron(Props(), children)

  def apply() = Jumbotron(Props())
}
