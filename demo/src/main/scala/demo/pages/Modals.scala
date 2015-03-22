package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.ContainedModal
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.window

/**
 * Created by weiyin on 16/03/15.
 */
object Modals {

  val exampleSource =
    """
      |<.div(^.className := "static-modal",
      |  Modal.Modal(title = "Modal title": ReactNode,
      |    bsStyle = Styles.primary,
      |    backdrop = false,
      |    animation = false,
      |    //container = mountRef,
      |    onRequestHide = handleHide)(
      |      <.div(^.className := "modal-body",
      |        "One fine body..."),
      |
      |      <.div(^.className := "modal-footer",
      |        Button("Close"),
      |        Button(Button.Button(bsStyle = Styles.primary), "Save changes"))
      |    )
      |)
    """.stripMargin

  val handleHide = () => {
    window.alert("close me")
  }

  def exampleContent = CodeContent.Content(exampleSource, {
    <.div(^.className := "static-modal",
      Modal.Modal(title = "Modal title": ReactNode,
        bsStyle = Styles.primary,
        backdrop = false,
        animation = false,
        //container = mountRef,
        onRequestHide = handleHide)(
          <.div(^.className := "modal-body",
            "One fine body..."),

          <.div(^.className := "modal-footer",
            Button("Close"),
            Button(Button.Button(bsStyle = Styles.primary), "Save changes"))
        )
    )
  }
  )

  val liveSource =
    """
      |val myModel = Modal.Modal(bsStyle = Styles.primary, title = "Modal heading": ReactNode, animation = false,
      |  onRequestHide = () => ())(
      |  <.div(^.className := "modal-body",
      |    <.h4("Text in a modal"),
      |    <.p("Duis mollis, est non commodo luctus, nisi erat porttitor ligula."),
      |    <.h4("Popover in a modal"),
      |    <.p("TODO"),
      |
      |    <.h4("Tooltips in a modal"),
      |    <.p("TODO"),
      |
      |    <.hr(),
      |
      |    <.h4("Overflowing text to show scroll behavior"),
      |    <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
      |    <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
      |    <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla."),
      |    <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
      |    <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
      |    <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla."),
      |    <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
      |    <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
      |    <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.")
      |  ),
      |  <.div(^.className := "modal-footer",
      |    Button.Button()("Close"))
      |)
      |
      |ModalTrigger.ModalTrigger(modal = myModel)(
      |  Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg)("Launch demo modal")
      |)
    """.stripMargin

  def liveContent = CodeContent.Content(liveSource, {
    val myModel = Modal.Modal(bsStyle = Styles.primary, title = "Modal heading": ReactNode, animation = false,
      onRequestHide = () => ())(
        <.div(^.className := "modal-body",
          <.h4("Text in a modal"),
          <.p("Duis mollis, est non commodo luctus, nisi erat porttitor ligula."),
          <.h4("Popover in a modal"),
          <.p("TODO"),

          <.h4("Tooltips in a modal"),
          <.p("TODO"),

          <.hr(),

          <.h4("Overflowing text to show scroll behavior"),
          <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
          <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
          <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla."),
          <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
          <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
          <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla."),
          <.p("Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros."),
          <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor."),
          <.p("Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.")
        ),
        <.div(^.className := "modal-footer",
          Button.Button()("Close"))
      )

    ModalTrigger.ModalTrigger(modal = myModel)(
      Button.Button(bsStyle = Styles.primary, bsSize = Sizes.lg)("Launch demo modal")
    )
  }
  )

  // TODO containedSource
  def containedContent = CodeContent.Content(liveSource,
    ContainedModal.trigger()
  )

  val content = Section("modals", <.span("Modals ", <.small("Modal"))
    , SubSection("modals-example", "A static example",
      <.p("A rendered modal with header, body, and set of actions in the footer."),
      <.p("The header is added automatically if you pass in a ", <.code("title"), " prop."),
      exampleContent()
    )
    , SubSection("modals-live", "Live Demo",
      <.p("Use ", <.code("ModalTrigger"), " to create a real modal that's added to the document body when opened."),
      liveContent())
    , SubSection("modals-contained", "Contained Modal",
      <.p("You will need to add the following css to your project and ensure that your container has the ", <.code("modal-container"), " class."),
      <.pre(
        """
          |.modal-container {
          |  position: relative;
          |}
          |
          |.modal-container .modal, .modal-container .modal-backdrop {
          |  position: absolute;
          |}
          |
        """.stripMargin
      ),
      containedContent())
  )
}
