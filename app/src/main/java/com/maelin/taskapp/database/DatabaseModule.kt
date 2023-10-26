package com.maelin.taskapp.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao
    }
    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(context = context,
                            TaskDatabase::class.java,
                            "TaskDatabase"
                    ).build()
    }
}