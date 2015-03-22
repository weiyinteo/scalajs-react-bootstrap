package com.acework.js.components.bootstrap

import com.acework.js.utils.{Mappable, Mergeable}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.raw.{HTMLInputElement, HTMLOptionElement}

import scala.collection.mutable.ArrayBuffer
import scala.scalajs.js.{UndefOr, undefined}

/**
 * Created by weiyin on 10/03/15.
 */
object Input extends BootstrapComponent {
  override type P = Input
  override type S = Unit
  override type B = Backend
  override type N = TopNode

  override def defaultProps = Input()

  val theInput = Ref[HTMLInputElement]("input")

  case class Input(id: UndefOr[String] = undefined,
                   `type`: UndefOr[String] = undefined,
                   label: UndefOr[String] = undefined,
                   help: UndefOr[String] = undefined,
                   placeholder: UndefOr[String] = undefined,
                   addonBefore: UndefOr[ReactNode] = undefined,
                   addonAfter: UndefOr[ReactNode] = undefined,
                   buttonBefore: UndefOr[ReactNode] = undefined,
                   buttonAfter: UndefOr[ReactNode] = undefined,
                   bsSize: UndefOr[Sizes.Value] = undefined,
                   bsStyle: UndefOr[Styles.Value] = undefined,
                   hasFeedback: UndefOr[Boolean] = undefined,
                   groupClassName: UndefOr[String] = undefined,
                   wrapperClassName: UndefOr[String] = undefined,
                   labelClassName: UndefOr[String] = undefined,
                   disabled: UndefOr[Boolean] = undefined,
                   multiple: Boolean = false,
                   value: UndefOr[String] = undefined,
                   defaultValue: UndefOr[String] = undefined,
                   checked: UndefOr[Boolean] = undefined,
                   readOnly: UndefOr[Boolean] = undefined,
                   onChange: UndefOr[() => Unit] = undefined,
                   ref: UndefOr[Ref] = undefined,
                   addClasses: String = "") extends MergeableProps[Input] {

    def merge(t: Map[String, Any]): Input = implicitly[Mergeable[Input]].merge(this, t)

    def asMap: Map[String, Any] = implicitly[Mappable[Input]].toMap(this)

    def apply(children: ReactNode*) = component(this, children)

    def apply() = component(this)
  }

  class Backend(scope: BackendScope[Input, Unit]) {
    def getInputDOMNode = {
      theInput(scope).get.getDOMNode()
    }

    def getValue = {
      if (scope.props.`type`.isDefined) {
        if (scope.props.`type`.get == "static")
          scope.props.value
        else if (scope.props.`type`.get == "select" && scope.props.multiple)
          getSelectedOptions
        else
          getInputDOMNode.nodeValue
      }
      else
      // FIXME js.Error("Cannot use getValue without specifying input type.")
        throw new RuntimeException("FIXME") //
    }

    def getChecked = getInputDOMNode.checked


    def getSelectedOptions = {
      val values = new ArrayBuffer[String]
      val nodeList = getInputDOMNode.getElementsByTagName("option")
      (0 until nodeList.length) foreach { i =>
        val option = nodeList(i).asInstanceOf[HTMLOptionElement]
        if (option.selected) {
          var value = option.getAttribute("value")
          if (value == null)
            value = option.innerHTML
          values.append(value)
        }
      }
      values.toArray
    }

    def isCheckboxOrRadio = {
      val tpe = scope.props.`type`.getOrElse("NA")
      tpe == "radio" || tpe == "checkbox"
    }

    def isFile = scope.props.`type`.getOrElse("NA") == "file"
  }


