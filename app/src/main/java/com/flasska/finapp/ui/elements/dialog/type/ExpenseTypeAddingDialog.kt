package com.flasska.finapp.ui.elements.dialog.type

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flasska.finapp.AppUtils.appComponent
import com.flasska.finapp.R
import com.flasska.finapp.ui.elements.CustomTextField
import com.flasska.finapp.ui.elements.TopColorLine
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
    Color.Yellow,
    Color.LightGray,
    Color.Gray,
    Color.DarkGray,
    Color.Black
)

@Composable
fun ExpenseTypeAddingDialog(onExit: () -> Unit) {
    val viewModel: ExpenseTypeAddingViewModel = viewModel(
        factory = LocalContext.current.appComponent
            .provideExpenseTypeViewModelFactoryWrapper()
            .Factory()
    )

    ExpenseTypeAddingDialog(
        onExit = onExit,
        onSave = viewModel::save
    )
}

@Composable
private fun ExpenseTypeAddingDialog(
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
            TopColorLine(color = screenState.value.color ?: Color.White)

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

            ColorChooser(screenState = screenState)
            CreateButton(
                screenState = screenState,
                onExit = onExit,
                onSave = onSave
            )
        }
    }
}

@Composable
private fun CreateButton(
    screenState: MutableState<TypeState>,
    onExit: () -> Unit,
    onSave: (Expense.Type) -> Unit
) {
    Button(
        onClick = {
            screenState.value.color?.let { color ->
                onSave(Expense.Type(color = color.toArgb(), name = screenState.value.name))
                onExit()
            }
        },
        enabled = screenState.value.run { name.isNotBlank() && color != null },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
    ) {
        Text(stringResource(R.string.create))
    }
}

@Composable
private fun ColorChooser(screenState: MutableState<TypeState>) {
    Text(
        text = stringResource(R.string.color),
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 4.dp)
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(50.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(colors) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(it)
                    .clickable(
                        enabled = it != screenState.value.color,
                        onClick = {
                            screenState.value = screenState.value.copy(color = it)
                        }
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                if (it == screenState.value.color) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.round_check_24),
                        tint = MaterialTheme.colorScheme.background,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
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