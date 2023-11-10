package com.maelin.taskapp.presentation.add_new_task

sealed class AddTaskEvent {
    data class TitleChanged(val value: String): AddTaskEvent()
    data class SubtaskChanged(val value: String, val order: Int): AddTaskEvent()

    object SaveTaskEvent: AddTaskEvent()
}
