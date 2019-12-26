package com.nakashima_hibari.learning.architecture.ch3_repository.tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.data.Task
import com.nakashima_hibari.learning.architecture.ch3_repository.data.source.TasksRepository
import com.nakashima_hibari.learning.architecture.ch3_repository.util.Event

class TasksViewModel
    (private val tasksRepository: TasksRepository
) : ViewModel() {

    init {
        //loadTasks()
    }

    private val _items = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val items: LiveData<List<Task>> = _items

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    fun openTask(taskId: String) {
        _openTaskEvent.value = Event(taskId)
    }

    fun loadTasks(){
         _items.value = listOf(
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title1", "description1"),
             Task("title2", "description2", true),
             Task("title3", "description3")
         )
    }

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun refresh() {
        loadTasks()
    }

}