package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.{HTMLDocument, HTMLUListElement}

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}
import com.acework.js.components.bootstrap.Utils._


/**
 * Created by weiyin on 09/03/15.
 */

object Nav extends BootstrapComponent {
  override type P = Nav
  override type S = CollapsableState
  override type B = Backend
  override type N = TopNode

  override def defaultProps = Nav()

  @inline implicit final class ReactExt2_PropsChildren(val _c: PropsChildren) extends AnyVal {
    @inline def map[U](f: ReactNode => U): UndefOr[Object] =
      React.Children.map(_c, (f: js.Function).asInstanceOf[js.Function1[ReactNode, js.Any]])

    @inline def map[U](f: (ReactNode, Int) => U): UndefOr[Object] =
      React.Children.map(_c, (f: js.Function).asInstanceOf[js.Function2[ReactNode, Int, js.Any]])
  }

  case class Nav(navbar: Boolean = false, stacked: Boolean = false,
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
                 addClasses: String = "") extends BsProps with CollapsableProps with MergeableProps[Nav] {

    def merge(t: Map[String, Any]): Nav = implicitly[Mergeable[Nav]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Nav]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }


  class Backend(val scope: BackendScope[Nav, CollapsableState]) extends CollapsableMixin[Nav] {

    def getCollapsableDOMNode: Option[TopNode] = Some(scope.getDOMNode())

    def getCollapsableDimensionValue: Int = {
      if (scope.isMounted() && scope.refs("ul").isDefined) {
        val node = scope.refs("ul").get.asInstanceOf[TopNode]
        val height = node.offsetHeight.toInt
        val computedStyles = node.ownerDocument.asInstanceOf[HTMLDocument].defaultView.getComputedStyle(node, "")
        height + computedStyles.marginTop.toInt + computedStyles.marginBottom.toInt
      }
      else
        0
    }

  }

  val ulRef = Ref[HTMLUListElement]("ul")

  override val component = ReactComponentB[Nav]("Nav")
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

    def renderNavItem(child: ReactNode, index: Int): ReactNode = {
      val keyAndRef = getChildKeyAndRef2(child, index)

      val propsMap = Map[String, Any](
        "active" -> getChildActiveProp(child),
        "activeKey" -> P.activeKey,
        "activeHref" -> P.activeHref,
        "onSelect" -> getOnSelectProps(child),
        "navItem" -> true
      )
      cloneWithProps(child, keyAndRef, propsMap)
    }

    def renderUI() = {
      val classes = P.bsClassSet ++ Map(
        "nav-stacked" -> P.stacked,
        "nav-justified" -> P.justified,
        "navbar-nav" -> P.navbar,
        "pull-right" -> P.pullRight,
        "navbar-right" -> P.right)

      /*
      val navItems = new ListBuffer[ReactNode]
      C.forEach { (n, idx) =>
        if (React.isValidElement(n))
          navItems.append(renderNavItem(n, idx))
      } */

      <.ul(^.classSet1M(P.addClasses, classes), ^.ref := ulRef,
        ValidComponentChildren.map(C, renderNavItem)
      )
    }

    var classes = if (P.collapsable.getOrElse(false)) B.getCollapsableClassSet("") else Map[String, Boolean]()
    classes += ("navbar-collapse" -> P.collapsable.getOrElse(false))

    if (P.navbar && !P.collapsable.getOrElse(false))
      renderUI()
    else {
      val ui = renderUI()
      <.nav(^.classSetM(classes), ^.role := P.role, ui)
    }
  }
    ).build

}

