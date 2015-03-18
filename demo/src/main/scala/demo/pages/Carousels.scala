package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 16/03/15.
 */
object Carousels {

  val exampleSource =
    """
      |Carousel(
      |  CarouselItem(
      |    <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
      |    <.div(^.className := "carousel-caption",
      |      <.h3("First slide label"),
      |      <.p("Nulla vitae elit libero, a pharetra augue mollis interdum.")
      |    )
      |  ),
      |  CarouselItem(
      |    <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
      |    <.div(^.className := "carousel-caption",
      |      <.h3("Second slide label"),
      |      <.p("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
      |    )
      |  ),
      |  CarouselItem(
      |    <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
      |    <.div(^.className := "carousel-caption",
      |      <.h3("Third slide label"),
      |      <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur.")
      |    )
      |  )
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Carousel(
      CarouselItem(
        <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
        <.div(^.className := "carousel-caption",
          <.h3("First slide label"),
          <.p("Nulla vitae elit libero, a pharetra augue mollis interdum.")
        )
      ),
      CarouselItem(
        <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
        <.div(^.className := "carousel-caption",
          <.h3("Second slide label"),
          <.p("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        )
      ),
      CarouselItem(
        <.img(^.width := 900, ^.height := 500, ^.alt := "900x500", ^.src := "res/carousel.png"),
        <.div(^.className := "carousel-caption",
          <.h3("Third slide label"),
          <.p("Praesent commodo cursus magna, vel scelerisque nisl consectetur.")
        )
      )
    )
  )

  val content = Section("carousels", <.span("Carousels ", <.small("Carousel, CarouselItem"))
    , SubSection("carousels-example", "Example carousels",
      <.h3("Uncontrolled"),
      <.p("Allow the component to control its own state."),
      exampleContent()
    )
  )
}
