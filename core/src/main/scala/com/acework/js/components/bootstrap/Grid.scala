package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Grid {

  case class Props(fluid: UndefOr[Boolean] = undefined,
                   componentClass: String = "div", addClasses: String = "")

  val Grid = ReactComponentB[Props]("Grid")
    .render { (P, C) =>

    val classes = if (P.fluid.getOrElse(false)) Map("container-fluid" -> true) else Map("container" -> true)
    val componentClass = P.componentClass.reactTag

    // FIXME spread props
    componentClass(^.classSet1M(P.addClasses, classes))(C)

  }.build
}
