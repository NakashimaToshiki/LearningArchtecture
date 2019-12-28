package com.nakashima_hibari.learning.architecture.ch3_repository.taskdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.data.Task
import com.nakashima_hibari.learning.architecture.ch3_repository.data.source.TasksRepository

class TaskDetailViewModel(
    private val tasksRepository: TasksRepository
) : ViewModel() {

    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task>  = _task

}
