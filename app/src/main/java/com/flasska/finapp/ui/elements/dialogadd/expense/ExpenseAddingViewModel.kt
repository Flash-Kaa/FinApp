package com.flasska.finapp.ui.elements.dialogadd.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.flasska.findomain.entity.Expense
import com.flasska.findomain.usecase.AddUseCase
import com.flasska.findomain.usecase.GetAllUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime


internal class ExpenseAddingViewModel(
    private val addUseCase: AddUseCase<Expense>,
    private val getTypesUseCase: GetAllUseCase<Expense.Type>
) : ViewModel() {
    private val _state = MutableStateFlow(DialogAddState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getTypesUseCase().collectLatest { types ->
                _state.update { it.copy(types = types) }
            }
        }
    }

    fun getEvent(event: DialogAddEvent) {
        when (event) {
            is DialogAddEvent.ChangeType -> _state.update {
                it.copy(type = event.value, typeIsError = false)
            }

            is DialogAddEvent.ChangeValue -> _state.update {
                it.copy(value = event.value, valueIsError = event.value.toFloatOrNull() == null)
            }

            DialogAddEvent.Save -> save()
        }
    }

    private fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = state.value
            value.type?.let {
                val float = value.value.toFloatOrNull()
                if (float != null) {
                    addUseCase(Expense(0, LocalDateTime.now(), float, it))
                }
            }
        }
    }

    class FactoryWrapper(
        private val addExpenseUseCase: AddUseCase<Expense>,
        private val getTypesUseCase: GetAllUseCase<Expense.Type>
    ) {
        inner class Factory : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ExpenseAddingViewModel(
                    addUseCase = addExpenseUseCase,
                    getTypesUseCase = getTypesUseCase
                ) as T
            }
        }
    }
}