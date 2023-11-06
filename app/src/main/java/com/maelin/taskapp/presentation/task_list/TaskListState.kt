package com.maelin.taskapp.presentation.task_list

import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.util.OrderType
import com.maelin.taskapp.domain.util.TaskOrder

data class TaskListState(
    val tasks: List<TaskWithSubtasks> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.TimeCreated(OrderType.Descending),
    val includeStatuses: Map<TaskStatus, Boolean> = mapOf(
        Pair(TaskStatus.CREATED, true),
        Pair(TaskStatus.COMPLETED, true)
    ),
    val isOrderSectionVisible: Boolean = false
)
