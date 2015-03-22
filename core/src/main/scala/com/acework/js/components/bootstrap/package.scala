package com.acework.js.components

import scala.scalajs.js

package object bootstrap extends js.GlobalScope {
  val jQuery: JQueryStatic = js.native

  import scala.language.implicitConversions

  implicit def jq2bootstrap(jq: JQuery): BootstrapJQuery = jq.asInstanceOf[BootstrapJQuery]

}
