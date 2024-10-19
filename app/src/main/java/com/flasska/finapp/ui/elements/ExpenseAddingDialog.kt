package com.flasska.finapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.flasska.finapp.R
import com.flasska.finapp.ui.theme.FinAppTheme
import com.flasska.findomain.entity.Expense
import java.time.LocalDateTime

private data class State(
    val value: String = "",
    val valueIsError: Boolean = true,
    val type: Expense.Type? = null,
    val typeIsError: Boolean = true,
    val types: List<Expense.Type> = emptyList()
)

@Composable
fun ExpenseAddingDialog(
    onExit: () -> Unit,
    onSave: (Expense) -> Unit
) {
    val screenState = remember { mutableStateOf(State()) }

    Dialog(onExit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 16.dp)
        ) {
            TopColorLine(
                color = screenState.value.type?.color?.let { Color(it) } ?: Color.White
            )

            Text(
                text = stringResource(R.string.add_expense),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )

            CustomTextField(
                value = screenState.value.value,
                onValueChange = {
                    screenState.value = screenState.value.copy(
                        value = it,
                        valueIsError = it.toFloatOrNull() == null
                    )
                },
                title = stringResource(R.string.expense),
                keyboardType = KeyboardType.Number,
                borderColor = if (screenState.value.run { valueIsError && value.isNotBlank() }) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )

            Text(
                text = stringResource(R.string.type),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            TypesList(
                types = screenState.value.types,
                chosen = screenState.value.type,
                onChoose = {
                    screenState.value = screenState.value.copy(
                        type = it,
                        typeIsError = false
                    )
                },
                modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
            )

            Button(
                onClick = {
                    screenState.value.type?.let { type ->
                        val value = screenState.value.value.toFloatOrNull()
                        if (value != null) {
                            onSave(Expense(0, LocalDateTime.now(), value, type))
                        }
                    }
                },
                enabled = screenState.value.run { !valueIsError && !typeIsError },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(stringResource(R.string.add))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FinAppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ExpenseAddingDialog(
                onExit = {},
                onSave = {}
            )
        }
    }
}