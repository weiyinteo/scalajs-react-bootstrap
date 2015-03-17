package com.acework.js.components.bootstrap

import Utils._
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLUListElement

import scala.collection.mutable.ListBuffer
import scala.scalajs.js
import scala.scalajs.js._


/**
 * Created by weiyin on 09/03/15.
 */

object Nav extends BootstrapMixin {
  type PROPS = Props

  @inline implicit final class ReactExt2_PropsChildren(val _c: PropsChildren) extends AnyVal {
    @inline def map[U](f: ReactNode => U): UndefOr[Object] =
      React.Children.map(_c, (f: js.Function).asInstanceOf[js.Function1[ReactNode, js.Any]])

    @inline def map[U](f: (ReactNode, Int) => U): UndefOr[Object] =
      React.Children.map(_c, (f: js.Function).asInstanceOf[js.Function2[ReactNode, Int, js.Any]])
  }

  case class Props(navbar: Boolean = false, stacked: Boolean = false,
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
                   key: UndefOr[String] = undefined,
                   ref: UndefOr[Ref] = undefined,
                   onSelect: UndefOr[() => Unit] = undefined,
                   addClasses: String = "") extends BaseProps with CollapsableProps


  class Backend(val scope: BackendScope[Props, CollapsableState]) extends CollapsableMixin[Props] {

    def getCollapsableDOMNode: Option[TopNode] = Some(scope.getDOMNode())

    def getCollapsableDimensionValue : Double = {
      if (scope.isMounted() && scope.refs != null && scope.refs("ul") != null) {
        val node = scope.refs("ul").asInstanceOf[TopNode]
        val height = node.offsetHeight
        // FIXME
        //node.ownerDocument.defaultView.getComptedStyle(elem, null)
        height
      }
      else
        0
    }
  }

  val ulRef = Ref[HTMLUListElement]("ul")

  val nav = ReactComponentB[Props]("Nav")
    .initialState(CollapsableState(false, false))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def renderNavItem(child: ReactNode, idx: Int): ReactNode = {
      ReactCloneWithProps(child, Map("active" -> true))
      /*
        Map("active" -> getChildActiveProp(child),
        "activeKey" -> P.activeKey,
        "activeHref" -> P.activeHref,
        "onSelect" -> "FIXME",
        "ref" -> child.ref,
        "key" -> child.key, // or idx
        "navItem" -> true
      ) */
    }

    def renderUI() = {
      var classes = getBsClassSet(P)
      classes += ("nav-stacked" -> P.stacked)
      classes += ("nav-justified" -> P.justified)
      classes += ("navbar-nav" -> P.navbar)
      classes += ("pull-right" -> P.pullRight)
      classes += ("navbar-right" -> P.right)

      val navItems = new ListBuffer[ReactNode]
      C.forEach { (n, idx) =>
        if (React.isValidElement(n))
          navItems.append(renderNavItem(n, idx))
      }

      <.ul(^.classSetM(classes), ^.ref := ulRef)(navItems)
    }

    var classes = if (P.collapsable.getOrElse(false)) B.getCollapsableClassSet("") else Map[String, Boolean]()
    classes += ("navbar-collapse" -> P.collapsable.getOrElse(false))

    if (P.navbar && !P.collapsable.getOrElse(false))
      renderUI()
    else {
      val ui = renderUI()
      <.nav(^.classSetM(classes), ui)
    }
  }
    ).build

  def apply(props: Props, children: ReactNode*) = nav(props, children)
}

