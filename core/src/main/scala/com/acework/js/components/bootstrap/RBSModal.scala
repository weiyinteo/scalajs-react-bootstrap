package com.acework.js.components.bootstrap

import japgolly.scalajs.react.{ReactComponentU_, ReactNode}

import scala.scalajs.js
import scala.scalajs.js.Dynamic._

/**
 * Created by weiyin on 11/03/15.
 */
trait ReactBootstrap extends js.Object {
  def Modal: js.Dynamic = js.native
  def Button: js.Dynamic = ???
  def OverlayMixin: js.Dynamic = ???
}

case class RBSModal(title: String,
                 onRequestHide: () => Unit,
                 className: js.UndefOr[String] = js.undefined) {
  def toJs: js.Object = {
    val p = literal(
      "title" -> title,
      "className" -> className
    )
    p.updateDynamic("onRequestHide")(onRequestHide)
    p
  }

  def apply(children: ReactNode/* VDom* */): ReactComponentU_ = {

    //implicit def jq2bootstrap(jq: JQuery): BootstrapJQuery = jq.asInstanceOf[BootstrapJQuery]
    //val f = ReactBootstrap.Modal
    //f(toJs, js.Array(children.toSeq: _*)).asInstanceOf[ReactComponentU_]
    null
  }
}
