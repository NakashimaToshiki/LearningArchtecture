package com.nakashima_hibari.learning.architecture.ch3_repository.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nakashima_hibari.learning.architecture.ch3_repository.data.Task
import com.nakashima_hibari.learning.architecture.ch3_repository.databinding.TaskItemBinding
import com.nakashima_hibari.learning.architecture.ch3_repository.tasks.TasksViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.util.getViewModelFactory

class TaskItemFragment : Fragment(){

    companion object {
        fun newInstance() =
            TaskItemFragment()
    }

    private val model : Task = Task()

    private val viewModel: TasksViewModel by viewModels<TasksViewModel>{getViewModelFactory() }

    private lateinit var viewDataBinding: TaskItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = TaskItemBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            task = model
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }
}