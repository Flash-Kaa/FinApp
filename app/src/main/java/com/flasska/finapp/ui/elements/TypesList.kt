package com.flasska.finapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.dialogadd.type.ExpenseTypeAddingDialog
import com.flasska.finapp.ui.theme.FinAppTheme
import com.flasska.findomain.entity.Expense

@Composable
fun TypesList(
    types: List<Expense.Type>,
    chosen: Expense.Type?,
    onChoose: (Expense.Type) -> Unit,
    modifier: Modifier = Modifier
) {
    var createTypeDialogIsOpen: Boolean by remember { mutableStateOf(false) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(types) {
            TypeDrawer(
                type = it,
                isChosen = it == chosen,
                onClick = { onChoose(it) }
            )
        }

        item {
            ElementDrawer(
                text = stringResource(R.string.create),
                onClick = {
                    createTypeDialogIsOpen = true
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.round_add_24),
                    contentDescription = stringResource(R.string.create)
                )
            }
        }
    }

    if (createTypeDialogIsOpen) {
        ExpenseTypeAddingDialog(
            onExit = {
                createTypeDialogIsOpen = false
            }
        )
    }
}

@Composable
fun TypeDrawer(
    type: Expense.Type,
    isChosen: Boolean = false,
    isClickable: Boolean = true,
    onClick: () -> Unit = {},
) {
    ElementDrawer(
        text = type.name,
        isChosen = isChosen,
        onClick = onClick,
        isClickable = isClickable
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color(type.color), shape = CircleShape)
        )
    }
}

@Composable
private fun ElementDrawer(
    text: String,
    onClick: () -> Unit,
    isChosen: Boolean = false,
    isClickable: Boolean = true,
    icon: @Composable () -> Unit = {}
) {
    val background = if (isChosen) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceContainer
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = background,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable(enabled = isClickable, onClick = onClick)
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            content = { icon() }
        )

        Text(
            text = text, modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun TypesListPreview() {
    FinAppTheme {
        TypesList(
            listOf(
                Expense.Type(0, "name", color = Color.Red.toArgb()),
                Expense.Type(0, "Long Long Long Long Long name ", color = Color.Red.toArgb()),
                Expense.Type(0, "nameLongLongLongLongLongLong", color = Color.Red.toArgb()),
                Expense.Type(0, "nameqw", color = Color.Red.toArgb()),
                Expense.Type(0, "name43qw", color = Color.Red.toArgb()),
            ),
            onChoose = {},
            chosen = Expense.Type(0, "nameqw", color = Color.Red.toArgb())
        )
    }
}
