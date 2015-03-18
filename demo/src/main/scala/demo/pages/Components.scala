package demo.pages

import com.acework.js.utils.TransitionEvent
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import com.acework.js.components.bootstrap._
import demo.examples._

import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr._

/**
 * Created by weiyin on 16/03/15.
 */
object Components {

  val alerts = <.div(
    PageHeader(<.h1("Alert messages ", <.small("alert"))),
    <.h3("Example Alerts"),
    Alert(Alert.Props(bsStyle = Styles.warning),
      <.strong("Holy guacamole!"), "Best check yo self, you're not looking too good."),
    <.h3("closable alert"),
    AlertDismissable(),
    <.h3("Auto hide alert"),
    AlertAutoDismissable(),
    Alert(Alert.Props(bsStyle = Styles.success), "Well done! You successfully read this important alert message."),
    Alert(Alert.Props(bsStyle = Styles.info), "Heads up! This alert needs your attention, but it's not super important."),
    Alert(Alert.Props(bsStyle = Styles.warning), "Warning! Better check yourself, you're not looking too good."),
    Alert(Alert.Props(bsStyle = Styles.danger), "Oh snap! Change a few things up and try submitting again.")
  )

  val sideNavRef = Ref("sideNav")
  val content = ReactComponentB[Unit]("Components")
    .stateless
    .render(P => {
    <.div(^.className := "container bs-docs-container",
      <.div(^.className := "row",
        <.div(^.className := "col-md-9", ^.role := "main",
          <.div(
            Buttons.content()
            , ButtonGroups.content()
            , DropdownButtons.content()
            , Panels.content()
            , Modals.content()
            , Tooltips.content()
            , Popovers.content()
            , Progressbars.content()
            , Navs.content()
            , Tabs.content()
            , Pagers.content()
            , Alerts.content()
            , Carousels.content()
            , Grids.content()
            , ListGroups.content()
            , Labels.content()
            , Jumbotrons.content()
            , PageHeaders.content()
            , Wells.content()
            , Glyphicons.content()
            , Tables.content()
            , Inputs.content()
          )
        ),
        <.div(^.className := "col-md-3",
          Affix(Affix.Props(addClasses = "bs-docs-sidebar hidden-print",
            role = "complementary": UndefOr[String], offset = 0, offsetTop = 0, offsetBottom = 0),
            Nav.withRef("sideRef")(Nav.Props(addClasses = "bs-docs-sidenav", activeHref = "", onSelect = (_: String) => ()),
              SubNav.withKey(1)(SubNav.Props(href = "#buttons", text = "Buttons"),
                NavItem.withKey(2)(NavItem.Props(href = "#btn-groups"), "Button groups")
                , NavItem.withKey(3)(NavItem.Props(href = "#btn-dropdowns"), "Button dropdowns")
              )
              , NavItem.withKey(4)(NavItem.Props(href = "#panels"), "Panels")
              , NavItem.withKey(5)(NavItem.Props(href = "#modals"), "Modals")
              , NavItem.withKey(6)(NavItem.Props(href = "#tooltips"), "Tooltips")
              , NavItem.withKey(7)(NavItem.Props(href = "#popovers"), "Popovers")
              , NavItem.withKey(8)(NavItem.Props(href = "#progress"), "Progress bars")
              , NavItem.withKey(9)(NavItem.Props(href = "#navs"), "Navs")
              , NavItem.withKey(10)(NavItem.Props(href = "#navbars"), "Navbars")
              , NavItem.withKey(11)(NavItem.Props(href = "#tabss"), "Togglable tabs")
              , NavItem.withKey(12)(NavItem.Props(href = "#pager"), "Pager")
              , NavItem.withKey(13)(NavItem.Props(href = "#alerts"), "Alerts")
              , NavItem.withKey(14)(NavItem.Props(href = "#carousels"), "Carousels")
              , NavItem.withKey(15)(NavItem.Props(href = "#grids"), "Grids")
              , NavItem.withKey(16)(NavItem.Props(href = "#listgroup"), "List group")
              , NavItem.withKey(17)(NavItem.Props(href = "#labels"), "Labels")
              , NavItem.withKey(18)(NavItem.Props(href = "#badges"), "Badges")
              , NavItem.withKey(19)(NavItem.Props(href = "#jumbotron"), "Jumbotron")
              , NavItem.withKey(20)(NavItem.Props(href = "#page-header"), "Page Header")
              , NavItem.withKey(21)(NavItem.Props(href = "#wells"), "Wells")
              , NavItem.withKey(22)(NavItem.Props(href = "#glyphicons"), "Glyphicons")
              , NavItem.withKey(23)(NavItem.Props(href = "#tables"), "Tables")
              , NavItem.withKey(24)(NavItem.Props(href = "#input"), "Input")
            )
          )
        )
      )
    )
  }).buildU
}
