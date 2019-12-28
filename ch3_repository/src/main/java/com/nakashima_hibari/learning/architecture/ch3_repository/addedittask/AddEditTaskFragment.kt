package com.nakashima_hibari.learning.architecture.ch3_repository.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nakashima_hibari.learning.architecture.ch3_repository.databinding.AddtaskFragBinding
import com.nakashima_hibari.learning.architecture.ch3_repository.taskdetail.TaskDetailViewModel
import com.nakashima_hibari.learning.architecture.ch3_repository.util.getViewModelFactory


class AddEditTaskFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditTaskFragment()
    }

    private val detailViewModel: TaskDetailViewModel by viewModels<TaskDetailViewModel>{getViewModelFactory() }

    private lateinit var viewDataBinding: AddtaskFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = AddtaskFragBinding.inflate(inflater, container, false).apply {
            viewmodel = detailViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }
}
