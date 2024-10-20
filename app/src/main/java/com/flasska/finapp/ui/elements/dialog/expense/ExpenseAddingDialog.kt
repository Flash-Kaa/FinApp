package com.flasska.finapp.ui.elements.dialog.expense

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flasska.finapp.AppUtils.appComponent
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.CustomTextField
import com.flasska.finapp.ui.elements.TopColorLine
import com.flasska.finapp.ui.elements.TypesList
import com.flasska.finapp.ui.theme.FinAppTheme

@Composable
fun ExpenseAddingDialog(
    onExit: () -> Unit,
) {
    val viewModel: ExpenseAddingViewModel = viewModel(
        factory = LocalContext.current.appComponent
            .provideExpenseViewModelFactoryWrapper()
            .Factory()
    )

    val state: DialogAddState by viewModel.state.collectAsState(DialogAddState())


    ExpenseAddingDialog(
        onExit = onExit,
        screenState = state,
        screenEvent = viewModel::getEvent
    )
}

@Composable
private fun ExpenseAddingDialog(
    onExit: () -> Unit,
    screenState: DialogAddState,
    screenEvent: (DialogAddEvent) -> Unit
) {
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
                color = screenState.type?.color?.let { Color(it) } ?: Color.White
            )

            Text(
                text = stringResource(R.string.add_expense),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )

            CustomTextField(
                value = screenState.value,
                onValueChange = { screenEvent(DialogAddEvent.ChangeValue(it)) },
                title = stringResource(R.string.expense),
                keyboardType = KeyboardType.Number,
                borderColor = if (screenState.run { valueIsError && value.isNotBlank() }) {
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
                types = screenState.types,
                chosen = screenState.type,
                onChoose = { screenEvent(DialogAddEvent.ChangeType(it)) },
                modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
            )

            Button(
                onClick = {
                    screenEvent(DialogAddEvent.Save)
                    onExit()
                },
                enabled = screenState.run { !valueIsError && !typeIsError },
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
                screenState = DialogAddState(),
                screenEvent = {}
            )
        }
    }
}