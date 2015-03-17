package com.acework.js.components.bootstrap

import com.acework.js.utils.EventListener
import japgolly.scalajs.react.{BackendScope, ReactEvent}
import org.scalajs.dom.document
import org.scalajs.dom.raw.{KeyboardEvent, Node}

/**
 * Created by weiyin on 11/03/15.
 */

case class DropdownState(open: Boolean)

trait DropdownStateMixin[P] {

  val scope: BackendScope[P, DropdownState]

  var _onDocumentClickListener: Option[EventListener] = None
  var _onDocumentKeyUpListener: Option[EventListener] = None

  /**
   * Checks whether a node is within
   * a root nodes tree
   *
   * @param node
   * @param root
   */
  def isNodeInRoot(node: Node, root: Node): Boolean = {
    var aNode = node
    var done = false
    var result = false
    while (aNode != null && !done) {
      if (aNode == root) {
        done = true
        result = true
      }
      else
        aNode = aNode.parentNode
    }
    result
  }

  def setDropdownState(newState: Boolean, onStateChangeComplete: () => Unit = () => ()) = {
    if (newState)
      bindRootCloseHandlers()
    else
      unBindRootCloseHandlers()

    scope.modState(s => s.copy(open = newState), onStateChangeComplete)
  }

  def bindRootCloseHandlers(): Unit = {
    _onDocumentClickListener = Some(EventListener.listen(document, "click", handleDocumentClick(_: ReactEvent)))
    _onDocumentKeyUpListener = Some(EventListener.listen(document, "keyup", handleDocumentKeyUp(_: KeyboardEvent)))
  }

  def unBindRootCloseHandlers(): Unit = {
    _onDocumentClickListener.map(_.remove())
    _onDocumentClickListener = None
    _onDocumentKeyUpListener.map(_.remove())
    _onDocumentKeyUpListener = None
  }

  def handleDocumentKeyUp(e: KeyboardEvent) = {
    if (e.keyCode == 27)
      setDropdownState(false)
  }

  def handleDocumentClick(e: ReactEvent) = {
    if (!isNodeInRoot(e.target, scope.getDOMNode()))
      setDropdownState(false)
  }

  def onComponentWillUnmount() = unBindRootCloseHandlers()

}
