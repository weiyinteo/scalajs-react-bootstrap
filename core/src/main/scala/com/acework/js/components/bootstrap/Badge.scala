package com.acework.js.components.bootstrap

import Utils.ValidComponentChildren
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Badge {

  case class Props(pullRight: UndefOr[Boolean] = undefined, addClasses: String = "")

  val Badge = ReactComponentB[Props]("Badge")
    .render { (P, C) =>

    def hasContent = {
      ValidComponentChildren.hasValidComponents(C) ||
        C.isInstanceOf[String] ||
        C.isInstanceOf[Int]
    }

    <.span(^.className := P.addClasses,
      ^.classSet("pull-right" -> P.pullRight.getOrElse(false), "badge" -> hasContent))(C)

  }.build

  def apply(props: Props, children: ReactNode) = Badge(props, children)

  def apply(children: ReactNode) = Badge(Props(), children)
}
