package com.maelin.taskapp.presentation.add_new_task

data class SubtaskState(
    val text: String = "",
    val hint : String = "Type your task...",
    val order: Int
)
