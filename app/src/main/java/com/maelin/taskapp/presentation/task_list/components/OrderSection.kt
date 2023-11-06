package com.maelin.taskapp.presentation.task_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.util.OrderType
import com.maelin.taskapp.domain.util.TaskOrder
import com.maelin.taskapp.theme.TaskAppTheme

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder = TaskOrder.TimeCreated(OrderType.Descending),
    onOrderChange: (TaskOrder) -> Unit,
    includeStatuses: Map<TaskStatus, Boolean> = mapOf(
        Pair(TaskStatus.CREATED, true),
        Pair(TaskStatus.COMPLETED, true)
    ),
    onFilterChange: (TaskStatus, Boolean) -> Unit
) {
    Column (
        modifier = modifier
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterCheckbox(
                text = "Created",
                checked = includeStatuses[TaskStatus.CREATED] ?: false,
                onChecked = { onFilterChange(TaskStatus.CREATED, it)}
            )
            FilterCheckbox(
                text = "Completed",
                checked = includeStatuses[TaskStatus.COMPLETED] ?: false,
                onChecked = { onFilterChange(TaskStatus.COMPLETED, it)}
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OrderRadioButton(
                text = "Name",
                selected = taskOrder is TaskOrder.Name,
                onSelected = { onOrderChange(TaskOrder.Name(taskOrder.orderType)) })

            OrderRadioButton(
                text = "Time created",
                selected = taskOrder is TaskOrder.TimeCreated,
                onSelected = { onOrderChange(TaskOrder.TimeCreated(taskOrder.orderType)) })

            OrderRadioButton(
                text = "Time due",
                selected = taskOrder is TaskOrder.TimeDue,
                onSelected = { onOrderChange(TaskOrder.TimeDue(taskOrder.orderType)) })
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OrderRadioButton(
                text = "Ascending",
                selected = taskOrder.orderType is OrderType.Ascending,
                onSelected = { onOrderChange(taskOrder.copy(OrderType.Ascending)) })

            OrderRadioButton(
                text = "Descending",
                selected = taskOrder.orderType is OrderType.Descending,
                onSelected = { onOrderChange(taskOrder.copy(OrderType.Descending)) })
        }
    }
}

@Preview
@Composable
fun OrderSectionDarkModePreview() {
    TaskAppTheme(
        darkTheme = true
    ) {
        Surface {
            OrderSection(
                onOrderChange = { },
                onFilterChange = { _, _ -> }
            )
        }
    }
}

@Preview
@Composable
fun OrderSectionLightModePreview() {
    TaskAppTheme(
        darkTheme = false
    ) {
        Surface {
            OrderSection(
                onOrderChange = {  },
                onFilterChange = { _, _ -> }
            )
        }
    }
}