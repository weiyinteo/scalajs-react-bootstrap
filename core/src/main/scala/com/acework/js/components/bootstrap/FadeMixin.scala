package com.acework.js.components.bootstrap

import japgolly.scalajs.react.{BackendScope, TopNode}
import org.scalajs.dom.{document, raw}

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */
trait FadeMixin[P, S] {

  def scope: BackendScope[P, S]

  var fadeOutEl: raw.Element = _

  def getElementAndSelf(root: TopNode, classes: Array[String]): Array[TopNode] = {
    val elements = root.querySelectorAll("." + classes.mkString("."))

    val els = (for (i <- 0 until elements.length) yield elements(i).asInstanceOf[TopNode]).toArray

    var matched = true
    for (i <- 0 until classes.length if matched) {
      val Pattern = "\\b${classes(i)}\\b".r
      root.className match {
        case Pattern() =>
        case _ =>
          matched = false
      }
    }
    if (matched)
      root +: els
    else
      els
  }

  def _fadeIn() = {
    if (scope.isMounted()) {
      val elements = getElementAndSelf(scope.getDOMNode(), Array("fade"))
      elements.foreach { el =>
        el.className += " in"
      }
    }
  }

  def _fadeOut() = {
    val elements = getElementAndSelf(scope.getDOMNode(), Array("fade", "in"))
    elements.foreach { el =>
      el.className = el.className.replace("\\bin\\b", "")
    }
    js.timers.setTimeout(300)(handleFadeOutEnd())
  }

  def handleFadeOutEnd(): Unit = ???

  def onComponentDidMount() = {
    // FIXME what does this mean? -- if(document.querySelectorAll)
    //val nodes = document.querySelectorAll("")
    if (true) {
      // Firefox needs delay for transition to be triggered
      js.timers.setTimeout(20)(_fadeIn())
    }
  }

  def onComponentWillUnmount(): Unit = {
    val elements = getElementAndSelf(scope.getDOMNode(), Array("fade"))
    // TODO container
    //val container = scope.props.container && scope.props.container.getDOMNode() || document.body
    val container = document.body
    if (elements.length > 0) {
      fadeOutEl = document.createElement("div")
      fadeOutEl.appendChild(scope.getDOMNode().cloneNode(true))
      js.timers.setTimeout(20)(_fadeOut())
    }
  }
}
