package com.acework.js.components.bootstrap

import japgolly.scalajs.react._

/**
 * Created by weiyin on 10/03/15.
 */
object Accordion {

  val Accordion = ReactComponentB[Unit]("Accordion")
    .stateless
    .render { (P, C, _) =>
    // TODO spread props
    PanelGroup(PanelGroup.PanelGroup(accordion = true), C)
  }.buildU

  def apply(children: ReactNode*) = Accordion(children)
}
