package com.flasska.finapp.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.flasska.findomain.entity.Expense

@Composable
fun ExpenseList(list: List<Expense>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(list) {
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
    }
}