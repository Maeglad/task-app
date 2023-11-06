package com.maelin.taskapp.presentation.task_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.presentation.task_list.components.OrderSection
import com.maelin.taskapp.presentation.task_list.components.TaskItem
import com.maelin.taskapp.theme.TaskAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: TaskListState,
    onEvent: (TaskEvent) -> Unit
) {
    //val state = viewModel.state.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                modifier = modifier.background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new task"
                )
            }
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current)
                )
                .padding(16.dp),
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tasks",
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(onClick = {
                    onEvent(TaskEvent.ToggleOrderSectionEvent)
                }) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Change task order or filter tasks"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    taskOrder = state.taskOrder,
                    includeStatuses = state.includeStatuses,
                    onOrderChange = {
                        onEvent(TaskEvent.OrderEvent(it))
                    },
                    onFilterChange = { taskStatus, isEnabled ->
                        onEvent(TaskEvent.FilterEvent(taskStatus, isEnabled))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.tasks) { task ->
                    TaskItem(
                        modifier = Modifier.fillMaxWidth(),
                        task = task,
                        onDone = {
                            val currentStatus = task.task.status
                            onEvent(
                                TaskEvent.UpdateTaskStatusTaskEvent(
                                    task.task,
                                    TaskStatus.COMPLETED
                                )
                            )
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Task completed",
                                    actionLabel = "Undo",
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    onEvent(TaskEvent.UpdateTaskStatusTaskEvent(
                                        task.task,
                                        taskStatus = currentStatus
                                    ))
                                }
                            }
                        },
                        onDelete = {
                            onEvent(TaskEvent.DeleteTaskEvent(task))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Task deleted",
                                    actionLabel = "Undo",
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    onEvent(TaskEvent.RestoreTaskEvent)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskListDarkModePreview() {
        TaskAppTheme (
            darkTheme = true
        ) {
            TaskListSection(
                navController = rememberNavController(),
                state = TaskListState(),
                onEvent = {}
            )
        }


}

@Preview
@Composable
fun TaskListLightModePreview() {
    TaskAppTheme (
        darkTheme = false
    ) {
        TaskListSection(
            navController = rememberNavController(),
            state = TaskListState(),
            onEvent = {}
        )
    }
}