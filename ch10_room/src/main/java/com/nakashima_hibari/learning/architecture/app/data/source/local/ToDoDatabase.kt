package com.nakashima_hibari.learning.architecture.app.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nakashima_hibari.learning.architecture.app.data.Task


/**
 * タスクテーブルを含む会議室データベース。
 *
 * exportSchemaは、本番データベースではtrueである必要があることに注意してください。
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao
}
