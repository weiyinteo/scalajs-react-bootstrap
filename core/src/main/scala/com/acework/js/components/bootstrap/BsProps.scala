package com.acework.js.components.bootstrap

import scala.scalajs.js.UndefOr

/**
 * Created by weiyin on 09/03/15.
 */

trait BsProps {
  val bsClass: UndefOr[Classes.Value]
  val bsStyle: UndefOr[Styles.Value]
  val bsSize: UndefOr[Sizes.Value]

  def bsClassSet: Map[String, Boolean] = {
    var classes = Map[String, Boolean]()

    if (bsClass.isDefined && Classes.values.contains(bsClass.get)) {
      val bsClassName = bsClass.get.toString.replaceAll("\\$minus", "-")
      classes += (bsClassName -> true)

      val prefix = bsClassName + "-"
      if (bsSize.isDefined && Sizes.values.contains(bsSize.get)) {
        val bsSizeName = bsSize.get.toString
        classes += (prefix + bsSizeName -> true)
      }
      if (bsStyle.isDefined && Styles.values.contains(bsStyle.get)) {
        val bsStyleName = bsStyle.get.toString
        classes += ((prefix + bsStyleName) -> true)
      }
    }
    classes
  }

  def prefixClass(subClass: String) = {
    val bsClassName = bsClass.get.toString.replaceAll("\\$minus", "-")
    s"$bsClassName-$subClass"
  }
}

