package com.maelin.taskapp.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maelin.taskapp.domain.model.Subtask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {
    @Query("select * from ${Subtask.TABLE_NAME}")
    fun getAllSubtasks(): Flow<List<Subtask>>

    @Insert
    suspend fun insertSubtask(subtask: Subtask)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubtasks(subtasks: List<Subtask>)

    @Query("delete from ${Subtask.TABLE_NAME} where ${Subtask.TASK_ID_NAME} = :taskId and id not in (:updatedSubtaskIds)")
    suspend fun deleteOldSubtasks(taskId: Long, updatedSubtaskIds: List<Long>?)
}