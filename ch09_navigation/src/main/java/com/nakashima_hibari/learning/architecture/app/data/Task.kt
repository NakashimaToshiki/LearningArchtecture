package com.nakashima_hibari.learning.architecture.app.data

import androidx.lifecycle.ViewModel
import java.util.*
/**
 * タスクデータ。MVVMパターンのModelに該当するクラス
 *
 * @param title タスクのタイトル
 * @param description タスクの詳細説明
 * @param isCompleted タスクが完了しているか
 * @param id ユニークUI
 * */
data class Task (
    var title : String = "",
    var description : String = "",
    var isCompleted: Boolean = false,
    var id: String = UUID.randomUUID().toString() // UUID.randomUUIDで重複しない文字列を生成している
): ViewModel(){

    /** タイトル */
    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    val isActive
        get() = !isCompleted

    val isEmpty
        get() = title.isEmpty() || description.isEmpty()
}