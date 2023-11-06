package com.maelin.taskapp.presentation.task_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maelin.taskapp.domain.model.Subtask
import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.theme.TaskAppTheme

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: TaskWithSubtasks,
    onDone: () -> Unit,
    onDelete: () -> Unit
) {
    val taskName =
        when (task.task.status) {
            TaskStatus.COMPLETED -> {
                task.task.taskName + " (Completed)"
            }
            else -> {
                task.task.taskName
            }
        }

    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize()
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = taskName,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                // cancel task button
                IconButton(
                    onClick = { onDelete() }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete task"
                    )
                }
                // complete task button
                IconButton(
                   onClick = { onDone() }) {
                   Icon(
                       imageVector = Icons.Default.Done,
                       contentDescription = "Mark task as done"
                   )
               }

                IconButton(onClick = {  }) {
                    Icon(imageVector = if (expanded)
                                            Icons.Default.KeyboardArrowUp
                                        else
                                            Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand card")
                }
            }

            AnimatedVisibility(visible = expanded) {
                Row (modifier = Modifier.fillMaxWidth()) {
                    SubtaskList(subtasks = task.subtasks)
                }
            }
        }

    }
}

@Composable
fun SubtaskList(
    modifier: Modifier = Modifier,
    subtasks: List<Subtask>?
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row {
        Column (modifier = modifier.padding(
                                       top = 10.dp,
                                       bottom = 15.dp)
        ) {
            subtasks?.forEach {
                Row (modifier = Modifier.padding(start = 15.dp)) {
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskItemDarkModePreview() {
    val task = TaskWithSubtasks(
        task = Task(
            id = 1,
            status = TaskStatus.CREATED,
            taskName = "Task name",
            timeCreated = System.nanoTime(),
            timeDue = System.nanoTime()
        ),
        subtasks = listOf(
            Subtask(
                id = 1,
                taskId = 1,
                description = "Subtask description",
                order = 1
            )
        )
    )

    TaskAppTheme(
        darkTheme = true
    ) {
        TaskItem(
            task = task,
            onDone = { }) {
        }
    }
}

@Preview
@Composable
fun TaskItemLightModePreview() {
    val task = TaskWithSubtasks(
        task = Task(
            id = 1,
            status = TaskStatus.CREATED,
            taskName = "Task name",
            timeCreated = System.nanoTime(),
            timeDue = System.nanoTime()
        ),
        subtasks = listOf(
            Subtask(
                id = 1,
                taskId = 1,
                description = "Subtask description",
                order = 1
            )
        )
    )

    TaskAppTheme(
        darkTheme = false
    ) {
        TaskItem(
            task = task,
            onDone = { }) {
        }
    }
}