package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._


/**
 * Created by weiyin on 10/03/15.
 */
object Jumbotron extends BootstrapComponent {
  override type P = Jumbotron
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Jumbotron()


  case class Jumbotron(addClasses: String = ""){

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Jumbotron]("Jumbotron")
    .render { (P, C) =>
    <.div(^.classSet1(P.addClasses, "jumbotron" -> true))(C)
  }.build

}
