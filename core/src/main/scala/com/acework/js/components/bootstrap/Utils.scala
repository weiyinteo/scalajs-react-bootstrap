package com.acework.js.components.bootstrap

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

  def getChildEventKey(child: ReactNode): UndefOr[js.Any] = {
    val dynChild = child.asInstanceOf[js.Dynamic]
    if (dynChild.eventKey == null) js.undefined else dynChild.eventKey
  }
}
