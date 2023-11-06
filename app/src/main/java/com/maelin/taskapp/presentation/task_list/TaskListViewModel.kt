package com.maelin.taskapp.presentation.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.use_case.TaskUseCases
import com.maelin.taskapp.domain.util.OrderType
import com.maelin.taskapp.domain.util.TaskOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class TaskListViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(TaskListState())
    val state: StateFlow<TaskListState> = _state.asStateFlow()

    private var taskJob: Job? = null

    init {
        getTasks(
            taskOrder = TaskOrder.TimeCreated(OrderType.Descending),
            includeStatuses = mapOf(
                Pair(TaskStatus.CREATED, true),
                Pair(TaskStatus.COMPLETED, true)
            )
        )
    }

    private var lastDeletedTask: TaskWithSubtasks? = null
    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.DeleteTaskEvent -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.taskWithSubtasks.task)
                    lastDeletedTask = event.taskWithSubtasks
                }
            }
            is TaskEvent.OrderEvent -> {
                // ordering is same
                if (state.value.taskOrder::class == event.taskOrder::class &&
                    // order type is same
                        state.value.taskOrder.orderType == event.taskOrder.orderType
                ) {
                    return
                }
                getTasks(event.taskOrder, state.value.includeStatuses)
            }
            is TaskEvent.RestoreTaskEvent -> {
                viewModelScope.launch {
                    lastDeletedTask?.run {
                        taskUseCases.createTask(this.task, this.subtasks)
                    }
                }
            }
            is TaskEvent.ToggleOrderSectionEvent -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is TaskEvent.FilterEvent -> {
                if (state.value.includeStatuses[event.taskStatus] == event.enabled) {
                    return
                }
                val newIncludeStatuses = HashMap(state.value.includeStatuses)
                newIncludeStatuses[event.taskStatus] = event.enabled
                getTasks(
                    taskOrder = state.value.taskOrder,
                    includeStatuses = newIncludeStatuses
                )
            }
            is TaskEvent.UpdateTaskStatusTaskEvent -> {
                viewModelScope.launch {
                    taskUseCases.updateTaskStatus(
                        task = event.task,
                        newStatus = event.taskStatus
                    )
                }
            }
        }
    }

    fun getTasks(taskOrder: TaskOrder, includeStatuses: Map<TaskStatus, Boolean>) {
        taskJob?.cancel()
        taskJob = taskUseCases.getTasks(taskOrder, includeStatuses)
            .onEach { tasks ->
                _state.update { currentState ->
                    currentState.copy(
                        tasks = tasks,
                        taskOrder = taskOrder,
                        includeStatuses = includeStatuses
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}