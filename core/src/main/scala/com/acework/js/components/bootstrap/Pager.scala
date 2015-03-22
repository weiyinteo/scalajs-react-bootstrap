package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Pager extends BootstrapComponent {
  override type P = Pager
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Pager()

  case class Pager(onSelect: UndefOr[Seq[UndefOr[String]] => Unit] = undefined, addClasses: String = "")
    extends MergeableProps[Pager] {

    def merge(t: Map[String, Any]): Pager = implicitly[Mergeable[Pager]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Pager]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Pager]("Pager")
    .render { (P, C) =>

    def getOnSelectProps(child: ReactNode): UndefOr[Seq[UndefOr[String]] => Unit] = {
      val childPropsAny = getChildProps[Any](child)
      childPropsAny match {
        case props: NavItem.NavItem =>
          if (props.onSelect.isDefined)
            props.onSelect
          else
            P.onSelect
        case _ => undefined
      }
    }

    def renderPageItem(child: ReactNode, index: Int) = {
      val keyAndRef = getChildKeyAndRef2(child, index)
      val propsMap = Map("onSelect" -> getOnSelectProps(child))
      cloneWithProps(child, keyAndRef, propsMap)
    }

    <.ul(^.classSet1(P.addClasses, "pager" -> true))(
      ValidComponentChildren.map(C, renderPageItem))
  }.build
}