  override val component = ReactComponentB[Input]("Input")
    .stateless
    .backend(new Backend(_))
    .render { (P, C, _, B) =>

    def renderInput(): ReactNode = {
      if (!P.`type`.isDefined)
        C
      else {
        val classes = Map(
          "checked" -> P.checked.getOrElse(false),
          "readOnly" -> P.readOnly.getOrElse(false)
        )
        // FIXME spread props
        P.`type`.get match {
          case "select" =>
            <.select(^.classSet1M(s"form-control ${P.addClasses}", classes), ^.multiple := P.multiple,
              ^.tpe := P.`type`, ^.ref := theInput, ^.key := "input")(C)
          case "textarea" =>
            <.textarea(^.classSet1M(s"form-control ${P.addClasses}", classes),
              ^.tpe := P.`type`, ^.ref := theInput, ^.key := "input")(P.defaultValue)
          case "static" =>
            <.p(^.classSet1M(s"form-control-static ${P.addClasses}", classes), ^.value := P.value,
              ^.tpe := P.`type`, ^.ref := theInput, ^.key := "input")(P.value)
          case "submit" =>
            // FIXME ref
            Button.withKey("input")(Button.Button(componentClass = "input", `type` = "submit", value = P.value))
          case _ =>
            // FIXME spread properties
            val className = if (B.isCheckboxOrRadio || B.isFile) "" else "form-control"
            <.input(^.classSet1M(s"$className ${P.addClasses}", classes),
              ^.tpe := P.`type`, ^.ref := theInput, ^.key := "input")(P.value)
        }
      }
    }

    def renderInputGroup(children: ReactNode*): TagMod = {
      val addonBefore = if (P.addonBefore.isDefined)
        <.span(^.classSet1("input-group-addon"), ^.key := "addonBefore")(P.addonBefore.get)
      else
        EmptyTag

      val addonAfter = if (P.addonAfter.isDefined)
        <.span(^.classSet1("input-group-addon"), ^.key := "addonAfter")(P.addonAfter.get)
      else
        EmptyTag

      val buttonBefore = if (P.buttonBefore.isDefined)
        <.span(^.classSet1("input-group-btn"))(P.buttonBefore.get)
      else
        EmptyTag

      val buttonAfter = if (P.buttonAfter.isDefined)
        <.span(^.classSet1("input-group-btn"))(P.buttonAfter.get)
      else
        EmptyTag

      val inputGroupClassName = P.bsSize.getOrElse(Sizes.sm).toString

      if (P.addonBefore.isDefined || P.addonAfter.isDefined || P.buttonBefore.isDefined || P.buttonAfter.isDefined)
        <.div(^.classSet1(s"input-group $inputGroupClassName"), ^.key := "input-group",
          addonBefore,
          buttonBefore,
          children,
          addonAfter,
          buttonAfter
        )
      else
        children
    }

    def renderIcon(): TagMod = {
      if (P.hasFeedback.getOrElse(false))
        <.span(^.classSet1("glyphicon form-control-feedback",
          "glyphicon-ok" -> (P.bsStyle.getOrElse(Styles.info) == Styles.success),
          "glyphicon-warning-sign" -> (P.bsStyle.getOrElse(Styles.info) == Styles.warning),
          "glyphicon-remove" -> (P.bsStyle.getOrElse(Styles.info) == Styles.error)),
          ^.key := "icon")
      else
        EmptyTag
    }

    def renderHelp(): TagMod = {
      if (P.help.isDefined)
        <.span(^.className := "help-block", ^.key := "help", P.help)
      else
        EmptyTag
    }

    def renderCheckboxAndArdioWrapper(children: TagMod*) = {
      <.div(^.classSet("checkbox" -> (P.`type`.getOrElse("NA") == "checkbox"),
        "radio" -> (P.`type`.getOrElse("NA") == "radio")), children)
    }

    def renderWrapper(children: TagMod*): TagMod = {
      if (P.wrapperClassName.isDefined)
        <.div(^.className := P.wrapperClassName, ^.key := "wrapper", children)
      else
        children
    }

    def renderLabel(children: TagMod*): TagMod = {
      var classes = Map("control-label" -> B.isCheckboxOrRadio)
      if (P.labelClassName.isDefined)
        classes += (P.labelClassName.get -> true)

      if (P.label.isDefined)
        <.label(^.htmlFor := P.id, ^.classSetM(classes), children, P.label)
      else
        children
    }

    def renderFormGroup(children: TagMod*) = {
      var classes = Map(
        "has-feedback" -> P.hasFeedback.getOrElse(false),
        "has-success" -> (P.bsStyle.getOrElse(Styles.info) == Styles.success),
        "has-warning" -> (P.bsStyle.getOrElse(Styles.info) == Styles.warning),
        "has-error" -> (P.bsStyle.getOrElse(Styles.info) == Styles.error)
      )
      if (P.groupClassName.isDefined)
        classes += (P.groupClassName.get -> true)
      <.div(^.classSet1M("form-group", classes))(children)
    }

    if (B.isCheckboxOrRadio) {
      renderFormGroup(
        renderWrapper(
          renderCheckboxAndArdioWrapper(
            renderLabel(renderInput())),
          renderHelp()
        )
      )
    }
    else {
      renderFormGroup(renderLabel(),
        renderWrapper(
          renderInputGroup(renderInput()),
          renderIcon(),
          renderHelp()
        )
      )
    }
  }.build

}
