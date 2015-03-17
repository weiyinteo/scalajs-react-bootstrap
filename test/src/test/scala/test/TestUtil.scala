package test

import japgolly.scalajs.react.{React, ReactElement}

/**
 * Created by weiyin on 16/03/15.
 */
object TestUtil {
  def assertRender(comp: ReactElement, expected: String): Unit = {
    val rendered: String = React.renderToStaticMarkup(comp)
    assert(rendered == expected, s"found $rendered")
  }

  implicit class ReactComponentUAS(val c: ReactElement) extends AnyVal {
    def shouldRender(expected: String) = assertRender(c, expected)
  }
}
