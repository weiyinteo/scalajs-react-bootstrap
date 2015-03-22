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
    Alert(Alert.Alert(bsStyle = Styles.warning),
      <.strong("Holy guacamole!"), "Best check yo self, you're not looking too good."),
    <.h3("closable alert"),
    AlertDismissable(),
    <.h3("Auto hide alert"),
    AlertAutoDismissable(),
    Alert(Alert.Alert(bsStyle = Styles.success), "Well done! You successfully read this important alert message."),
    Alert(Alert.Alert(bsStyle = Styles.info), "Heads up! This alert needs your attention, but it's not super important."),
    Alert(Alert.Alert(bsStyle = Styles.warning), "Warning! Better check yourself, you're not looking too good."),
    Alert(Alert.Alert(bsStyle = Styles.danger), "Oh snap! Change a few things up and try submitting again.")
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
            Nav.withRef("sideRef")(Nav.Nav(addClasses = "bs-docs-sidenav", activeHref = "", onSelect = (_: Seq[UndefOr[String]]) => ()),
              SubNav.withKey(1)(SubNav.SubNav(href = "#buttons", text = "Buttons"),
                NavItem.withKey(2)(NavItem.NavItem(href = "#btn-groups"), "Button groups")
                , NavItem.withKey(3)(NavItem.NavItem(href = "#btn-dropdowns"), "Button dropdowns")
              )
              , NavItem.withKey(4)(NavItem.NavItem(href = "#panels"), "Panels")
              , NavItem.withKey(5)(NavItem.NavItem(href = "#modals"), "Modals")
              , NavItem.withKey(6)(NavItem.NavItem(href = "#tooltips"), "Tooltips")
              , NavItem.withKey(7)(NavItem.NavItem(href = "#popovers"), "Popovers")
              , NavItem.withKey(8)(NavItem.NavItem(href = "#progress"), "Progress bars")
              , NavItem.withKey(9)(NavItem.NavItem(href = "#navs"), "Navs")
              , NavItem.withKey(10)(NavItem.NavItem(href = "#navbars"), "Navbars")
              , NavItem.withKey(11)(NavItem.NavItem(href = "#tabss"), "Togglable tabs")
              , NavItem.withKey(12)(NavItem.NavItem(href = "#pager"), "Pager")
              , NavItem.withKey(13)(NavItem.NavItem(href = "#alerts"), "Alerts")
              , NavItem.withKey(14)(NavItem.NavItem(href = "#carousels"), "Carousels")
              , NavItem.withKey(15)(NavItem.NavItem(href = "#grids"), "Grids")
              , NavItem.withKey(16)(NavItem.NavItem(href = "#listgroup"), "List group")
              , NavItem.withKey(17)(NavItem.NavItem(href = "#labels"), "Labels")
              , NavItem.withKey(18)(NavItem.NavItem(href = "#badges"), "Badges")
              , NavItem.withKey(19)(NavItem.NavItem(href = "#jumbotron"), "Jumbotron")
              , NavItem.withKey(20)(NavItem.NavItem(href = "#page-header"), "Page Header")
              , NavItem.withKey(21)(NavItem.NavItem(href = "#wells"), "Wells")
              , NavItem.withKey(22)(NavItem.NavItem(href = "#glyphicons"), "Glyphicons")
              , NavItem.withKey(23)(NavItem.NavItem(href = "#tables"), "Tables")
              , NavItem.withKey(24)(NavItem.NavItem(href = "#input"), "Input")
            )
          )
        )
      )
    )
  }).buildU
}
