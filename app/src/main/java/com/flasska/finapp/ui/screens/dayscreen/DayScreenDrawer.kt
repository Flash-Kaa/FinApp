package com.flasska.finapp.ui.screens.dayscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flasska.finapp.AppUtils.appComponent
import java.time.LocalDate

@Composable
fun DayScreenDrawer(
    date: LocalDate
) {
    val viewModel: DayScreenViewModel = viewModel(
        factory = LocalContext.current.appComponent
            .provideDayScreenViewModelFactoryWrapper()
            .Factory(date)
    )

    val screenState: DayScreenState by viewModel.state.collectAsState(DayScreenState())

    DayScreen(
        screenState = screenState,
        screenEvent = viewModel::getEvent
    )
}