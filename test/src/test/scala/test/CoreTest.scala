package test

import com.acework.js.components.bootstrap._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom._
import japgolly.scalajs.react.vdom.prefix_<^._
import scala.scalajs.js, js.{Array => JArray}

import utest._
import TestUtil._

object CoreTest extends TestSuite {
  val tests = TestSuite {
    'alerts {
      'ExampleAlert {
        Alert(Alert.Alert(bsStyle = Styles.warning),
          <.strong("Holy guacamole!"), " Best check yo self, you're not looking too good.") shouldRender
          "<div class=\"alert-warning  alert\"><strong>Holy guacamole!</strong> Best check yo self, you&#x27;re not looking too good.</div>"
      }

    }

    'buttons {
      Button("Hi") shouldRender "<button type=\"button\" class=\"btn-default  btn\">Hi</button>"
    }

  }

}
