package com.maelin.taskapp.domain.use_case

import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskStatusUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task, newStatus: TaskStatus) {
        taskRepository.updateTaskStatus(
            task = task,
            newStatus = newStatus
        )
    }
}