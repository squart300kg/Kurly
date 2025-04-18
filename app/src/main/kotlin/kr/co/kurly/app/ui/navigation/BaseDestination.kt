package kr.co.kurly.app.ui.navigation

import androidx.annotation.DrawableRes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kr.co.kurly.app.R
import kr.co.kurly.feature.home.HOME_BASE_ROUTE

val baseDestinations: ImmutableList<BaseDestination> =
  BaseDestination.entries.toImmutableList()

enum class BaseDestination(
  @DrawableRes val selectedIconRes: Int,
  @DrawableRes val unselectedIconRes: Int,
  val iconTextIdRes: String,
  val route: String
) {
  FIRST(
    selectedIconRes = R.drawable.tab_first_on,
    unselectedIconRes = R.drawable.tab_first_off,
    iconTextIdRes = "first",
    route = HOME_BASE_ROUTE
  )
}