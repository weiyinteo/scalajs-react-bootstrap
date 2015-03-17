package demo.pages

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Section {


  def Section(id: String, header: ReactNode, subSections: ReactNode*) =
    <.div(^.className := "bs-docs-section",
      <.h1(^.id := id, ^.className := "page-header", header),
      subSections
    )

  def apply(id: String, h: ReactNode, s: ReactNode*) = Section(id, h, s)
}
