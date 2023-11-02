package com.maelin.taskapp.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.maelin.taskapp.data.data_source.TaskDatabase
import com.maelin.taskapp.domain.model.Subtask
import com.maelin.taskapp.domain.model.Task
import com.maelin.taskapp.domain.model.TaskStatus
import com.maelin.taskapp.domain.model.TaskWithSubtasks
import com.maelin.taskapp.domain.repository.TaskRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var db: TaskDatabase

    @Inject
    lateinit var taskRepository: TaskRepository

    @Before
    fun setup() {
        rule.inject()
    }

    @After
    fun tearDown() {
        db.close()
    }
    @Test
    fun testCascadeDeleteOnSubtask() = runBlocking {
        // create object we will be testing with
        // one task containing one subtask
        val taskWithOneSubtaskEntity = TaskWithSubtasks(
            Task(id = 1L,
                taskName = "Task with one subtask",
                status = TaskStatus.CREATED,
                System.currentTimeMillis()
            ),
            subtasks = listOf(
                Subtask(
                    id = 1L,
                    taskId = 1L,
                    description = "Single subtask",
                    order = 1L
                )
            )
        )

        val taskDao = db.taskDao
        val subtaskDao = db.subtaskDao



        // at start database should be empty
        assert(subtaskDao.getAllSubtasks().first().isEmpty())

        // persist task with one subtask in database
        taskRepository.insertTaskWithSubtasks(taskWithOneSubtaskEntity)

        // database should have one task and one subtask
        assert(taskDao.getAllTasks().first().size == 1)
        assert(subtaskDao.getAllSubtasks().first().size == 1)

        // delete task
        taskDao.deleteTask(taskWithOneSubtaskEntity.task)

        // database should be empty
        assert(taskDao.getAllTasks().first().isEmpty())
        assert(subtaskDao.getAllSubtasks().first().isEmpty())
    }

}