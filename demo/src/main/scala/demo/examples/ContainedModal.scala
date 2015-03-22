package demo.examples

import com.acework.js.components.bootstrap.Modal.{N, B, S, P}
import com.acework.js.components.bootstrap._
import japgolly.scalajs.react.ReactComponentC.{ReqProps, ConstProps}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.{Node, document}

/**
 * Created by weiyin on 21/03/15.
 */
object ContainedModal {

  val containerRef: RefSimple[TopNode] = Ref[HTMLElement]("container")

  def containedModel = {
    Modal.Modal(bsStyle = Styles.primary, title = "Contained Modal": ReactNode, animation = true,
      onRequestHide = () => ())(
        <.div(^.className := "modal-body",
          <.h4("Text in a modal"),
          <.p("Elit est explicabo ipsum eaque dolorem blanditiis doloribus sed id ipsam, beatae, rem fuga id earum? Inventore et facilis obcaecati.")
        ),
        <.div(^.className := "modal-footer",
          // FIXME link onClick to onRequestHide
          Button(Button.Button(), "Close"))
      )
  }

  class Backend(val scope: BackendScope[Unit, Unit])

  val trigger = ReactComponentB[Unit]("Trigger")
    .stateless
    .backend(new Backend(_))
    .render((_, _, B) => {

    <.div(^.className := "modal-container", ^.height := 300, ^.ref := containerRef,
      ModalTrigger.ModalTrigger(modal = containedModel,
        container = new ReferencedContainer(containerRef, B.scope))(
          Button(Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg), "Launch contained modal")
        )
    )
  }).
    buildU

}
