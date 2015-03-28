package com.acework.js.components.bootstrap

import com.acework.js.utils.TransitionEvent
import japgolly.scalajs.react.{BackendScope, TopNode}
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 09/03/15.
 */

case class CollapsableState(collapsing: Boolean, expanded: Boolean)

trait CollapsableProps {
  val collapsable: UndefOr[Boolean]
  val expanded: UndefOr[Boolean]
}

trait CollapsableMixin[P <: CollapsableProps] {

  val scope: BackendScope[P, CollapsableState]
  var _collapseEnd = false

  def isExpanded = {
    scope.props.expanded.getOrElse(scope.state.expanded)
  }

  def getCollapsableClassSet(classNames: String) = {
    var classes = Map[String, Boolean]()
    for (className <- classNames.split(" "))
      classes += (className -> true)

    classes += ("collapsing" -> scope.state.collapsing)
    classes += ("collapse" -> !scope.state.collapsing)
    classes += ("in" -> (isExpanded && !scope.state.collapsing))

    classes
  }

  def dimension: String = "height"

  def getCollapsableDimensionValue: Int

  def onComponentWillUpdate(nextProps: P, nextState: CollapsableState) = {
    val willExpanded = nextProps.expanded.getOrElse(nextState.expanded)
    if (willExpanded != isExpanded) {
      // if the expanded state is being toggled, ensure node has a dimension value
      // this is needed for the animation to work and needs to be set before
      // the collapsing class is applied (after collapsing is applied the in class
      // is removed and the node's dimension will be wrong)
      val dim = dimension
      val value = if (!willExpanded) getCollapsableDimensionValue else 0

      getCollapsableDOMNode.map(_.style.setProperty(dim, s"${value}px"))

      _afterWillUpdate()
    }
  }

  def onComponentDidUpdate(prevProps: P, prevState: CollapsableState) = {
    // check if expanded is being toggled, if so, set collapsing
    _checkToggleCollapsing(prevProps, prevState)

    // check if collapsing was turned on, if so, start animation
    _checkStartAnimation()
  }

  def _afterWillUpdate() = {}

  def _checkStartAnimation() = {
    if (scope.state.collapsing) {
      val dim = dimension
      val value = if (isExpanded) getCollapsableDimensionValue else 0
      getCollapsableDOMNode.map(_.style.setProperty(dim, s"${value}px"))
    }
  }

  def _checkToggleCollapsing(nextProps: P, nextState: CollapsableState) = {
    val wasExpanded = nextProps.expanded.getOrElse(nextState.expanded)
    if (wasExpanded != isExpanded) {
      if (wasExpanded)
        _handleCollapse()
      else
        _handleExpand()
    }
  }

  def _handleCollapse() = {
    getCollapsableDOMNode.map { node =>
      var onHandleCollapseComplete: (Event) => Unit = null
      onHandleCollapseComplete = (e: Event) => {
        _removeEndEventListener(node, onHandleCollapseComplete)
        scope.modState(_.copy(collapsing = false))
      }
      _addEndEventListener(node, onHandleCollapseComplete)
      scope.modState(_.copy(collapsing = true))
    }
  }

  def _handleExpand() = {
    getCollapsableDOMNode.map { node =>
      val dim = dimension
      var onHandleExpandComplete: (Event) => Unit = null
      onHandleExpandComplete = (e: Event) => {
        _removeEndEventListener(node, onHandleExpandComplete)
        // remove dimension value - this ensure the collapsable item can grow
        // in dimension after initial display (such as an image loading)
        node.style.setProperty(dim, "")
        scope.modState(_.copy(collapsing = false))
      }
      _addEndEventListener(node, onHandleExpandComplete)
      scope.modState(_.copy(collapsing = true))
    }
  }

  def _addEndEventListener(node: TopNode, complete: (Event) => Unit) =
    TransitionEvent.addEndEventListener(node, complete)

  def _removeEndEventListener(node: TopNode, complete: (Event) => Unit) =
    TransitionEvent.removeEndEventListener(node, complete)

  def getCollapsableDOMNode: Option[TopNode]
}
