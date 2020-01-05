package com.nakashima_hibari.learning.architecture.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nakashima_hibari.learning.architecture.app.addedittask.AddEditTaskViewModel
import com.nakashima_hibari.learning.architecture.app.data.source.DefualtTasksRepository
import com.nakashima_hibari.learning.architecture.app.statistics.StatisticsViewModel
import com.nakashima_hibari.learning.architecture.app.taskdetail.TaskDetailViewModel
import com.nakashima_hibari.learning.architecture.app.tasks.TasksViewModel

@Suppress("UNCHECKED_CAST") // 警告を抑制する
class ViewModelFactory constructor(
    private val tasksRepository: DefualtTasksRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = // ここ「=」を使ってアローメソッドにしている
        with(modelClass){
            when {
                isAssignableFrom(StatisticsViewModel::class.java) ->
                    StatisticsViewModel(tasksRepository)
                isAssignableFrom(AddEditTaskViewModel::class.java) ->
                    AddEditTaskViewModel(tasksRepository)
                isAssignableFrom(TasksViewModel::class.java) ->
                    TasksViewModel(tasksRepository)
                isAssignableFrom(TaskDetailViewModel::class.java) ->
                    TaskDetailViewModel(tasksRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}