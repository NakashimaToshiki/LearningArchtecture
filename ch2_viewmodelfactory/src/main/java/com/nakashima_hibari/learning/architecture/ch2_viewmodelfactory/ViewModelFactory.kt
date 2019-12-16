package com.nakashima_hibari.learning.architecture.ch2_viewmodelfactory

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


class ViewModelFactory : ViewModelProvider.NewInstanceFactory(){

    companion object {
        var taskViewModel = TaskViewModel()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(TaskViewModel::class.java) ->
                    taskViewModel
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}

fun Fragment.getViewModelFactory(): ViewModelFactory{
    return ViewModelFactory()
}