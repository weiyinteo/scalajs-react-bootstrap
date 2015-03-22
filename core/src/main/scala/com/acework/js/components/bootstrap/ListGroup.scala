package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._

/**
 * Created by weiyin on 10/03/15.
 */
object ListGroup extends BootstrapComponent {
  override type P = ListGroup
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = ListGroup()

  case class ListGroup(fill: Boolean = false, onClick: UndefOr[() => Unit] = undefined) {
    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[ListGroup]("ListGroup")
    .render { (P, C) =>

    def renderListItem(child: ReactNode, index: Int) = {
      ReactCloneWithProps(child, getChildKeyAndRef(child, index))
    }

    <.div(^.className := "list-group")(
      ValidComponentChildren.map(C, renderListItem)
    )
  }.build

}
