package com.maelin.taskapp.presentation.task_list

import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.util.TaskOrder

sealed class TaskEvent {
    data class OrderEvent(val taskOrder: TaskOrder): TaskEvent()
    data class FilterEvent(val taskStatus: TaskStatus, val enabled: Boolean): TaskEvent()
    data class DeleteTaskEvent(val taskWithSubtasks: TaskWithSubtasks): TaskEvent()
    data class UpdateTaskStatusTaskEvent(val task: Task, val taskStatus: TaskStatus): TaskEvent()
    object RestoreTaskEvent: TaskEvent()
    object ToggleOrderSectionEvent: TaskEvent()
}
