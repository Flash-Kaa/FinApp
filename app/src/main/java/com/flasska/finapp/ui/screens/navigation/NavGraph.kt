package com.flasska.finapp.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flasska.finapp.ui.screens.dayscreen.DayScreenDrawer
import java.time.LocalDate

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val now = LocalDate.now()

    NavHost(
        navController = navController,
        startDestination = NavScreens.DayExpenseScreen.getRouteWithArgs(
            day = now.dayOfMonth,
            month = now.month,
            year = now.year
        )
    ) {
        composable(
            route = NavScreens.DayExpenseScreen.getPath(),
            arguments = NavScreens.DayExpenseScreen.args
        ) {
            val d = it.arguments?.getInt(NavScreens.DayExpenseScreen.DAY_ARG) ?: 1
            val m = it.arguments?.getInt(NavScreens.DayExpenseScreen.MONTH_ARG) ?: 1
            val y = it.arguments?.getInt(NavScreens.DayExpenseScreen.YEAR_ARG) ?: 1980

            DayScreenDrawer(LocalDate.of(y, m, d))
        }
    }
}