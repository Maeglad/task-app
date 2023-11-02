package com.maelin.taskapp.domain.use_case

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
        taskOrder: TaskOrder = TaskOrder.TimeCreated(OrderType.Descending)
    ): Flow<List<TaskWithSubtasks>> {
        return taskRepository.getTasksWithSubtasks().map { taskWithSubtasks ->
            when(taskOrder.orderType) {
                OrderType.Ascending -> {
                    when(taskOrder) {
                        is TaskOrder.TimeCreated -> taskWithSubtasks.sortedBy { it.task.timeCreated }
                        is TaskOrder.TimeDue -> taskWithSubtasks.sortedBy { it.task.timeDue }
                        is TaskOrder.Title -> taskWithSubtasks.sortedBy { it.task.taskName }
                    }
                }
                OrderType.Descending -> {
                    when(taskOrder) {
                        is TaskOrder.TimeCreated -> taskWithSubtasks.sortedByDescending { it.task.timeCreated }
                        is TaskOrder.TimeDue -> taskWithSubtasks.sortedByDescending { it.task.timeDue }
                        is TaskOrder.Title -> taskWithSubtasks.sortedByDescending { it.task.taskName }
                    }
                }
            }
        }
    }
}