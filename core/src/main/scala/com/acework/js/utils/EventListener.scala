package com.acework.js.utils

import org.scalajs.dom.raw.{Event, EventTarget, HTMLElement}

import org.scalajs.dom.{raw, document}

import scala.scalajs.js

/**
 * Created by weiyin on 11/03/15.
 */

trait EventListener {
  def remove(): Unit
}

object EventListener {

  /**
   * Listen to DOM events during the bubble phase.
   *
   * @param target DOM element to register listener on.
   * @param eventType Event type, e.g. 'click' or 'mouseover'.
   * @param listener Callback function.
   * @return EventListener with a `remove` method.
   * @return
   */
  def listen[T <: Event](target: EventTarget, eventType: String, listener: (T) => _) = {
    target.addEventListener(eventType, listener, false)

    new EventListener {
      override def remove() = {
        target.removeEventListener(eventType, listener.asInstanceOf[js.Function1[Event, _]], false)
      }
    }
  }

  // TODO implement attachEvent
  /*
  if (target.attachEvent) {
    target.attachEvent('on ' +eventType, callback);
    return {
      remove: function () {
        target.detachEvent('on ' +eventType, callback);
      }
    };
  }*/

}
