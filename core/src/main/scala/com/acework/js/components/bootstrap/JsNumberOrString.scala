package com.acework.js.components.bootstrap

import scala.scalajs.js
import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 17/03/15.
 */

trait JsNumberOrString extends js.Object

object JsNumberOrString {
  implicit def int2JsNumOrString(i: Int): UndefOr[JsNumberOrString] = i.asInstanceOf[JsNumberOrString]

  implicit def str2JsNumOrString(s: String): UndefOr[JsNumberOrString] = s.asInstanceOf[JsNumberOrString]
}