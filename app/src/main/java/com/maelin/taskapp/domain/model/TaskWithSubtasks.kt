package com.maelin.taskapp.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSubtasks(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    )
    val subtasks: List<Subtask>?
)