package com.nakashima_hibari.learning.architecture.app.data.source

import com.nakashima_hibari.learning.architecture.app.data.Task
import kotlinx.coroutines.newFixedThreadPoolContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class DefualtTasksRepository{

    private var cachedTasks: ConcurrentMap<String, Task>? = null

    init {
        cachedTasks = ConcurrentHashMap()

        var list = listOf(
            Task("title01", "description01"),
            Task("title02", "description02", true),
            Task("title03", "description03"),
            Task("title04", "description04", true),
            Task("title05", "description05"),
            Task("", "description06", true),
            Task("", "description07"),
            Task("", "description08", true),
            Task("", "description09"),
            Task("", "description10"),
            Task("title11", "description11"),
            Task("title12", "description12", true),
            Task("title13", "description13"),
            Task("title14", "description14", true),
            Task("title15", "description15"),
            Task("", "description16", true),
            Task("", "description17"),
            Task("", "description18", true),
            Task("", "description19"),
            Task("", "description20")
        )
        list.forEach{
            cachedTasks?.put(it.id, it)
        }
    }

    fun getTasks() : List<Task>{
        return cachedTasks?.values?.sortedBy { it.id }!!
    }

    fun clearCompletedTasks() {
        cachedTasks?.entries?.removeAll { it.value.isCompleted }
    }

}