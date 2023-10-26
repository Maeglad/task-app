package com.maelin.taskapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TaskStatus {
    CREATED,
    COMPLETED
}

@Entity
data class TaskEntity constructor(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val taskName: String,
        val status: TaskStatus
    )

