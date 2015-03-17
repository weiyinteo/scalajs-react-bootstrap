package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Pager {

  case class Props(onSelect: UndefOr[() => Unit] = undefined, addClasses: String = "")

  val Pager = ReactComponentB[Props]("Pager")
    .render { (P, C) =>
    def renderPageItem(child: ReactNode, index: Int) = {
      ReactCloneWithProps(child, getChildKeyAndRef(child, index)
        ++ Map[String, js.Any](
        "onSelect" -> P.onSelect // FIXME create chain function
      ))
    }

    <.ul(^.classSet1(P.addClasses, "pager" -> true))(
      ValidComponentChildren.map(C, renderPageItem))
  }.build
}
