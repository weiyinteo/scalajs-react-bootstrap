package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */

import com.acework.js.components.bootstrap.Utils._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

object DropdownMenu extends BootstrapComponent {
  override type P = Props
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Props()

  case class Props(
                    /*==  start react bootstraps  ==*/
                    ariaLabelledBy: UndefOr[String] = undefined,
                    pullRight: UndefOr[Boolean] = undefined,
                    onSelect: UndefOr[(String) => Unit] = undefined,
                    /*==  end react bootstraps  ==*/
                    addClasses: String = "") extends MergeableProps[Props] {

    def merge(t: Map[String, Any]): Props = implicitly[Mergeable[Props]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Props]].toMap(this)
  }

  override val component = ReactComponentB[Props]("DropdownMenu")
    .render((P, C) => {

    def renderMenuItem(child: ReactNode, index: Int) = {
      val keyAndRef = getChildKeyAndRef(child, index)
      if (P.onSelect.isDefined)
        ReactCloneWithProps(child, keyAndRef ++
          Map[String, js.Any](
            "onSelect" -> P.onSelect.get) // FIXME createChainedFunction0(childProps.onSelect, handleOptionSelect)
        )
      else
        ReactCloneWithProps(child, keyAndRef)
    }

    <.ul(^.classSet1("dropdown-menu", "dropdown-menu-right" -> P.pullRight.getOrElse(false)),
      ^.role := "menu", ^.aria.labelledby := P.ariaLabelledBy)(
        ValidComponentChildren.map(C, renderMenuItem)
      )
  })
    .build

}
