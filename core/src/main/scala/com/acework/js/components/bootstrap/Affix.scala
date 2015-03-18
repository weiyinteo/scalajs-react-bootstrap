package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.document

import scala.scalajs.js
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Affix {

  case class State(affixClass: String = "affix-top")

  case class Props(offset: UndefOr[Int] = undefined,
                   offsetTop: UndefOr[Int] = undefined,
                   offsetBottom: UndefOr[Int] = undefined,
                   role: UndefOr[String] = undefined,
                   affixed: UndefOr[String] = undefined,
                   addClasses: String = "")

  class Backend(val scope: BackendScope[Props, State]) {

    case class Offset(top: Double, left: Double)

    var pinnedOffset: Option[Offset] = None

    def getOffset(node: TopNode) = {
      val offset = jQuery(node).offset().asInstanceOf[js.Dynamic]
      Offset(offset.top.asInstanceOf[Double], offset.left.asInstanceOf[Double])
    }

    def getPinnedOffset(node: TopNode) = {
      if (pinnedOffset.isDefined)
        pinnedOffset.get
      else {
        var className = node.className.replaceAll("affix-top|affix-bottom|affix", "")
        className = if (className.length > 0) className + " affix" else className + "affix"
        node.className = className

        pinnedOffset = Some(getOffset(node))
        pinnedOffset.get
      }
    }

    def checkPosition = {
      if (scope.isMounted()) {
        val domNode = scope.getDOMNode()
        val scrollHeight = document.documentElement.scrollHeight
        val scrollTop = document.documentElement.scrollTop
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

        if (!offsetTop.isDefined && !offsetBottom.isDefined) {
          // do nothing
        }
        else {
          if (!offsetTop.isDefined)
            offsetTop = 0
          if (!offsetBottom.isDefined)
            offsetBottom = 0
        }

      }
    }

  }

  val Affix = ReactComponentB[Props]("Affix")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    // FIXME holder style
    <.div(^.classSet1(P.addClasses, S.affixClass -> true))(C)

  }).build

  def apply(props: Props, children: ReactNode*) = Affix(props, children)

}
