package com.maelin.taskapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = Task.TABLE_NAME)
//@TypeConverters(StatusTypeConverter::class)
data class Task constructor(
    @PrimaryKey(autoGenerate = true)
        val id: Long,
        val taskName: String,
        val status: TaskStatus,
        val timeCreated: Long,
        val timeDue: Long
    ) {
    companion object {
        const val TABLE_NAME = "task"
    }
}