package com.flasska.finapp.ui.elements.dialog.expense

import com.flasska.findomain.entity.Expense
import java.time.LocalDate

internal sealed class DialogAddEvent {
    data class ChangeValue(val value: String) : DialogAddEvent()
    data class ChangeType(val value: Expense.Type) : DialogAddEvent()
    data class ChangeDate(val value: LocalDate) : DialogAddEvent()
    data object Save : DialogAddEvent()
}