package com.nakashima_hibari.learning.architecture.ch2_viewmodelfactory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.nakashima_hibari.learning.architecture.ch2_viewmodelfactory.databinding.FragmentTaskEditBinding


class TaskEditFragment : Fragment() {

    companion object {
        fun newInstance() = TaskEditFragment()
    }

    private val viewModel: TaskViewModel by viewModels<TaskViewModel>{getViewModelFactory() }

    private lateinit var viewDataBinding: FragmentTaskEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentTaskEditBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }
}
