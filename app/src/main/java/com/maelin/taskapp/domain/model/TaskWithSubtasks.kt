package com.maelin.taskapp.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSubtasks(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = Task.ID_NAME,
        entityColumn = Subtask.TASK_ID_NAME
    )
    val subtasks: List<Subtask>?
)