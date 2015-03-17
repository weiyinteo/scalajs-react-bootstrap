package com.acework.js.components.bootstrap

import Utils._
import com.acework.js.logger._
import com.acework.js.utils.EventListener
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.document
import org.scalajs.dom.raw.{HTMLElement, KeyboardEvent}

import scala.scalajs.js
import scala.scalajs.js._

/**
 * Created by weiyin on 09/03/15.
 */
object Modal extends BootstrapMixin {
  type PROPS = Props

  // header and footer are functions, so that they can get access to the the hide() function for their buttons
  case class Props(backdrop: Boolean = true,
                   keyboard: Boolean = true,
                   animation: Boolean = true,
                   closeButton: Boolean = true,
                   bsClass: UndefOr[Classes.Value] = Classes.modal,
                   bsStyle: UndefOr[Styles.Value] = Styles.default,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   onClick: () => Unit = () => (),
                   onRequestHide: () => Unit = () => (),
                   title: UndefOr[ReactNode] = undefined,
                   footer: UndefOr[() => ReactNode] = undefined,
                   closed: () => Unit = () => (),
                   addClasses: String = "") extends BaseProps

  class Backend(val scope: BackendScope[Props, Unit]) extends FadeMixin[Props, Unit] {
    def hide(): Unit = {
      // instruct Bootstrap to hide the modal
      jQuery(scope.getDOMNode()).modal("hide")
    }

    // jQuery event handler to be fired when the modal has been hidden
    def hidden(e: JQueryEventObject): js.Any = {
      // inform the owner of the component that the modal was closed/hidden
      scope.props.closed()
    }


    def handleBackdropClick(e: ReactEvent): Unit = {
      log.debug(s"handleBackdropClick: $e")
      if (e.target == e.currentTarget)
        scope.props.onRequestHide()
    }

    override def onComponentDidMount(): Unit = {
      EventListener.listen(document, "keyup", handleDocumentKeyUp)

      // FIXME scope.props.container.getDOMNODE() || document.body
      val container = document.body
      container.className = if (container.className.nonEmpty) s"${container.className}} modal-open" else "modal-open"

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

      // TODO
      // this.refs.modal.getDOMNode().onclick = function() {};
      //this.refs.backdrop.getDOMNode().onclick = function() {};
    }
  }

  val component = ReactComponentB[Props]("Modal")
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
          ^.onClick ==> onClickHandler)(modal)
      )
    }

    var dialogClasses = getBsClassSet(P).filter(p => p._1 != "modal")
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
          <.div(^.className := "modal-content", ^.overflow := "hidden"),
          if (P.title.isDefined) renderHeader() else EmptyTag,
          C
        )
      )
    if (P.backdrop)
      renderBackdrop(modal)
    else
      modal
  })
    .componentDidMount(scope => {
    val P = scope.props

    scope.backend.onComponentDidMount()

    import scala.language.implicitConversions
    implicit def jq2bootstrap(jq: JQuery): BootstrapJQuery = jq.asInstanceOf[BootstrapJQuery]

    // instruct Bootstrap to show the modal
    jQuery(scope.getDOMNode()).modal(js.Dynamic.literal("backdrop" -> P.backdrop, "keyboard" -> P.keyboard, "show" -> true))

    jQuery(scope.getDOMNode()).on("hidden.bs.modal", null, null, scope.backend.hidden _)
  })
    .componentWillUnmount(scope => {

  })
    .build

  def apply(props: Props, children: ReactNode*) = component(props, children)

  def apply() = component
}
