package com.maelin.taskapp.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase

@Dao
interface TaskDao {

}
@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
}

