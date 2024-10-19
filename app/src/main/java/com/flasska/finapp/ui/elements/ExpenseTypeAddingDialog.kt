package com.flasska.finapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.flasska.finapp.R
import com.flasska.finapp.ui.theme.FinAppTheme
import com.flasska.findomain.entity.Expense

private data class TypeState(
    val name: String = "",
    val color: Color? = null
)

private val colors = listOf(
    Color.Red,
    Color.Blue,
    Color.Cyan,
    Color.Green,
    Color.Magenta,
    Color.Yellow
)

@Composable
fun ExpenseTypeAddingDialog(
    onExit: () -> Unit,
    onSave: (Expense.Type) -> Unit
) {
    val screenState = remember { mutableStateOf(TypeState()) }

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
                color = screenState.value.color ?: Color.White
            )

            Text(
                text = stringResource(R.string.add_type),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )

            CustomTextField(
                value = screenState.value.name,
                onValueChange = { screenState.value = screenState.value.copy(name = it) },
                borderColor = MaterialTheme.colorScheme.primary,
                title = stringResource(R.string.name)
            )

            // TODO: choose color

            Button(
                onClick = {
                    screenState.value.color?.let { color ->
                        onSave(Expense.Type(color = color.toArgb(), name = screenState.value.name))
                    }
                },
                enabled = screenState.value.run { name.isNotBlank() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(stringResource(R.string.create))
            }
        }
    }
}

@Preview
@Composable
private fun ExpenseTypeAddingDialogPreview() {
    FinAppTheme {
        ExpenseTypeAddingDialog(onExit = {}, onSave = {})
    }
}