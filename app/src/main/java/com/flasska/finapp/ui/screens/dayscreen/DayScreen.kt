package com.flasska.finapp.ui.screens.dayscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.ExpenseList
import com.flasska.finapp.ui.utils.DateUtils.getDateInFormat

@Composable
internal fun DayScreen(
    screenState: DayScreenState,
    screenEvent: (DayScreenEvent) -> Unit
) {
    Column {
        TopAppBar(screenEvent, screenState)
        ExpenseList(screenState.expenses)
    }
}

@Composable
private fun TopAppBar(
    screenEvent: (DayScreenEvent) -> Unit,
    screenState: DayScreenState
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

            IconButton(
                onClick = { screenEvent(DayScreenEvent.NextDay) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_forward),
                    contentDescription = stringResource(R.string.see_one_day_next)
                )
            }
        }
    }
}