package com.flasska.finapp.ui.screens.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.time.Month

internal sealed class NavScreens(val route: String) {
    data object DayExpenseScreen : NavScreens("day_screen") {
        const val YEAR_ARG = "year"
        const val MONTH_ARG = "month"
        const val DAY_ARG = "day"

        val args = listOf(
            navArgument(YEAR_ARG) { type = NavType.IntType },
            navArgument(MONTH_ARG) { type = NavType.IntType },
            navArgument(DAY_ARG) { type = NavType.IntType },
        )

        fun getPath() = "{$route}/{$YEAR_ARG}/{$MONTH_ARG}/{$DAY_ARG}"

        fun getRouteWithArgs(day: Int, month: Month, year: Int) =
            "$route/$year/${month.value}/$day"
    }
}