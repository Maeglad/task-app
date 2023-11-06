package com.maelin.taskapp.presentation.task_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maelin.taskapp.theme.TaskAppTheme

@Composable
fun FilterCheckbox(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onBackground
            ))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun FilterCheckboxDarkModePreview() {
    TaskAppTheme(
        darkTheme = true
    ) {
        Surface {
            Column {
                FilterCheckbox(
                    text = "Unchecked",
                    checked = false,
                    onChecked = {}
                )
                FilterCheckbox(
                    text = "Checked",
                    checked = true,
                    onChecked = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterCheckboxLightModePreview() {
    TaskAppTheme(
        darkTheme = false
    ) {
        Surface {
            Column {
                FilterCheckbox(
                    text = "Unchecked",
                    checked = false,
                    onChecked = {}
                )
                FilterCheckbox(
                    text = "Checked",
                    checked = true,
                    onChecked = {}
                )
            }
        }
    }
}