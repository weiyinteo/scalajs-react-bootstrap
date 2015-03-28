package com.acework.js.components.bootstrap

import com.acework.js.components.bootstrap.Utils._
import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}


/**
 * Created by weiyin on 09/03/15.
 */

object NavBar extends BootstrapComponent {
  override type P = NavBar
  override type S = State
  override type B = Backend
  override type N = TopNode

  override def defaultProps = NavBar()

  case class NavBar(
                     /*==  start react bootstraps  ==*/
                     fixedTop: UndefOr[Boolean] = undefined,
                     fixedBottom: UndefOr[Boolean] = undefined,
                     staticTop: UndefOr[Boolean] = undefined,
                     inverse: UndefOr[Boolean] = undefined,
                     fluid: UndefOr[Boolean] = undefined,
                     role: UndefOr[String] = "navigation",
                     componentClass: UndefOr[String] = "Nav",
                     brand: UndefOr[ReactNode] = undefined,
                     toggleButton: UndefOr[ReactNode] = undefined,
                     toggleNavKey: UndefOr[String] = undefined, // FIXME String or Number
                     onToggle: UndefOr[() => Unit] = undefined,
                     navExpanded: UndefOr[Boolean] = undefined,
                     defaultNavExpanded: UndefOr[Boolean] = undefined,
                     bsClass: UndefOr[Classes.Value] = Classes.navbar,
                     bsStyle: UndefOr[Styles.Value] = Styles.default,
                     bsSize: UndefOr[Sizes.Value] = undefined,
                     addClasses: String = "") extends BsProps with MergeableProps[NavBar] {

    def merge(t: Map[String, Any]): NavBar = implicitly[Mergeable[NavBar]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[NavBar]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  case class State(navExpanded: Boolean)

  case class Backend(scope: BackendScope[NavBar, State]) {
    var _isChanging = false

    def handleToggle() = {
      if (scope.props.onToggle.isDefined) {
        _isChanging = true
        scope.props.onToggle.get()
        _isChanging = false
      }

      scope.modState(s => s.copy(navExpanded = !s.navExpanded))
    }

    def isNavExpanded = {
      if (scope.props.navExpanded.isDefined)
        scope.props.navExpanded.get
      else
        scope.state.navExpanded
    }
  }


  override val component = ReactComponentB[NavBar]("NavBar")
    .initialStateP(P => State(navExpanded = P.defaultNavExpanded.getOrElse(false)))
    .backend(new Backend(_))
    .render((P, C, S, B) => {

    def renderHeader(): TagMod = {
      val brand: TagMod =
        if (P.brand.isDefined) {
          if (React.isValidElement(P.brand.get)) {
            cloneWithProps(P.brand.get, (undefined, undefined), Map("addClasses" -> "navbar-brand"))
          }
          else
            <.span(^.className := "navbar-brand", P.brand.get)
        }
        else
          EmptyTag

      val toggleButton = if (P.toggleButton.isDefined || P.toggleNavKey.isDefined)
        renderToggleButton()
      else
        EmptyTag

      <.div(^.className := "navbar-header",
        brand,
        toggleButton
      )
    }

    def renderChild(child: ReactNode, index: Int) = {
      val dynChild = child.asInstanceOf[js.Dynamic]
      val childProps = dynChild.props

      val keyAndRef = getChildKeyAndRef2(child, index)
      cloneWithProps(child, keyAndRef, Map(
        "navbar" -> true,
        "collapsable" -> (P.toggleNavKey.isDefined && P.toggleNavKey.get == childProps.eventKey),
        "expanded" -> (P.toggleNavKey.isDefined && P.toggleNavKey.get == childProps.eventKey && B.isNavExpanded)
      ))
    }

    def renderToggleButton(): TagMod = {
      if (P.toggleButton.isDefined) {
        if (React.isValidElement(P.toggleButton))
          ReactCloneWithProps(P.toggleButton.get, Map[String, js.Any]("className" -> "navbar-toggle",
            "onClick" -> (() => B.handleToggle()) // FIXME createChainedFunction0(B.handleToggle(_), P.toggleButton.props.onClick))
          ))
        else {
          val children: TagMod = if (P.toggleButton.isDefined)
            P.toggleButton.get
          else
            Seq(
              <.span(^.className := "sr-only", ^.key := 0, "Toggle navigation"),
              <.span(^.className := "icon-bar", ^.key := 1),
              <.span(^.className := "icon-bar", ^.key := 2),
              <.span(^.className := "icon-bar", ^.key := 3)
            )

          <.button(^.className := "navbar-toggle", ^.tpe := "botton", ^.onClick --> B.handleToggle)(children)
        }
      }
      else
        EmptyTag
    }



    val classes = P.bsClassSet ++ Map(
      "navbar-fixed-top" -> P.fixedTop.getOrElse(false),
      "navbar-fixed-bottom" -> P.fixedBottom.getOrElse(false),
      "navbar-static-top" -> P.staticTop.getOrElse(false),
      "navbar-inverse" -> P.inverse.getOrElse(false))

    val header: TagMod =
      if (P.brand.isDefined || P.toggleButton.isDefined || P.toggleNavKey.isDefined)
        renderHeader()
      else
        EmptyTag

    val className = if (P.fluid.getOrElse(false)) "container-fluid" else "container"
    if (P.componentClass.isDefined && P.componentClass.get != "Nav") {
      val componentClass = P.componentClass.get.reactTag
      componentClass(^.classSet1M(P.addClasses, classes), ^.role := P.role,
        <.div(^.className := className,
          header,
          ValidComponentChildren.map(C, renderChild)
        )
      )
    }
    else {
      val addClasses = classes.filter(_._2).map(_._1).mkString(" ")
      Nav.Nav(addClasses = s"$addClasses ${P.addClasses}".trim, role = P.role)(
        <.div(^.className := className,
          header,
          ValidComponentChildren.map(C, renderChild)
        )
      )
    }

  }
    ).build

}

