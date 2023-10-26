package com.maelin.taskapp.database

import com.maelin.taskapp.domain.Task
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TaskRepository @Inject constructor (private val database: TaskDatabase) {

    //val tasks: StateFlow<List<Task>> = database.taskDao
}