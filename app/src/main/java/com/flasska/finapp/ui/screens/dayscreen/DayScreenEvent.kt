package com.flasska.finapp.ui.screens.dayscreen

internal sealed class DayScreenEvent {
    data object NextDay: DayScreenEvent()
    data object PreviousDay: DayScreenEvent()
}