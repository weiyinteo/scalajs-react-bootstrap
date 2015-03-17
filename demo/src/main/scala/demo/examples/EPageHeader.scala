package demo.examples

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
  * Created by weiyin on 14/03/15.
  */
object EPageHeader {

   case class Props(title: String, subTitle: String)

   val EPageHeader = ReactComponentB[Props]("EPageHeader")
     .stateless
     .render((P, C) => {
     <.div(^.className := "bs-doc-header", ^.id := "content",
       <.div(^.className := "container",
         <.h1(P.title),
         <.p(P.subTitle)
       )
     )
   }).build


   def apply(title: String, subTitle: String) = EPageHeader(Props(title = title, subTitle = subTitle))
 }
