package com.maelin.taskapp.domain.use_case

import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.repository.TaskRepository
import com.maelin.taskapp.domain.util.OrderType
import com.maelin.taskapp.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.TimeCreated(OrderType.Descending),
        includeStatuses: Map<TaskStatus, Boolean> = mapOf(
            Pair(TaskStatus.CREATED, true),
            Pair(TaskStatus.COMPLETED, true)
        ),
    ): Flow<List<TaskWithSubtasks>> {
        return taskRepository.getTasksWithSubtasks().map { taskList ->
            // filter out tasks that do match excluded statuses
            taskList.filter {
                includeStatuses[it.task.status] ?: false
            }
        }.map { taskWithSubtasks ->
            // sort tasks by user selected order (or default one)
            when(taskOrder.orderType) {
                OrderType.Ascending -> {
                    when(taskOrder) {
                        is TaskOrder.TimeCreated -> taskWithSubtasks.sortedBy { it.task.timeCreated }
                        is TaskOrder.TimeDue -> taskWithSubtasks.sortedBy { it.task.timeDue }
                        is TaskOrder.Name -> taskWithSubtasks.sortedBy { it.task.taskName }
                    }
                }
                OrderType.Descending -> {
                    when(taskOrder) {
                        is TaskOrder.TimeCreated -> taskWithSubtasks.sortedByDescending { it.task.timeCreated }
                        is TaskOrder.TimeDue -> taskWithSubtasks.sortedByDescending { it.task.timeDue }
                        is TaskOrder.Name -> taskWithSubtasks.sortedByDescending { it.task.taskName }
                    }
                }
            }
        }
    }
}