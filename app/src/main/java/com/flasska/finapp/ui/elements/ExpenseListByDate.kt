package com.flasska.finapp.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flasska.finapp.ui.utils.DateUtils.getDateInFormat
import com.flasska.findomain.entity.Expense
import java.time.LocalDate


@Composable
fun ExpenseListByDate(list: List<Expense>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        var lastDate = LocalDate.MIN

        items(list) {
            if (it.dateTime != lastDate) {
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    if (it.dateTime != lastDate) {
                        lastDate = it.dateTime

                        Text(
                            text = it.dateTime.getDateInFormat(),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                TypeDrawer(
                    type = it.type,
                    isClickable = false
                )

                Text(
                    text = String.format("%.2f", it.value),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}