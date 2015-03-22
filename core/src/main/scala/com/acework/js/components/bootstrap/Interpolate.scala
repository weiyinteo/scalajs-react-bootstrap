package com.acework.js.components.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js._
import Utils._

import scala.util.matching.Regex.Groups

/**
 * Created by weiyin on 10/03/15.
 */
object Interpolate extends BootstrapComponent {
  override type P = Interpolate
  override type S = Unit
  override type B = Unit
  override type N = TopNode

  def defaultProps = Interpolate()

  case class Interpolate(now: UndefOr[Double] = undefined,
                         min: UndefOr[Double] = undefined,
                         max: UndefOr[Double] = undefined,
                         format: UndefOr[String] = undefined,
                         componentClass: UndefOr[String] = "span",
                         unsafe: UndefOr[Boolean] = undefined,
                         percent: UndefOr[Double] = undefined,
                         bsClass: UndefOr[Classes.Value] = Classes.label,
                         bsStyle: UndefOr[Styles.Value] = Styles.default,
                         bsSize: UndefOr[Sizes.Value] = undefined,
                         addClasses: String = "") extends BsProps {

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  val REGEXP = "%\\((.*)\\)s".r

  override val component = ReactComponentB[Interpolate]("Interpolate")
    .render { (P, C) =>

    def getFormat(): String = {
      // FIXME C is str
      //if (ValidComponentChildren.hasValidComponents(C))
      if (React.Children.count(C) > 0)
        C.asInstanceOf[Array[String]](0)
      else
        P.format.getOrElse("")
    }

    val format = getFormat()
    val parent = P.componentClass.get.reactTag
    val unsafe = P.unsafe.getOrElse(false)

    val content = REGEXP.replaceAllIn(format, _ match {
      case Groups("now") => s"${P.min}"
      case Groups("min") => s"${P.min}"
      case Groups("max") => s"${P.min}"
      case Groups("percent") => s"${P.percent}"
      case Groups("bsStyle") => s"${P.bsStyle}"
    })

    // TODO props spread
    if (unsafe) {
      parent(^.classSet1M(P.addClasses, P.bsClassSet), ^.dangerouslySetInnerHtml(content))
    }
    else
      parent(^.classSet1M(P.addClasses, P.bsClassSet), content)
  }.build

}
