package com.acework.js.components.bootstrap

import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 09/03/15.
 */

// define default props bsClass, bsStyle, bsSize
trait BaseProps {
  val bsClass: UndefOr[Classes.Value]
  val bsStyle: UndefOr[Styles.Value]
  val bsSize: UndefOr[Sizes.Value]
}

trait BootstrapMixin {

  type PROPS <: BaseProps

  def getBsClassSet(props: PROPS): Map[String, Boolean] = {
    var classes = Map[String, Boolean]()

    if (props.bsClass.isDefined && Classes.values.contains(props.bsClass.get)) {
      val bsClass = props.bsClass.get.toString.replaceAll("\\$minus", "-")
      classes += (bsClass -> true)

      val prefix = bsClass + "-"
      if (props.bsSize.isDefined && Sizes.values.contains(props.bsSize.get)) {
        val bsSize = props.bsSize.get.toString
        classes += (prefix + bsSize -> true)
      }
      if (props.bsStyle.isDefined && Styles.values.contains(props.bsStyle.get)) {
        val bsStyle = props.bsStyle.get.toString
        classes += ((prefix + bsStyle) -> true)
      }
    }
    classes
  }
}