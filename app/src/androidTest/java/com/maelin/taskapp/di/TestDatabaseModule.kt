package com.maelin.taskapp.di

import android.content.Context
import androidx.room.Room
import com.maelin.taskapp.data.data_source.TaskDao
import com.maelin.taskapp.data.data_source.TaskDatabase
import com.maelin.taskapp.data.repository.TaskRepositoryImpl
import com.maelin.taskapp.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class TestDatabaseModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.inMemoryDatabaseBuilder(context = context,
            TaskDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDatabase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(taskDatabase)
    }
}