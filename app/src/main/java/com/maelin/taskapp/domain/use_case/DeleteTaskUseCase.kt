package com.maelin.taskapp.domain.use_case

import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task)
    }
}