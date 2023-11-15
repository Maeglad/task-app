package com.maelin.taskapp.presentation.add_new_task

import androidx.lifecycle.ViewModel
import com.maelin.taskapp.domain.model.Subtask
import com.maelin.taskapp.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val subtaskStates = listOf<SubtaskState>(
        SubtaskState(
            order = 1
        ))

//    private val _state = MutableStateFlow(AddNewTaskState())
//    val state: StateFlow<AddNewTaskState> = _state.asStateFlow()

    
}