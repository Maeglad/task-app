package com.maelin.taskapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maelin.taskapp.domain.model.Subtask
import com.maelin.taskapp.domain.model.Task


@Database(entities = [Task::class, Subtask::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val subtaskDao: SubtaskDao
}