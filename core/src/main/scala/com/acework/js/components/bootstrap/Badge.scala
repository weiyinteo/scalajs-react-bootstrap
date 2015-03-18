package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils.ValidComponentChildren
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Badge extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  def defaultProps = Props()

  case class Props(pullRight: UndefOr[Boolean] = undefined, addClasses: String = "")

  override val component = ReactComponentB[Props]("Badge")
    .render { (P, C) =>

    def hasContent = {
      ValidComponentChildren.hasValidComponents(C) ||
        C.isInstanceOf[String] ||
        C.isInstanceOf[Int]
    }

    <.span(^.className := P.addClasses,
      ^.classSet("pull-right" -> P.pullRight.getOrElse(false), "badge" -> hasContent))(C)

  }.build

}
