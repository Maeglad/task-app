package com.maelin.taskapp.data.repository

import androidx.room.Transaction
import com.maelin.taskapp.data.data_source.TaskDatabase
import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    taskDatabase: TaskDatabase
) : TaskRepository {

    private val taskDao = taskDatabase.taskDao
    private val subtaskDao = taskDatabase.subtaskDao

    @Transaction
    override fun getTasksWithSubtasks(): Flow<List<TaskWithSubtasks>> {
        return taskDao.getTasksWithSubtasks()
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    /**
     * Insert task with subtasks into database
     *
     * If task exists it is overwritten
     *
     * If subtask exists it is overwritten
     *
     * If there were any subtasks that were linked to original task
     * but were not part of this transaction
     * they will be deleted
     */
    @Transaction
    override suspend fun insertTaskWithSubtasks(taskWithSubtasks: TaskWithSubtasks) {
        // create or update task
        val taskId = taskDao.insertTask(taskWithSubtasks.task)
        // create or update subtasks
        taskWithSubtasks.subtasks?.map { it ->
            // set taskId to subtask
            it.taskId = taskId
            return@map it
        }?.run {
            // insert or update
            subtaskDao.insertSubtasks(this)
        }
        // delete subtasks that were left of from before
        val updatedSubtaskIds = taskWithSubtasks.subtasks?.map { it.id }
        subtaskDao.deleteOldSubtasks(taskWithSubtasks.task.id, updatedSubtaskIds)
    }
}