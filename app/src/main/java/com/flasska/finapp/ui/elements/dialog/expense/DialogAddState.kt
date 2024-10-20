package com.flasska.finapp.ui.elements.dialog.expense

import com.flasska.findomain.entity.Expense
import java.time.LocalDate

internal data class DialogAddState(
    val date: LocalDate = LocalDate.now(),
    val value: String = "",
    val valueIsError: Boolean = true,
    val type: Expense.Type? = null,
    val typeIsError: Boolean = true,
    val types: List<Expense.Type> = emptyList()
)