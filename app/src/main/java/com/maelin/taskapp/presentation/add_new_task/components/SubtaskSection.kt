package com.maelin.taskapp.presentation.add_new_task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maelin.taskapp.presentation.add_new_task.SubtaskState
import com.maelin.taskapp.theme.TaskAppTheme

@Composable
fun SubtaskSection(
    modifier: Modifier = Modifier,
    subtasks: List<SubtaskState> = listOf(),
    onNewSubtask: () -> Unit,
    onSubtaskChange: (Int, String) -> Unit
) {
    Column {
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            items(subtasks) { subtaskContent ->
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    TextField(
                        modifier = modifier.fillMaxWidth(),
                        onValueChange = {
                            onSubtaskChange(subtaskContent.order, it)
                        },
                        value = subtaskContent.text ?: subtaskContent.hint,
                        textStyle = MaterialTheme.typography.titleLarge

                    )
                }
            }
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.padding(4.dp)
                        .size(48.dp),
                    imageVector = Icons.Default.AddCircle,

                    contentDescription = "Add new task step")
                Text(
                    modifier = modifier.padding(4.dp),
                    text = "Add next step",
                    style = MaterialTheme.typography.titleLarge.plus(
                        TextStyle(fontWeight = FontWeight.Bold)
                    )
                )
            }

        }
    }
}

@Preview
@Composable
fun SubtaskSectionPreview() {
    TaskAppTheme {
        SubtaskSection(
            subtasks = listOf(SubtaskState("Text", "Hint", 1),
                SubtaskState("Text 2", "Hint", 2)),
            onNewSubtask = { },
            onSubtaskChange = { _,_ -> }
        )
    }
}