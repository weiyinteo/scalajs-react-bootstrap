package demo.pages

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import com.acework.js.components.bootstrap._

/**
 * Created by weiyin on 16/03/15.
 */
object SubSection {
  def SubSection(id: String, header: ReactNode, description: ReactNode,
                 content: ReactNode*)
  = <.div(<.h2(^.id := id, header),
    description,
    content)

  def apply(id: String, h: ReactNode, d: ReactNode, c: ReactNode*) = SubSection(id, h, d, c)

}
