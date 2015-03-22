package com.acework.js.components.bootstrap

import com.acework.js.logger._
import com.acework.js.utils.{EventListener, Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.{MouseEvent, document}
import org.scalajs.dom.raw.{HTMLElement, KeyboardEvent}

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 09/03/15.
 */
object Modal extends BootstrapComponent {
  override type P = Modal
  override type S = Unit
  override type B = Backend
  override type N = TopNode

  override def defaultProps = throw new RuntimeException("noRequestHide must be provided")

  // header and footer are functions, so that they can get access to the the hide() function for their buttons
  case class Modal(
                    onRequestHide: () => Unit,
                    backdrop: Boolean = true,
                    keyboard: Boolean = true,
                    animation: Boolean = true,
                    closeButton: Boolean = true,
                    container: OverlayContainer = new OverlayContainer {},
                    bsClass: UndefOr[Classes.Value] = Classes.modal,
                    bsStyle: UndefOr[Styles.Value] = Styles.default,
                    bsSize: UndefOr[Sizes.Value] = undefined,
                    onClick: (ReactEvent) => Unit = _ => (),
                    title: UndefOr[ReactNode] = undefined,
                    footer: UndefOr[() => ReactNode] = undefined,
                    closed: () => Unit = () => (),
                    addClasses: String = "") extends BsProps
  with MergeableProps[Modal] with OverlayProps {

    def merge(t: Map[String, Any]): Modal = implicitly[Mergeable[Modal]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Modal]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }


  class Backend(val scope: BackendScope[Modal, Unit]) extends FadeMixin[Modal, Unit] {
    var _onDocumentKeyupListener: Option[EventListener] = None

    def handleBackdropClick(e: ReactEvent): Unit = {
      if (e.target == e.currentTarget)
        scope.props.onRequestHide()
    }

    override def onComponentDidMount(): Unit = {
      _onDocumentKeyupListener = Some(EventListener.listen(document, "keyup", handleDocumentKeyUp _))

      val container = scope.props.container.getDOMNode
      val containerClasses = container.className.split(" ")
        .map(_.trim).filter(_ != "modal-open") ++ "modal-open"
      container.className = containerClasses.mkString(" ")

      if (scope.props.backdrop)
        iosClickHack()

      super.onComponentDidMount()
    }

    def handleDocumentKeyUp(e: KeyboardEvent) = {
      if (scope.props.keyboard && e.keyCode == 27)
        scope.props.onRequestHide()
    }

    def iosClickHack() = {
      // IOS only allows click events to be delegated to the document on elements
      // it considers 'clickable' - anchors, buttons, etc. We fake a click handler on the
      // DOM nodes themselves. Remove if handled by React: https://github.com/facebook/react/issues/1169

      /*
      for (refName <- Seq("modal", "backdrop")) {
        val ref = scope.refs(refName)
        if (ref.isDefined) {
          val element = ref.get.getDOMNode().asInstanceOf[HTMLElement]
          element.onclick = (x: MouseEvent) => ()
        }
      } */
    }
  }

  override val component = ReactComponentB[Modal]("Modal")
    .stateless
    .backend(new Backend(_))
    .render((P, C, _, B) => {

    val onClickHandler = if (P.backdrop) B.handleBackdropClick _ else (x: ReactEvent) => ()

    def renderTitle(): TagMod = {
      if (P.title.isDefined) {
        val title = P.title.get
        if (React.isValidElement(title))
          title
        else
          <.h4(^.className := "modal-title")(title)
      }
      else
        EmptyTag
    }

    def renderHeader() = {
      val closeButton: TagMod =
        if (P.closeButton)
          <.button(^.`type` := "button", ^.className := "close", ^.aria.hidden := "true",
            ^.onClick --> P.onRequestHide(), ^.dangerouslySetInnerHtml("&times"))
        else
          EmptyTag

      val style = P.bsStyle.toString
      val classes = Map("modal-header" -> true,
        s"bg-$style" -> true,
        s"text-$style" -> true
      )
      <.div(^.classSetM(classes), closeButton, renderTitle())
    }

    def renderBackdrop(modal: ReactTag) = {
      val backdropRef = Ref[HTMLElement]("backdrop")
      <.div(
        <.div(
          ^.classSet1("modal-backdrop", "fade" -> P.animation,
            "in" -> (!P.animation || true)), // FIXME document.querySelectorAll
          ^.ref := backdropRef,
          ^.onClick ==> onClickHandler),
        modal
      )
    }

    var dialogClasses = P.bsClassSet.filter(p => p._1 != "modal")
    dialogClasses += ("modal-dialog" -> true)

    val modalRef = Ref("modal")
    // FIXME spread props
    val modal =
      <.div(
        ^.tabIndex := -1,
        ^.role := "dialog",
        ^.display := "block",
        ^.classSet1("modal " + P.addClasses,
          "fade" -> P.animation,
          "in" -> (!P.animation || true) // FIXME !document.querySelectorAll)
        ),
        ^.onClick ==> onClickHandler,
        ^.ref := modalRef,
        <.div(^.classSetM(dialogClasses),
          <.div(^.className := "modal-content", ^.overflow := "hidden",
            if (P.title.isDefined) renderHeader() else EmptyTag,
            C
          )
        )
      )
    if (P.backdrop)
      renderBackdrop(modal)
    else
      modal
  })
    .componentDidMount(scope => {
    scope.backend.onComponentDidMount()
  })
    .componentDidUpdate((scope, prevProps, _) => {
    if (scope.props.backdrop && scope.props.backdrop != prevProps.backdrop)
      scope.backend.iosClickHack()
  })
    .componentWillUnmount(scope => {
    if (scope.backend._onDocumentKeyupListener.isDefined) {
      scope.backend._onDocumentKeyupListener.get.remove()
      scope.backend._onDocumentKeyupListener = None
    }

    val container = scope.props.container.getDOMNode
    val containerClasses = container.className.split(" ")
      .map(_.trim).filter(_ != "modal-open")
    container.className = containerClasses.mkString(" ")

  })
    .build

}
