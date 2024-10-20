package com.flasska.finapp.ui.elements.dialog.type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.usecase.AddUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class ExpenseTypeAddingViewModel(
    private val addUseCase: AddUseCase<Expense.Type>
) : ViewModel() {
    fun save(value: Expense.Type) {
        viewModelScope.launch(Dispatchers.IO) {
            addUseCase(value)
        }
    }

    class FactoryWrapper(
        private val addUseCase: AddUseCase<Expense.Type>
    ) {
        inner class Factory : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ExpenseTypeAddingViewModel(addUseCase) as T
            }
        }
    }
}