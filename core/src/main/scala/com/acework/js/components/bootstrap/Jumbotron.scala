package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._


/**
 * Created by weiyin on 10/03/15.
 */
object Jumbotron extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()


  case class Props(addClasses: String = "")

  override val component = ReactComponentB[Props]("Jumbotron")
    .render { (P, C) =>
    <.div(^.classSet1(P.addClasses, "jumbotron" -> true))(C)
  }.build

}
