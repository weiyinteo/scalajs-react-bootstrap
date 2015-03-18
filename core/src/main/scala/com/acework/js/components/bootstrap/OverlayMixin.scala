package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.{Node, document}


/**
 * Created by weiyin on 17/03/15.
 */

trait OverlayContainer {
  // Provide `getDOMNode` fn mocking a React component API. The `document.body`
  // reference needs to be contained within this function so that it is not accessed
  // in environments where it would not be defined, e.g. nodejs. Equally this is needed
  // before the body is defined where `document.body === null`, this ensures
  // `document.body` is only accessed after componentDidMount.
  def getDOMNode: HTMLElement = document.body
}

class ReferencedContainer[P, S](ref: RefSimple[TopNode], scope: BackendScope[P, S]) extends OverlayContainer {
  override def getDOMNode = {
    val refNode = ref(scope)
    if (refNode != null)
      refNode.get.getDOMNode()
    else
      super.getDOMNode
  }
}

trait OverlayProps {
  val container: OverlayContainer
}

trait OverlayMixin[P <: OverlayProps, S] {

  val scope: BackendScope[P, S]

  var _overlayTarget: Option[Node] = None
  var _overlayInstance: Option[ReactComponentM_[TopNode]] = None

  def onComponentWillUnmount() = {
    _unrenderOverlay()
    _overlayTarget match {
      case Some(target) =>
        getContainerDOMNode.removeChild(target)
        _overlayTarget = None
      case None =>
    }
  }

  def onComponentDidUpdate() = {
    _renderOverlay()
  }

  def onComponentDidMount() = {
    _renderOverlay()
  }

  def _renderOverlay() = {
    _overlayTarget match {
      case None =>
        _mountOverlayTarget()
      case Some(_) =>
    }

    renderOverlay() match {
      case Some(overlay) =>
        _overlayInstance = Some(React.render(overlay, _overlayTarget.get))
      case None =>
        // unreder if the componet is null or transitions to null
        _unrenderOverlay()
    }
  }

  def _unrenderOverlay() = {
    _overlayTarget map { target =>
      React.unmountComponentAtNode(target)
      _overlayTarget = None
    }
  }

  def _mountOverlayTarget() = {
    _overlayTarget = Some(document.createElement("div"))
    getContainerDOMNode.appendChild(_overlayTarget.get)
  }

  def getOverlayDOMNode: Option[TopNode] = {
    var node: Option[TopNode] = None
    if (scope.isMounted()) {
      if (_overlayInstance.isDefined)
        node = Some(_overlayInstance.get.getDOMNode())
    }
    node
  }

  def getContainerDOMNode: HTMLElement = {
    scope.props.container.getDOMNode
  }

  def renderOverlay(): Option[ReactElement]

}
