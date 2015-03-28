package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react.Addons.ReactCloneWithProps
import japgolly.scalajs.react._

import scala.scalajs.js
import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 09/03/15.
 */
object Utils {

  object ValidComponentChildren {
    /**
     * mapValidComponents
     * Maps children that are typically specified as `props.children`,
     * but only iterates over children that are "valid components".
     *
     * The mapFunction provided index will be normalised to the components mapped,
     * so an invalid component would not increase the index.
     *
     * @param children children Children tree container.
     * @param func mapFunction
     * @return Object containing the ordered map of results.
     */
    def map(children: PropsChildren, func: (ReactNode, Int) => Any) = {
      var index = 0
      val f = (child: ReactNode, idx: Int) => {
        if (React.isValidElement(child)) {
          val lastIndex = index
          index += 1
          func(child, lastIndex)
        }
        else
          child
      }
      val res = React.Children.map(children, (f: js.Function).asInstanceOf[js.Function1[ReactNode, js.Any]])
      res.get.asInstanceOf[ReactNode]
    }


    /**
     * Iterates through children that are typically specified as `props.children`,
     * but only iterates over children that are "valid components".
     *
     * The provided forEachFunc(child, index) will be called for each
     * leaf child with the index reflecting the position relative to "valid components".
     */
    def forEach2(children: PropsChildren, func: (ReactNode, Int) => Any) = {
      var index = 0
      val f = (child: ReactNode) => {
        if (React.isValidElement(child)) {
          func(child, index)
          index += 1
        }
      }
      children.forEach(f)
    }

    def forEach3(children: PropsChildren, func: (ReactNode, Int, Any) => Any, context: Any) = {
      var index = 0
      val f = (child: ReactNode) => {
        if (React.isValidElement(child)) {
          func(child, index, context)
          index += 1
        }
      }
      children.forEach(f)
    }

    /**
     * Count the number of "valid components" in the Children container.
     */
    def numberOfValidComponents(children: PropsChildren): Int = {
      var count = 0
      children.forEach { child =>
        if (React.isValidElement(child))
          count += 1
      }
      count
    }

    def hasValidComponents(children: PropsChildren): Boolean = numberOfValidComponents(children) > 0
  }

  def createChainedFunction0[X](f1: UndefOr[() => X], f2: UndefOr[() => X]): UndefOr[() => X] = {
    if (f1.isEmpty && f2.isEmpty)
      js.undefined
    else if (f1.isEmpty)
      f2
    else if (f2.isEmpty)
      f1
    else
      () => {
        f1.get()
        f2.get()
      }
  }

  def createChainedFunction1[X, Y](f1: UndefOr[(X) => Y], f2: UndefOr[(X) => Y]): UndefOr[(X) => Y] = {
    if (f1.isEmpty && f2.isEmpty)
      js.undefined
    else if (f1.isEmpty)
      f2
    else if (f2.isEmpty)
      f1
    else
      (x: X) => {
        f1.get(x)
        f2.get(x)
      }
  }

  def createChainedFunction2[X, Y, Z](f1: UndefOr[(X, Y) => Z], f2: UndefOr[(X, Y) => Z]): UndefOr[(X, Y) => Z] = {
    if (f1.isEmpty && f2.isEmpty)
      js.undefined
    else if (f1.isEmpty)
      f2
    else if (f2.isEmpty)
      f1
    else
      (x: X, y: Y) => {
        f1.get(x, y)
        f2.get(x, y)
      }
  }

  def getChildKeyAndRef(child: ReactNode, index: Int): Map[String, js.Any] = {
    val dynChild = child.asInstanceOf[js.Dynamic]
    val ref: UndefOr[js.Any] = if (dynChild.ref == null) js.undefined else dynChild.ref
    val key: UndefOr[js.Any] = if (dynChild.key == null) js.undefined else dynChild.key
    val newKey: js.Any = if (key.isDefined) key else index
    Map("ref" -> ref, "key" -> newKey)
  }

