package com.acework.js.components.bootstrap

/**
 * Created by weiyin on 18/03/15.
 */
trait MergeableProps[T] {

  def merge(t: Map[String, Any]): MergeableProps[T]

  def asMap: Map[String, Any]
}
