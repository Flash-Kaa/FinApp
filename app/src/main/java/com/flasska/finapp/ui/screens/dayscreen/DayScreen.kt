package com.flasska.finapp.ui.screens.dayscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.dialogadd.expense.ExpenseAddingDialog
import com.flasska.finapp.ui.elements.ExpenseList
import com.flasska.finapp.ui.utils.DateUtils.getDateInFormat

@Composable
internal fun DayScreen(
    screenState: DayScreenState,
    screenEvent: (DayScreenEvent) -> Unit
) {
    var addDialogIsOpen: Boolean by remember { mutableStateOf(false) }
    var statDialogIsOpen: Boolean by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            screenState = screenState,
            screenEvent = screenEvent,
            onAddClick = { addDialogIsOpen = true },
            onStatClick = { statDialogIsOpen = true }
        )

        ExpenseList(screenState.expenses)
    }

    if (addDialogIsOpen) {
        ExpenseAddingDialog(onExit = { addDialogIsOpen = false })
    } else if (statDialogIsOpen) {

    }
}

@Composable
private fun TopAppBar(
    screenState: DayScreenState,
    screenEvent: (DayScreenEvent) -> Unit,
    onAddClick: () -> Unit,
    onStatClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 32.dp, vertical = 8.dp)
        ) {
            IconButton(
                onClick = { screenEvent(DayScreenEvent.PreviousDay) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
                    contentDescription = stringResource(R.string.see_one_day_ago)
                )
            }

            Text(text = screenState.date.getDateInFormat())

            IconButton(onClick = { screenEvent(DayScreenEvent.NextDay) }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_forward),
                    contentDescription = stringResource(R.string.see_one_day_next)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = onAddClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.round_add_24),
                    contentDescription = stringResource(R.string.add_expense)
                )
            }

            IconButton(onClick = onStatClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.pie_char),
                    contentDescription = stringResource(R.string.see_month_stat)
                )
            }
        }
    }
}