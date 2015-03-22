package demo.pages

import com.acework.js.components.bootstrap._
import demo.examples.util.CodeContent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 16/03/15.
 */
object Grids {

  val exampleSource =
    """
      |Grid(
      |  Row.Row(addClasses = "show-grid")(
      |    Col.Col(xs = 12, md = 8)(<.code("Col(Col.props(xs=12 md=8)")),
      |    Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)"))
      |  ),
      |  Row.Row(addClasses = "show-grid")(
      |    Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)")),
      |    Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)")),
      |    Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)"))
      |  ),
      |  Row.Row(addClasses = "show-grid")(
      |    Col.Col(xs = 6, xsOffset = 6)(<.code("Col(Col.Props(xs = 6, xsOffset = 6)"))
      |  ),
      |  Row.Row(addClasses = "show-grid")(
      |    Col.Col(md = 6, mdPush = 6)(<.code("Col(Col.Props(md = 6, mdPush = 6)")),
      |    Col.Col(md = 6, mdPull = 6)(<.code("Col(Col.Props(md = 6, mdPull = 6)"))
      |  )
      |)
    """.stripMargin

  def exampleContent = CodeContent.Content(exampleSource,
    Grid(
      Row.Row(addClasses = "show-grid")(
        Col.Col(xs = 12, md = 8)(<.code("Col(Col.props(xs=12 md=8)")),
        Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)"))
      ),
      Row.Row(addClasses = "show-grid")(
        Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)")),
        Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)")),
        Col.Col(xs = 6, md = 4)(<.code("Col(Col.props(xs=6 md=4)"))
      ),
      Row.Row(addClasses = "show-grid")(
        Col.Col(xs = 6, xsOffset = 6)(<.code("Col(Col.Props(xs = 6, xsOffset = 6)"))
      ),
      Row.Row(addClasses = "show-grid")(
        Col.Col(md = 6, mdPush = 6)(<.code("Col(Col.Props(md = 6, mdPush = 6)")),
        Col.Col(md = 6, mdPull = 6)(<.code("Col(Col.Props(md = 6, mdPull = 6)"))
      )
    )
  )


  val content = Section("grids", <.span("Grids ", <.small("Grid, Row, Col"))
    , SubSection("grids-example", "Example grids",
      exampleContent()
    )
  )
}
