package com.maelin.taskapp.domain

import com.maelin.taskapp.database.TaskStatus


data class Task(
    val id: Long,
    var taskName: String,
    var taskStatus: TaskStatus,
    var subtasks: ArrayList<Subtask>?
)


/**
 * Prototype for future, we want to add
 */
data class Subtask (
    val id: Long,
    var description: String,
    var order: Long
)