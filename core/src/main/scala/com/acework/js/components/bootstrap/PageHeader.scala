package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._


/**
 * Created by weiyin on 10/03/15.
 */
object PageHeader extends BootstrapComponent {
  override type P = PageHeader
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = PageHeader()

  case class PageHeader(addClasses: String = "") {

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[PageHeader]("PageHeader")
    .render { (P, C) =>
    <.div(^.classSet1(P.addClasses, "page-header" -> true),
      <.h1(C))
  }.build

}
