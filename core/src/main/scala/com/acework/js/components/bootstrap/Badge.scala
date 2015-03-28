package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils.ValidComponentChildren
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Badge extends BootstrapComponent {
  override type P = Badge
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  def defaultProps = Badge()

  case class Badge(pullRight: UndefOr[Boolean] = undefined, addClasses: String = "") {
    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Badge]("Badge")
    .render { (P, C) =>

    def hasContent = {
      var res = ValidComponentChildren.hasValidComponents(C)
      if (!res) {
        C.forEach { c =>
          if (js.typeOf(c) == "string" || js.typeOf(c) == "number")
            res = true
        }
      }
      res
    }

    // FIXME spread props
    <.span(^.className := P.addClasses,
      ^.classSet("pull-right" -> P.pullRight.getOrElse(false), "badge" -> hasContent))(C)

  }.build

}
