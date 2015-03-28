package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLUListElement

import scala.scalajs.js.{UndefOr, undefined}


/**
 * Created by weiyin on 09/03/15.
 */

object CollapsableNav extends BootstrapComponent {
  override type P = CollapsableNav
  override type S = CollapsableState
  override type B = Backend
  override type N = TopNode

  override def defaultProps = CollapsableNav()


  case class CollapsableNav(navbar: Boolean = false, stacked: Boolean = false,
                            justified: Boolean = false, pullRight: Boolean = false,
                            right: Boolean = false,
                            collapsable: UndefOr[Boolean] = undefined,
                            expanded: UndefOr[Boolean] = undefined,
                            activeHref: UndefOr[String] = undefined,
                            activeKey: UndefOr[String] = undefined,
                            role: UndefOr[String] = undefined,
                            eventKey: UndefOr[String] = undefined,
                            id: UndefOr[String] = undefined,
                            bsClass: UndefOr[Classes.Value] = Classes.nav,
                            bsStyle: UndefOr[Styles.Value] = undefined,
                            bsSize: UndefOr[Sizes.Value] = undefined,
                            onSelect: UndefOr[Seq[UndefOr[String]] => Unit] = undefined,
                            addClasses: String = "") extends BsProps with CollapsableProps with MergeableProps[CollapsableNav] {

    def merge(t: Map[String, Any]): CollapsableNav = implicitly[Mergeable[CollapsableNav]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[CollapsableNav]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }


  class Backend(val scope: BackendScope[CollapsableNav, CollapsableState]) extends CollapsableMixin[CollapsableNav] {

    def getCollapsableDOMNode: Option[TopNode] = Some(scope.getDOMNode())

    def getCollapsableDimensionValue: Int = {
      0
    }
  }

  val ulRef = Ref[HTMLUListElement]("ul")

  override val component = ReactComponentB[CollapsableNav]("Nav")
    .initialState(CollapsableState(false, false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def getChildActiveProp(child: ReactNode) = {
      val childPropsAny = getChildProps[Any](child)
      childPropsAny match {
        case props: NavItem.NavItem =>
          props.active ||
            props.eventKey.getOrElse("NA1") == P.activeKey.getOrElse("NA") ||
            props.href.getOrElse("NA1") == P.activeHref.getOrElse("NA")
        case _ => false
      }
    }

    def getChildOnSelectProp(child: ReactNode) = {
      val childPropsAny = getChildProps[Any](child)
      childPropsAny match {
        case props: NavItem.NavItem =>
          props.onSelect
        case _ => undefined
      }
    }

    def renderChildren(child: ReactNode, index: Int) = {
      val (key, _) = getChildKeyAndRef2(child, index)
      cloneWithProps(child, (key, s"nocollapse_$key"), Map(
        "activeKey" -> P.activeKey,
        "activeHref" -> P.activeHref,
        "navItem" -> true
      ))
    }

    def renderCollapsableNavChildren(child: ReactNode, index: Int) = {
      val (key, _) = getChildKeyAndRef2(child, index)

      cloneWithProps(child, (key, s"collapsable_$key"), Map(
        "active" -> getChildActiveProp(child),
        "activeKey" -> P.activeKey,
        "activeHref" -> P.activeHref,
        "onSelect" -> createChainedFunction1(getChildOnSelectProp(child), P.onSelect),
        "navItem" -> true
      ))
    }

    var classes = if (P.collapsable.getOrElse(false)) B.getCollapsableClassSet("") else Map[String, Boolean]()
    classes += ("navbar-collapse" -> P.collapsable.getOrElse(false))

    <.div(^.classSet1M(P.addClasses, classes),
      ValidComponentChildren.map(C,
        if (P.collapsable.getOrElse(false))
          renderCollapsableNavChildren
        else
          renderChildren
      )
    )

  }
    )
    .componentWillUpdate((scope, nextProps, nextState) => scope.backend.onComponentWillUpdate(nextProps, nextState))
    .componentDidUpdate((scope, prevProps, prevState) => scope.backend.onComponentDidUpdate(prevProps, prevState))
    .build

}

