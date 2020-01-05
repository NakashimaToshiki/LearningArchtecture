package com.nakashima_hibari.learning.architecture.app.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nakashima_hibari.learning.architecture.app.R
import com.nakashima_hibari.learning.architecture.app.databinding.TaskdetailFragBinding
import com.nakashima_hibari.learning.architecture.app.util.getViewModelFactory

class TaskDetailFragment : Fragment() {

    private val viewModel by viewModels<TaskDetailViewModel>{getViewModelFactory()}

    private lateinit var viewDataBinding : TaskdetailFragBinding

    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = TaskdetailFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.taskId)

        return viewDataBinding.root
    }

}