package com.maelin.taskapp.presentation.add_new_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import com.maelin.taskapp.presentation.add_new_task.components.SubtaskSection
import com.maelin.taskapp.presentation.add_new_task.components.TaskTitle

@Composable
fun AddNewTask(
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                onClick = { /*TODO*/ }

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task")
            }
        }
    ) { paddingValues ->
        Column (
            modifier = modifier.padding(
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            TaskTitle()
            SubtaskSection(onNewSubtask = { /*TODO*/ }, onSubtaskChange = { _,_ -> })
        }
    }
}