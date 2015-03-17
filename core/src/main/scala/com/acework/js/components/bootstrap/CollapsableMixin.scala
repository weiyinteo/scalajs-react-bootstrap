package com.acework.js.components.bootstrap

import japgolly.scalajs.react.{TopNode, BackendScope}

import scala.scalajs.js
import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 09/03/15.
 */

case class CollapsableState(collapsing: Boolean, isExpended: Boolean)

trait CollapsableProps {
  val collapsable: UndefOr[Boolean]
  val expanded: UndefOr[Boolean]
}

trait CollapsableMixin[P <: CollapsableProps] {

  val scope: BackendScope[P, CollapsableState]
  var _collapseEnd = false

  def isExpanded = {
    if (scope.props.expanded.isDefined)
      scope.props.expanded.get
    else
      scope.state.isExpended
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

  def onComponentWillReceiveProps(newProps: P) = {
    if (scope.props.collapsable.getOrElse(false) && newProps.expanded != scope.props.expanded) {
      _collapseEnd = false
      scope.modState(_.copy(collapsing = true))
    }
  }

  def onComponentDidMount = _afterRender()

  def onComponentWillUnmount = _removeEndTransitionListener()

  def onComponentWillUpdate(nextProps: P) = {

  }

  def onComponentDidUpdate(prevProps: P, prevState: CollapsableState) = _afterRender


  def _afterRender() = {
    if (scope.props.collapsable.getOrElse(false)) {
      _addEndTransitionListener()
      js.timers.setTimeout(0)(_updateDimensionAfterRender())
    }
  }

  def _updateDimensionAfterRender() = {
    // TODO
    getCollapsableDOMNode match {
      case Some(node) =>
      case None =>
    }
  }

  def _addEndTransitionListener() = {
    // TODO
    getCollapsableDOMNode match {
      case Some(node) =>
      case None =>
    }
  }

  def _removeEndTransitionListener() = {
    // TODO
    getCollapsableDOMNode match {
      case Some(node) =>
      case None =>
    }
  }

  def getCollapsableDOMNode: Option[TopNode]
}
