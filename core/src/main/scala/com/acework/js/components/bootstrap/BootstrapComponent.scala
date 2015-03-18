package com.acework.js.components.bootstrap

import japgolly.scalajs.react.ReactComponentC.ReqProps
import japgolly.scalajs.react.{ReactNode, TopNode}

import scala.scalajs.js

/**
 * Created by weiyin on 18/03/15.
 */
trait BootstrapComponent {

  type P
  type S
  type B
  type N <: TopNode

  val component: ReqProps[P, S, B, N]

  def defaultProps: P

  def withKey(key: js.Any) = component.withKey(key)

  def withRef(ref: String) = component.withRef(ref)

  def apply(props: P, children: ReactNode*) = component(props, children)

  def apply(children: ReactNode*) = component(defaultProps, children)

  def apply() = component
}
