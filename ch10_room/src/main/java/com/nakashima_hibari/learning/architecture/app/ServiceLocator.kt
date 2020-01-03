package com.nakashima_hibari.learning.architecture.app

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.nakashima_hibari.learning.architecture.app.data.FakeTasksRemoteDataSource
import com.nakashima_hibari.learning.architecture.app.data.source.DefaultTasksRepository
import com.nakashima_hibari.learning.architecture.app.data.source.TasksDataSource
import com.nakashima_hibari.learning.architecture.app.data.source.TasksRepository
import com.nakashima_hibari.learning.architecture.app.data.source.local.TasksLocalDataSource
import com.nakashima_hibari.learning.architecture.app.data.source.local.ToDoDatabase
import kotlinx.coroutines.runBlocking

object ServiceLocator {

    private val lock = Any()
    private var database: ToDoDatabase? = null
    @Volatile
    var tasksRepository: TasksRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): TasksRepository {
        synchronized(this) {
            return tasksRepository ?: tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): TasksRepository {
        return DefaultTasksRepository(FakeTasksRemoteDataSource, createTaskLocalDataSource(context))
    }

    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val database = database ?: createDataBase(context)
        return TasksLocalDataSource(database.taskDao())
    }

    private fun createDataBase(context: Context): ToDoDatabase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            ToDoDatabase::class.java, "Tasks.db"
        ).build()
        database = result
        return result
    }

    @VisibleForTesting
    fun resetRepository() {
        synchronized(lock) {
            runBlocking {
                FakeTasksRemoteDataSource.deleteAllTasks()
            }
            // Clear all data to avoid test pollution.
            database?.apply {
                clearAllTables()
                close()
            }
            database = null
            tasksRepository = null
        }
    }
}
