package com.acework.js.components.bootstrap

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */
trait BootstrapJQuery extends JQuery {
  def modal(action: String): BootstrapJQuery = js.native

  def modal(options: js.Any): BootstrapJQuery = js.native
}

