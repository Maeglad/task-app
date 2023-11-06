package com.maelin.taskapp.presentation.task_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maelin.taskapp.theme.TaskAppTheme

@Composable
fun OrderRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(
            selected = selected,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun OrderRadioButtonDarkModePreview() {
    TaskAppTheme(
        darkTheme = true
    ) {
        Surface {
            Column {
                OrderRadioButton(
                    text = "Unselected",
                    selected = false,
                    onSelected = { })
                OrderRadioButton(
                    text = "Selected",
                    selected = true,
                    onSelected = { })
            }
        }
    }
}


@Preview
@Composable
fun OrderRadioButtonLightModePreview() {
    TaskAppTheme(
        darkTheme = false
    ) {
        Surface {
            Column {
                OrderRadioButton(
                    text = "Unselected",
                    selected = false,
                    onSelected = { })
                OrderRadioButton(
                    text = "Selected",
                    selected = true,
                    onSelected = { })
            }
        }
    }
}