  def getChildKeyAndRef2(child: ReactNode, index: Int): (UndefOr[String], UndefOr[String]) = {
    val childElement = child.asInstanceOf[ReactDOMElement]
    val key = childElement.key
    val ref = childElement.ref
    val newKey: UndefOr[String] = if (key.isDefined) key else index.toString
    (newKey, ref)
  }

  def getChildEventKey(child: ReactNode): UndefOr[js.Any] = {
    val dynChild = child.asInstanceOf[js.Dynamic]
    if (dynChild.eventKey == null) js.undefined else dynChild.eventKey
  }

  def getChildProps[P](child: ReactNode): P = {
    val childNode = child.asInstanceOf[ReactDOMElement]
    childNode.props.asInstanceOf[WrapObj[P]]
  }

  def cloneWithProps[P <: MergeableProps[P]](child: ReactNode, keyAndRef: (UndefOr[String], UndefOr[String]) = (js.undefined, js.undefined),
                                             propsMap: Map[String, Any] = Map.empty) = {

    var newProps = Map[String, js.Any]().empty
    if (keyAndRef._1.isDefined)
      newProps += ("key" -> keyAndRef._1.get)

    if (keyAndRef._2.isDefined)
      newProps += ("ref" -> keyAndRef._2.get)

    if (isScalaComponent(child)) {
      val childElement = child.asInstanceOf[ReactDOMElement]
      val childProps: MergeableProps[P] = childElement.props.asInstanceOf[WrapObj[MergeableProps[P]]]

      var mergedProps = childProps
      if (propsMap.nonEmpty)
        mergedProps = mergedProps.merge(propsMap)

      val newChild = ReactCloneWithProps(child, newProps)

      val dynChild = newChild.asInstanceOf[js.Dynamic]
      val newChildProps = dynChild.props
      newChildProps.updateDynamic("v")(mergedProps.asInstanceOf[js.Any])
      newChild
    }
    else {
      for ((k, v) <- propsMap) {
        unwrapUndefOrFunc1(v) match {
          case Some(fn) =>
            newProps += (k -> fn)
          case None =>
            newProps += (k -> v.asInstanceOf[js.Any])
        }
      }
      val newChild = ReactCloneWithProps(child, newProps)
      newChild
    }
  }

  def unwrapUndefOrFunc1(v: Any): Option[Any => Any] = {
    var result: Option[Any => Any] = None
    val unwrapped = v.asInstanceOf[UndefOr[Any]]
    if (unwrapped.isDefined) {
      if (unwrapped.get.isInstanceOf[Function1[_, _]]) {
        result = Some(unwrapped.get.asInstanceOf[Any => Any])
      }
    }
    result
  }

  implicit class MergeableCaseClass[S: Mergeable](v: S) {
    def merge(map: Map[String, Any]): S =
      implicitly[Mergeable[S]].merge(v, map)

    def mergeProps[T: Mappable](t: T): S = {
      val m = implicitly[Mappable[T]].toMap(t)
      merge(m)
    }
  }

  def caseClass2Map[T: Mappable](t: T): Map[String, Any] = implicitly[Mappable[T]].toMap(t)

  def isScalaComponent(node: ReactNode): Boolean = {
    var scalaComponent = false
    if (React.isValidElement(node)) {
      val element = node.asInstanceOf[ReactDOMElement]
      val childProps = element.props
      // this is a hack, is there better way to find out if it is a
      if (childProps.hasOwnProperty("v"))
        scalaComponent = true
    }
    scalaComponent
  }

  case class Offset(top: Double, left: Double)

  def getOffset(node: TopNode) = {
    val offset = jQuery(node).offset().asInstanceOf[js.Dynamic]
    Offset(offset.top.asInstanceOf[Double], offset.left.asInstanceOf[Double])
  }

}
