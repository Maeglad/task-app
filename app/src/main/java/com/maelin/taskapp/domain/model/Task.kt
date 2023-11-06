package com.maelin.taskapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = Task.TABLE_NAME)
//@TypeConverters(StatusTypeConverter::class)
data class Task constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID_NAME)
        val id: Long,
        val taskName: String,
        @ColumnInfo(name = STATUS_NAME)
        val status: TaskStatus,
        val timeCreated: Long,
        val timeDue: Long
    ) {
    companion object {
        const val TABLE_NAME = "task"
        const val ID_NAME = "id"
        const val STATUS_NAME = "status"
    }
}