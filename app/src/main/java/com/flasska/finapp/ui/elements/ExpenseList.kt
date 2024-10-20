package com.flasska.finapp.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.flasska.findomain.entity.Expense

@Composable
fun ExpenseList(list: List<Expense>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            Spacer(Modifier.height(16.dp))
        }

        items(list) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp)
            ) {
                Text(
                    text = String.format("%.2f", it.value),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.width(16.dp))

                TypeDrawer(
                    type = it.type,
                    isClickable = false
                )
            }
        }
    }
}