package demo.examples.util

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import org.scalajs.dom.document
import org.scalajs.dom.ext.PimpedNodeList

/**
 * Created by weiyin on 16/03/15.
 */
object CodeContent {

  case class Content(scalaSource: String, el: ReactNode, exampleClasses: String = "") {
    def apply() = component(this)
  }

  case class State(showCode: Boolean = false)

  class Backend(scope: BackendScope[Content, State]) {

    def toggleCodeMode(e: ReactEvent) = {
      e.preventDefault()
      scope.modState(s => s.copy(showCode = !s.showCode))
    }
  }

  def installSyntaxHighlighting[P, S, B] =
    (_: ReactComponentB[P, S, B])
      .componentDidMount(_ => applySyntaxHighlight())
      .componentDidUpdate((_, _, _) => applySyntaxHighlight())


  def applySyntaxHighlight() = {
    import scala.scalajs.js.Dynamic.{global => g}
    val nodeList = document.querySelectorAll("pre code").toArray
    nodeList.foreach(n => g.hljs.highlightBlock(n))
  }

  val component = ReactComponentB[Content]("CodeContent")
    .initialState(State())
    .backend(new Backend(_))
    .render((P, C, S, B) => {
    <.div(^.className := "playground",
      <.div(^.className := s"bs-example ${P.exampleClasses}",
        <.div(P.el)),
      if (S.showCode)
        <.div(
          <.pre(<.code(P.scalaSource.trim)),
          <.a(^.className := "code-toggle", ^.onClick ==> B.toggleCodeMode, ^.href := "#", "Hide code")
        )
      else
        <.a(^.className := "code-toggle", ^.onClick ==> B.toggleCodeMode, ^.href := "#", "Show code")

    )
  }).configure(installSyntaxHighlighting)
    .build
}
