package com.maelin.taskapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("select * from ${Task.TABLE_NAME}")
    fun getAllTasks(): Flow<List<Task>>

    @Transaction
    @Query("select * from ${Task.TABLE_NAME}")
    fun getTasksWithSubtasks(): Flow<List<TaskWithSubtasks>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("update ${Task.TABLE_NAME} " +
            "set ${Task.STATUS_NAME} = :newStatus " +
            "where  ${Task.ID_NAME} = :taskId")
    suspend fun updateTaskStatus(taskId: Long, newStatus: TaskStatus)
}