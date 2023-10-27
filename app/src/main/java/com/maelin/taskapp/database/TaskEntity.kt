package com.maelin.taskapp.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

enum class TaskStatus {
    CREATED,
    COMPLETED
}

@Entity(tableName = "task")
data class TaskEntity constructor(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val taskName: String,
        val status: TaskStatus
    )

data class TaskWithSubtasks(
    @Embedded
    val task: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val subtasks: List<SubtaskEntity>
)