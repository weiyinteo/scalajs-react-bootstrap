package com.acework.js.components.bootstrap

import com.acework.js.logger._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

/**
 * Created by weiyin on 10/03/15.
 */
object Panel2 {

  case class LabelProps(bsClass: String = "label", bsStyle: String = "default", testString: String = "xxx")

  case class LabelState()

  class LabelBackend(val c: BackendScope[LabelProps, LabelState])

  def pobj(x: Any): String = ???

  def transferPropsTo2(x: Any, y: Any): Unit = ???

  val Label = ReactComponentB[LabelProps]("Label")
    .initialState(LabelState())
    .backend { s => new LabelBackend(s)}
    .render { (props, children, state, backend) =>
    log.debug("rendering label and children: " + children + " values: " + pobj(children))
    //React.Children.forEach(children, (child: VDom) => { log.debug("child: " + pobj(child)) })

    val ch = <.span(^.classSet(props.bsClass -> true, props.bsStyle -> true))(children).render
    //transferPropsTo2(backend.c, ch)
    ch
  }.build
  /*
  .mergeProps { (sourceProps, targetProps) =>
  targetProps.bsClass = sourceProps.bsClass
  targetProps.bsStyle = sourceProps.bsStyle
  targetProps.testString = sourceProps.testString
}
  .deserializeProps { (source, current) =>
  log.debug("deserializeProps for label")
  val r = current.copy(testString = source.testString.asInstanceOf[String])
  log.debug("new props: " + r)
  r
}*/
  //.create
}
