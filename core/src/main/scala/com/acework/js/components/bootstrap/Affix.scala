package com.acework.js.components.bootstrap

import com.acework.js.utils.EventListener
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.{Event, document, window}

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}
import Utils._

/**
 * Created by weiyin on 10/03/15.
 */
object Affix {

  case class State(affixClass: String = "affix-top", affixPositionTop: Double = 0)

  case class Affix(offset: UndefOr[Double] = undefined,
                   offsetTop: UndefOr[Double] = undefined,
                   offsetBottom: UndefOr[Double] = undefined,
                   role: UndefOr[String] = undefined,
                   affixed: UndefOr[String] = undefined,
                   addClasses: String = "") {
    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  val affixRegexp = "(affix-top|affix-bottom|affix)".r

  class Backend(val scope: BackendScope[Affix, State]) {
    var _onWindowScrollListener: UndefOr[EventListener] = undefined
    var _onDocumentClickListener: UndefOr[EventListener] = undefined


    var pinnedOffset: UndefOr[Double] = undefined
    var unpin: UndefOr[Double] = undefined
    var affixed: UndefOr[String] = undefined


    def checkPositionWithEventLoop() = {
      js.timers.setTimeout(0)(checkPosition())
    }

    def getPinnedOffset(node: TopNode) = {
      if (pinnedOffset.isDefined)
        pinnedOffset.get
      else {
        var className = affixRegexp.replaceAllIn(node.className, "")
        className = if (className.length > 0) className + " affix" else className + "affix"
        node.className = className

        pinnedOffset = getOffset(node).top - window.pageYOffset
        pinnedOffset.get
      }
    }

    def checkPosition() = {
      if (scope.isMounted()) {
        val domNode = scope.getDOMNode()
        // documentElement has no offsetHeight
        val scrollHeight = document.body.offsetHeight
        val scrollTop = window.pageYOffset
        var position = getOffset(domNode)

        if (scope.props.affixed.getOrElse("NA") == "top")
          position = position.copy(top = position.top + scrollTop)

        var offsetTop = if (scope.props.offsetTop.isDefined)
          scope.props.offsetTop
        else
          scope.props.offset

        var offsetBottom = if (scope.props.offsetBottom.isDefined)
          scope.props.offsetBottom
        else
          scope.props.offset

        if (offsetTop.isDefined || offsetBottom.isDefined) {
          if (!offsetTop.isDefined)
            offsetTop = 0.0
          if (!offsetBottom.isDefined)
            offsetBottom = 0.0
        }

        var affix: UndefOr[String] = undefined

        if (this.unpin.isDefined && (scrollTop + unpin.get < position.top)) {
          affix = undefined
        } else if (offsetBottom.isDefined && (position.top + domNode.offsetHeight >= scrollHeight - offsetBottom.get)) {
          affix = "bottom"
        } else if (offsetTop.isDefined && (scrollTop <= offsetTop.get)) {
          affix = "top"
        }

        if (affixed.getOrElse("NA1") != affix.getOrElse("NA2")) {
          if (unpin.isDefined)
            domNode.style.top = ""

          val affixType = if (affix.isDefined) s"affix-${affix.get}" else "affix"
          affixed = affix

          if (affix.getOrElse("NA") == "bottom")
            unpin = getPinnedOffset(domNode)
          else
            unpin = undefined

          var affixPositionTop = 0.0
          if (affix.getOrElse("NA") == "bottom") {
            domNode.className = affixRegexp.replaceAllIn(domNode.className, "affix-bottom")
            affixPositionTop = scrollHeight - offsetBottom.get - domNode.offsetHeight - getOffset(domNode).top
          }

          scope.modState(_.copy(affixClass = affixType, affixPositionTop = affixPositionTop))
        }
      }
    }

    def onComponentDidMount() = {
      _onWindowScrollListener = EventListener.listen(window, "scroll", (_: Event) => checkPosition())
      _onDocumentClickListener = EventListener.listen(document, "click", (_: Event) => checkPositionWithEventLoop())
    }

    def onComponentWillUnmount() = {
      if (_onDocumentClickListener.isDefined) {
        _onDocumentClickListener.get.remove()
        _onDocumentClickListener = undefined
      }

      if (_onWindowScrollListener.isDefined) {
        _onWindowScrollListener.get.remove()
        _onWindowScrollListener = undefined
      }
    }

    def onComponentDidUpdate(prevProps: Affix, prevState: State) = {
      if (prevState.affixClass == scope.state.affixClass)
        checkPositionWithEventLoop()
    }
  }

  val component = ReactComponentB[Affix]("Affix")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    <.div(^.classSet1(P.addClasses, S.affixClass -> true), ^.top := S.affixPositionTop,
      C
    )

  })
    .componentDidMount(scope => scope.backend.onComponentDidMount())
    .componentDidUpdate((scope, prevProps, prevState) => scope.backend.onComponentDidUpdate(prevProps, prevState))
    .componentWillUnmount(scope => scope.backend.onComponentWillUnmount())
    .build

  def apply(props: Affix, children: ReactNode*) = component(props, children)

}
