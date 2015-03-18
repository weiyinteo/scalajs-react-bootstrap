package com.acework.js.utils

import org.scalajs.dom._
import org.scalajs.dom.raw.HTMLElement

/**
 * Created by weiyin on 20/03/15.
 */
object TransitionEvent {

  val EVENT_NAME_MAP = Map(
    "transitioned" -> Map(
      "transition" -> "transitionend",
      "WebkitTransition" -> "webkitTransitionEnd",
      "MozTransition" -> "mozTransitionEnd",
      "OTransition" -> "oTransitionEnd",
      "msTransition" -> "MSTransitionEnd"
    ),
    "animationend" -> Map(
      "animation" -> "animationend",
      "WebkitAnimation" -> "webkitAnimationEnd",
      "MozAnimation" -> "mozAnimationEnd",
      "OAnimation" -> "oAnimationEnd",
      "msAnimation" -> "MSAnimationEnd"
    )
  )

  lazy val endEvents: List[String] = {
    try {
      var events: List[String] = Nil
      val testEl = document.createElement("div").asInstanceOf[HTMLElement]
      val style = testEl.style

      // On some platforms, in particular some releases of Android 4.x,
      // the un-prefixed "animation" and "transition" properties are defined on the
      // style object but the events that fire will still be prefixed, so we need
      // to check if the un-prefixed events are usable, and if not remove them
      // from the map
      val excludeAnimationEvent = !window.hasOwnProperty("AnimationEvent")

      val excludeTransition = !window.hasOwnProperty("TransitionEvent")

      for ((baseEventName, baseEvents) <- EVENT_NAME_MAP) {
        for ((styleName, eventName) <- baseEvents
             if style.hasOwnProperty(styleName) &&
               !(excludeAnimationEvent && styleName == "animation") &&
               !(excludeTransition && styleName == "transition")) {
          events = eventName :: events
        }
      }
      events
    }
    catch {
      case e: Throwable =>
        Nil
    }
  }

  def addEventListener(node: EventTarget, eventName: String, listener: (Event) => _) = {
    node.addEventListener(eventName, listener, false)
  }

  def removeEventListener(node: EventTarget, eventName: String, listener: (Event) => _) = {
    node.removeEventListener(eventName, listener, false)
  }

  def addEndEventListener(node: EventTarget, listener: (Event) => _) = {
    if (endEvents.isEmpty) {
      // if CSS transitions are not supported, trigger an "end animation event immediately
      //window.setTimeout(listener.asInstanceOf[Function0[Any]], 0)
      window.setTimeout(() => listener(null), 0)
    }
    else {
      endEvents.foreach { endEvent =>
        addEventListener(node, endEvent, listener)
      }
    }
  }

  def removeEndEventListener(node: EventTarget, listener: (Event) => _) = {
    endEvents.foreach { endEvent =>
      removeEventListener(node, endEvent, listener)
    }
  }
}
