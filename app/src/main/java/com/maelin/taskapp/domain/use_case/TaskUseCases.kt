package com.maelin.taskapp.domain.use_case

data class TaskUseCases(
    val getTasks: GetTasksUseCase,
    val deleteTask: DeleteTaskUseCase,
    val createTask: CreateTaskUseCase,
    val updateTaskStatus: UpdateTaskStatusUseCase
)
