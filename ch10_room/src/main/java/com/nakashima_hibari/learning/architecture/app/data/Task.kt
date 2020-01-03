package com.nakashima_hibari.learning.architecture.app.data

import androidx.databinding.adapters.Converters
import androidx.lifecycle.ViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*
/**
 * タスクデータ。MVVMパターンのModelに該当するクラス
 *
 * @param title タスクのタイトル
 * @param description タスクの詳細説明
 * @param isCompleted タスクが完了しているか
 * @param id ユニークUI
 * */
@Entity(tableName = "tasks")
@TypeConverters(Converters::class)
data class Task (
    @ColumnInfo(name = "title") var title : String = "",
    @ColumnInfo(name = "description") var description : String = "",
    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString() // UUID.randomUUIDで重複しない文字列を生成している
): ViewModel(){

    /** タイトル */
    val titleForList: String
        get() = if (title.isNotEmpty()) title else description
}