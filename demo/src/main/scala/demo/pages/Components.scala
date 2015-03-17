package demo.pages

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
          )
        ),
        <.div(^.className := "col-md-3",
          Affix(Affix.Props(addClasses = "bs-docs-sidebar hidden-print",
            role = "complementary": UndefOr[String], offset = 0, offsetTop = 0, offsetBottom = 0),
            Nav(Nav.Props(addClasses = "bs-docs-sidenav",
              activeHref = "", onSelect = () => (), ref = sideNavRef),
              SubNav(SubNav.Props(href = "#buttons", key = 1, text = "Buttons"),
                NavItem(NavItem.Props(href = "#btn-groups", key = 2), "Button groups")
                , NavItem(NavItem.Props(href = "#btn-dropdowns", key = 3), "Button dropdowns")
              )
              , NavItem(NavItem.Props(href = "#panels", key = 4), "Panels")
              , NavItem(NavItem.Props(href = "#modals", key = 5), "Modals")
              , NavItem(NavItem.Props(href = "#tooltips", key = 6), "Tooltips")
              , NavItem(NavItem.Props(href = "#popovers", key = 7), "Popovers")
              , NavItem(NavItem.Props(href = "#progress", key = 8), "Progress bars")
              , NavItem(NavItem.Props(href = "#navs", key = 9), "Navs")
              , NavItem(NavItem.Props(href = "#navbars", key = 10), "Navbars")
              , NavItem(NavItem.Props(href = "#tabss", key = 11), "Togglable tabs")
              , NavItem(NavItem.Props(href = "#pager", key = 12), "Pager")
              , NavItem(NavItem.Props(href = "#alerts", key = 13), "Alerts")
              , NavItem(NavItem.Props(href = "#carousels", key = 14), "Carousels")
              , NavItem(NavItem.Props(href = "#grids", key = 15), "Grids")
              , NavItem(NavItem.Props(href = "#listgroup", key = 16), "List group")
              , NavItem(NavItem.Props(href = "#labels", key = 17), "Labels")
              , NavItem(NavItem.Props(href = "#badges", key = 18), "Badges")
              , NavItem(NavItem.Props(href = "#jumbotron", key = 19), "Jumbotron")
              , NavItem(NavItem.Props(href = "#page-header", key = 20), "Page Header")
              , NavItem(NavItem.Props(href = "#wells", key = 21), "Wells")
              , NavItem(NavItem.Props(href = "#glyphicons", key = 22), "Glyphicons")
              , NavItem(NavItem.Props(href = "#tables", key = 23), "Tables")
              , NavItem(NavItem.Props(href = "#input", key = 24), "Input")
            )
          )
        )
      )
    )
  }).buildU
}
