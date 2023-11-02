package com.maelin.taskapp.domain.repository

import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasksWithSubtasks(): Flow<List<TaskWithSubtasks>>

    suspend fun deleteTask(task: Task)
    suspend fun insertTaskWithSubtasks(taskWithSubtasks: TaskWithSubtasks)

}