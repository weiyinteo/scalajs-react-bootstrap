package demo.examples

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 14/03/15.
 */
object ExamplePageHeader {

  case class ExamplePageHeader(title: String, subTitle: String) {
    def apply() = component(this)
  }

  val component = ReactComponentB[ExamplePageHeader]("ExamplePageHeader")
    .stateless
    .render((P, C) => {
    <.div(^.className := "bs-doc-header", ^.id := "content",
      <.div(^.className := "container",
        <.h1(P.title),
        <.p(P.subTitle)
      )
    )
  }).build

}
