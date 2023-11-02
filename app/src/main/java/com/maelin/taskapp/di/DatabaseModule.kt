package com.maelin.taskapp.di

import android.content.Context
import androidx.room.Room
import com.maelin.taskapp.data.data_source.SubtaskDao
import com.maelin.taskapp.data.data_source.TaskDao
import com.maelin.taskapp.data.data_source.TaskDatabase
import com.maelin.taskapp.data.repository.TaskRepositoryImpl
import com.maelin.taskapp.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao
    }

    @Provides
    @Singleton
    fun provideSubtaskDao(taskDatabase: TaskDatabase): SubtaskDao {
        return taskDatabase.subtaskDao
    }

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(context = context,
                            TaskDatabase::class.java,
                            "TaskDatabase"
                    ).build()
    }
    @Provides
    @Singleton
    fun provideRepository(taskDatabase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(taskDatabase)
    }
}