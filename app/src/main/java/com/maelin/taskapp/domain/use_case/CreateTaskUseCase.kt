package com.maelin.taskapp.domain.use_case

import com.maelin.taskapp.domain.model.Subtask
import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.repository.TaskRepository
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task, subtasks: List<Subtask>?) {
        // todo validation once we know what are inputs
        val newTask = TaskWithSubtasks(
            task = task,
            subtasks = subtasks
        )
        taskRepository.insertTaskWithSubtasks(newTask)
    }
}