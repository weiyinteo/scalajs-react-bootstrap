package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object ListGroup {

  case class Props(fill: Boolean = false, onClick: UndefOr[() => Unit] = undefined)

  val ListGroup = ReactComponentB[Props]("ListGroup")
    .render { (P, C) =>

    def renderListItem(child: ReactNode, index: Int) = {
      ReactCloneWithProps(child, getChildKeyAndRef(child, index))
    }

    <.div(^.className := "list-group")(
      ValidComponentChildren.map(C, renderListItem)
    )
  }.build

  def apply(props: Props, children: ReactNode*) = ListGroup(props, children)
  def apply(children: ReactNode*) = ListGroup(Props(), children)
}
