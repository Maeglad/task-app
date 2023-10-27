package com.maelin.taskapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.Update
import com.maelin.taskapp.domain.Subtask

@Dao
interface TaskDao {

    @Update
    suspend fun updateTask(task: TaskEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskWithSubtasks(taskWithSubtasks: TaskWithSubtasks)
    @Transaction
    suspend fun updateTaskWithSubtasks(taskWithSubtasks: TaskWithSubtasks) {
        insertTaskWithSubtasks(taskWithSubtasks)

        val updatedSubtaskIds = taskWithSubtasks.subtasks.map { it.id }
        deleteOldSubtasks(taskWithSubtasks.task.id, updatedSubtaskIds)
    }
    @Query("delete from subtask where taskId = :taskId and id not in (:updatedSubtaskIds)")
    suspend fun deleteOldSubtasks(taskId: Long, updatedSubtaskIds: List<Long>)
}

interface SubtaskDao {
    @Insert
    suspend fun insertSubtask(subtask: Subtask)
    @Update
    suspend fun updateSubtask()
}

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val subtaskDao: SubtaskDao
}