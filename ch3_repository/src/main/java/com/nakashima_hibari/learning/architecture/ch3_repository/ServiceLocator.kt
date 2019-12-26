package com.nakashima_hibari.learning.architecture.ch3_repository

import com.nakashima_hibari.learning.architecture.ch3_repository.data.source.TasksRepository
import com.nakashima_hibari.learning.architecture.ch3_repository.taskdetail.TaskDetailViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.tasks.TasksViewModel

object ServiceLocator{

    private var tasksRepository: TasksRepository? = null

    private var taskDetailViewModel : TaskDetailViewModel? = null

    private var tasksViewModel : TasksViewModel? = null

    fun provideTasksRepository() : TasksRepository {
        if(tasksRepository == null)
            tasksRepository = TasksRepository()
        return tasksRepository!!
    }

    fun provideDetailViewModel() : TaskDetailViewModel{
        if(taskDetailViewModel == null)
            taskDetailViewModel = TaskDetailViewModel(provideTasksRepository())
        return taskDetailViewModel!!
    }

    fun provideTasksViewModel() : TasksViewModel{
        if(tasksViewModel == null)
            tasksViewModel = TasksViewModel(provideTasksRepository())
        return tasksViewModel!!
    }

}