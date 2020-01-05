package com.nakashima_hibari.learning.architecture.app.taskdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakashima_hibari.learning.architecture.app.data.Task
import com.nakashima_hibari.learning.architecture.app.data.source.DefualtTasksRepository

class TaskDetailViewModel(
    private val tasksRepository: DefualtTasksRepository
) : ViewModel(){

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> = _task

    fun start(taskId : String?){
        _task.value = tasksRepository.getTask(taskId!!)
    }
}