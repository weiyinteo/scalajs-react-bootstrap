package demo.examples

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 14/03/15.
 */
object ExamplePageFooter {
  val component = ReactComponentB[Unit]("ExamplePageFooter")
    .stateless
    .render((P, C) => {
    <.footer(^.className := "bc-docs-footer", ^.role := "contentinfo",

      <.p("Code licensed under ", <.a(^.href := "https://github.com/weiyinteo/scalajs-react-bootstrap/blob/master/LICENSE", ^.target := "_blank", "MIT"))
    )
  }).buildU

  def apply() = component()

}
