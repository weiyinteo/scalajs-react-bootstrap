package demo

/**
 * Created by weiyin on 16/03/15.
 */

import demo.pages.Components
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object ReactApp extends JSApp {

  case class State(index: Int)

  class Backend(scope: BackendScope[_, State]) {
    def onMenuClick(newIndex: Int) = scope.modState(_.copy(index = newIndex))
  }

  val navMenu = ReactComponentB[(List[String], Backend)]("appMenu")
    .render(P => {
    val (data, backend) = P
    def element(name: String, index: Int) =
      <.li(^.cls := "navbar-brand", ^.onClick --> backend.onMenuClick(index), name)

    <.div(^.cls := "navbar navbar-default",
      <.ul(^.cls := "navbar-header",
        data.zipWithIndex.map { case (name, index) => element(name, index)}
      )
    )
  }).build

  val container = ReactComponentB[String]("appMenu")
    .render(P => {
    <.div(^.cls := "container",
      P match {
        case "Home" => <.div("home")
        case "Components" => Components.content()
      }
    )
  }).build

  val app = ReactComponentB[List[String]]("app")
    .initialState(State(0))
    .backend(new Backend(_))
    .render((P, S, B) => {
    <.div(
      navMenu((P, B)),
      container(P(S.index))
    )

  }).build

  @JSExport
  override def main(): Unit =
    app(List("Home", "Getting Started", "Components")) render dom.document.body

}
