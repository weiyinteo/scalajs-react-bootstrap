package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._


/**
 * Created by weiyin on 10/03/15.
 */
object PageHeader {

  case class Props(addClasses: String = "")

  val PageHeader = ReactComponentB[Props]("PageHeader")
    .render { (P, C) =>
    <.div(^.classSet1(P.addClasses, "page-header" -> true),
      <.h1(C))
  }.build

  def apply(props: Props, children: ReactNode*) = PageHeader(props, children)

  def apply(children: ReactNode*) = PageHeader(Props(), children)

  def apply() = PageHeader
}
