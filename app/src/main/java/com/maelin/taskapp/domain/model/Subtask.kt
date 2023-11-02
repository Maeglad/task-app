package com.maelin.taskapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = Subtask.TABLE_NAME,
    foreignKeys = [ForeignKey(entity = Task::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Subtask (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(index = true, name = TASK_ID_NAME)
    var taskId: Long,
    val description: String,
    val order: Long
) {
    companion object {
        const val TABLE_NAME = "subtask"
        const val TASK_ID_NAME = "taskId"
    }
}