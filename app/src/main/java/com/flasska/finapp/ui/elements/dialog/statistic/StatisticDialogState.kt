package com.flasska.finapp.ui.elements.dialog.statistic

import com.flasska.findomain.entity.Expense
import java.time.LocalDate

internal data class StatisticDialogState(
    val displayMonth: String = "",
    val typeToSum: Map<Expense.Type, Float> = mapOf(),
    val sum: Float = 0f,
    val average: Float = 0f,
)