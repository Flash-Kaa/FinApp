package com.flasska.finapp.ui.screens.dayscreen

import com.flasska.findomain.entity.Expense
import java.time.LocalDate

internal data class DayScreenState(
    val expenses: List<Expense> = emptyList(),
    val date: LocalDate = LocalDate.now()
)