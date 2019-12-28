package com.nakashima_hibari.learning.architecture.ch3_repository

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nakashima_hibari.learning.architecture.ch3_repository.data.source.TasksRepository
import com.nakashima_hibari.learning.architecture.ch3_repository.taskdetail.TaskDetailViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.tasks.TasksViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory() : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(TaskDetailViewModel::class.java) ->
                    ServiceLocator.provideDetailViewModel()
                isAssignableFrom(TasksViewModel::class.java) ->
                    ServiceLocator.provideTasksViewModel()
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")

            }
        } as T
}