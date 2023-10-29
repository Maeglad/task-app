package com.maelin.taskapp.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    private lateinit var taskDao: TaskDao

    @Inject
    private lateinit var db: TaskDatabase

    @Before
    fun setup() {
        rule.inject()
    }

    @After
    fun tearDown() {
        db.close()
    }


}