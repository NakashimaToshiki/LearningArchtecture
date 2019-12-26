package com.nakashima_hibari.learning.architecture.app.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nakashima_hibari.learning.architecture.app.databinding.TasksFragBinding

class TasksFragment : Fragment(){
    // 簡略化のため、ここでViewModelをインスタンスしている
    private val viewModel = TasksViewModel() // by viewModels<TasksViewModel> { getViewModelFactory()}

    private lateinit var viewDataBinding: TasksFragBinding

    private lateinit var listAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = TasksFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()

        viewModel.loadTasks()
    }

    private fun setupListAdapter(){
        val viewModel = viewDataBinding.viewmodel
        if(viewModel != null){
            listAdapter = TasksAdapter(viewModel)
            viewDataBinding.tasksList.adapter = listAdapter
        }
    }
}