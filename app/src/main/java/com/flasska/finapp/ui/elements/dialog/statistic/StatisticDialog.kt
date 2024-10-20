package com.flasska.finapp.ui.elements.dialog.statistic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flasska.finapp.AppUtils.appComponent
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.ExpenseList
import com.flasska.finapp.ui.theme.FinAppTheme
import com.flasska.findomain.entity.Expense
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun StatisticDialog(
    date: LocalDate,
    onExit: () -> Unit
) {
    val viewModel: StatisticViewModel = viewModel(
        factory = LocalContext.current.appComponent
            .provideStatisticUseCaseFactoryWrapper()
            .Factory()
    )
    val state by viewModel.state.collectAsState()
    viewModel.updateDate(date)

    StatisticDialog(
        screenState = state,
        onExit = onExit
    )
}

@Composable
private fun StatisticDialog(
    screenState: StatisticDialogState,
    onExit: () -> Unit
) {
    Dialog(onExit) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(vertical = 20.dp),
        ) {
            Text(
                text = stringResource(R.string.month_statistic, screenState.displayMonth),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Среднее за месяц: ${screenState.average}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Text(
                text = stringResource(R.string.month_expenses, screenState.sum),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.expenses_by_type),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            ExpenseList(
                list = screenState.typeToSum.toList().map {
                    Expense(
                        id = 0,
                        dateTime = LocalDate.now(),
                        value = it.second,
                        type = it.first
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FinAppTheme {
        StatisticDialog(
            screenState = StatisticDialogState(
                displayMonth = LocalDate.now().month
                    .getDisplayName(TextStyle.FULL, Locale("ru"))
            ),
            onExit = {}
        )
    }
}