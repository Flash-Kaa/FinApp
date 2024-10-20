package com.flasska.finapp.ui.elements.dialog.expense

import com.flasska.findomain.entity.Expense

internal data class DialogAddState(
    val value: String = "",
    val valueIsError: Boolean = true,
    val type: Expense.Type? = null,
    val typeIsError: Boolean = true,
    val types: List<Expense.Type> = emptyList()
)