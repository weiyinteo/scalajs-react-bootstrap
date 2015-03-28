package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement

import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js.{Any => JAny, UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */

object Panel extends BootstrapComponent {
  override type P = Panel
  override type S = CollapsableState
  override type B = Backend
  override type N = TopNode

  override def defaultProps = Panel()

  case class Panel(collapsable: UndefOr[Boolean] = undefined,
                   defaultExpanded: UndefOr[Boolean] = false,
                   expanded: UndefOr[Boolean] = undefined,
                   header: UndefOr[ReactNode] = undefined,
                   footer: UndefOr[String] = undefined,
                   eventKey: UndefOr[String] = undefined,
                   onSelect: UndefOr[(ReactEvent, UndefOr[String]) => Unit] = undefined,
                   id: UndefOr[String] = undefined,
                   bsClass: UndefOr[Classes.Value] = Classes.panel,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   addClasses: String = "") extends BsProps with CollapsableProps with MergeableProps[Panel] {

    def merge(t: Map[String, Any]): Panel = implicitly[Mergeable[Panel]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Panel]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend(val scope: BackendScope[Panel, CollapsableState]) extends CollapsableMixin[Panel] {
    def handleSelect(e: ReactEvent) = {
      // FIXME
      // e.selected = true
      if (scope.props.onSelect.isDefined)
        scope.props.onSelect.get(e, scope.props.eventKey.get)
      else
        e.preventDefault()

      // FIXME
      // if (e.selected)
      handleToggle()
    }

    def handleToggle() = {
      scope.modState(s => s.copy(expanded = !s.expanded))
    }

    def getCollapsableDimensionValue: Int = {
      getCollapsableDOMNode match {
        case Some(panel) =>
          panel.scrollHeight

        case None =>
          0
      }
    }

    def getCollapsableDOMNode: Option[TopNode] = {
      if (scope.isMounted() && scope.refs("panel").isDefined) {
        Some(scope.refs("panel").get.getDOMNode().asInstanceOf[TopNode])
      }
      else
        None
    }
  }

  val panelRef = Ref[HTMLElement]("panel")

  override val component = ReactComponentB[Panel]("Panel")
    .initialStateP(P => {
    val defaultExpanded = P.defaultExpanded.getOrElse(P.expanded.getOrElse(false))
    CollapsableState(expanded = defaultExpanded, collapsing = false)
  }
    )
    .backend(new Backend(_))
    .render { (P, C, S, B) =>

    def renderCollapsableTitle(heading: String) = {
      <.h4(^.className := "panel-title")(renderAnchor(heading))
    }
    def renderHeading(): TagMod = {
      // TODO when header is a Node

      if (P.header.isDefined) {
        if (React.isValidElement(P.header.get)) {
          val header = if (P.collapsable.getOrElse(false)) {
            ReactCloneWithProps(P.header.get, Map[String, JAny](
              "className" -> "panel-title" // ,
              // FIXME "children" -> renderAnchor(P.header.props.children)
            ))
          }
          else {
            ReactCloneWithProps(P.header.get, Map[String, JAny]("className" -> "panel-title"))
          }
          <.div(^.className := "panel-heading")(header)
        }
        else {
          val header: ReactNode = if (P.collapsable.getOrElse(false))
            renderCollapsableTitle(P.header.get.asInstanceOf[String])
          else
            P.header.get
          <.div(^.className := "panel-heading")(header)
        }
      }
      else
        EmptyTag
    }

    def renderAnchor(header: String) = {
      // FIXME check isExpanded
      <.a(^.href := s"#${P.id.getOrElse("")}", ^.className := "", ^.onClick ==> B.handleSelect)(header)
    }

    def renderFooter() = {
      if (P.footer.isDefined)
        <.div(^.className := "panel-footer")(P.footer.get)
      else
        EmptyTag
    }


    def renderBody(): ReactNode = {
      val bodyElements = new ArrayBuffer[ReactNode]

      def shouldRenderFill(c: ReactNode) =
        React.isValidElement(c) && false // TODO: c.props.fill != null

      def getProps: Map[String, JAny] = Map("key" -> bodyElements.length)

      def addPanelChild(c: ReactNode) = {
        val node: ReactNode = ReactCloneWithProps(c, getProps)
        bodyElements += node
      }

      def addPanelBody(c: ReactNode*) = {
        bodyElements += <.div(^.className := "panel-body")(c)
      }

      val numChildren = React.Children.count(C)

      if (numChildren == 0) {
        if (shouldRenderFill(C))
          addPanelChild(C)
        else
          addPanelBody(C)
      }
      else {
        val panelBodyChildren = new ArrayBuffer[ReactNode]

        def maybeRenderPanelBody() = {
          if (panelBodyChildren.length > 0) {
            addPanelBody(panelBodyChildren)
          }
        }

        C.forEach { child =>
          if (shouldRenderFill(child)) {
            maybeRenderPanelBody()

            // separately add the filled element
            addPanelChild(child)
          }
          else
            panelBodyChildren += child
        }
        maybeRenderPanelBody()
      }
      bodyElements
    }

    def renderCollapsableBody(): ReactNode = {
      <.div(^.classSetM(B.getCollapsableClassSet("panel-collapse")), ^.id := P.id, ^.ref := panelRef)(renderBody())
    }

    val classes = P.bsClassSet + ("panel" -> true)

    val hasId = !P.collapsable.getOrElse(false) && P.id.isDefined
    <.div(^.classSet1M(P.addClasses, classes), hasId ?= (^.id := P.id.get))(
      renderHeading(),
      if (P.collapsable.getOrElse(false)) renderCollapsableBody() else renderBody(),
      renderFooter()
    )
  }
    .componentWillUpdate((scope, nextProps, nextState) => scope.backend.onComponentWillUpdate(nextProps, nextState))
    .componentDidUpdate((scope, prevProps, prevState) => scope.backend.onComponentDidUpdate(prevProps, prevState))
    .build

}
