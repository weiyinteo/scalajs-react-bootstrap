package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object Col {

  case class Col(xs: UndefOr[Int] = undefined,
                   sm: UndefOr[Int] = undefined,
                   md: UndefOr[Int] = undefined,
                   lg: UndefOr[Int] = undefined,
                   xsOffset: UndefOr[Int] = undefined,
                   smOffset: UndefOr[Int] = undefined,
                   mdOffset: UndefOr[Int] = undefined,
                   lgOffset: UndefOr[Int] = undefined,
                   xsPush: UndefOr[Int] = undefined,
                   smPush: UndefOr[Int] = undefined,
                   mdPush: UndefOr[Int] = undefined,
                   lgPush: UndefOr[Int] = undefined,
                   xsPull: UndefOr[Int] = undefined,
                   smPull: UndefOr[Int] = undefined,
                   mdPull: UndefOr[Int] = undefined,
                   lgPull: UndefOr[Int] = undefined,
                   componentClass: String = "div", addClasses: String = "") {

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  val component = ReactComponentB[Col]("Col")
    .render { (P, C) =>
    var classes = Map[String, Boolean]()

    def extractClasses(sizeName: String, size: UndefOr[Int], offset: UndefOr[Int], push: UndefOr[Int], pull: UndefOr[Int]) {

      if (size.isDefined)
        classes += (s"col-$sizeName-${size.get}" -> true)

      if (offset.isDefined && offset.get >= 0)
        classes += (s"col-$sizeName-offset-${offset.get}" -> true)

      if (push.isDefined && push.get >= 0)
        classes += (s"col-$sizeName-push-${push.get}" -> true)

      if (pull.isDefined && pull.get >= 0)
        classes += (s"col-$sizeName-pull-${pull.get}" -> true)
    }

    extractClasses("xs", P.xs, P.xsOffset, P.xsPush, P.xsPull)
    extractClasses("sm", P.sm, P.smOffset, P.smPush, P.smPull)
    extractClasses("md", P.md, P.mdOffset, P.mdPush, P.mdPull)
    extractClasses("lg", P.lg, P.lgOffset, P.lgPush, P.lgPull)

    val componentClass = P.componentClass.reactTag
    componentClass(^.classSet1M(P.addClasses, classes))(C)
  }.build

}
