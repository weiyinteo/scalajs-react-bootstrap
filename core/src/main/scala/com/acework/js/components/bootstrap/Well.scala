package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Well extends BootstrapComponent {
  override type P = Well
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  override def defaultProps = Well()

  case class Well(bsClass: UndefOr[Classes.Value] = Classes.well,
                  bsStyle: UndefOr[Styles.Value] = undefined,
                  bsSize: UndefOr[Sizes.Value] = undefined,
                  addClasses: String = "")
    extends BsProps with MergeableProps[Well] {

    def merge(t: Map[String, Any]): Well = implicitly[Mergeable[Well]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Well]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  override val component = ReactComponentB[Well]("Well")
    .render { (P, C) =>

    // TODO spread props
    <.div(^.classSet1M(P.addClasses, P.bsClassSet))(C)

  }.build

}
