package com.nakashima_hibari.learning.architecture.ch1_mvvm

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.nakashima_hibari.learning.architecture.ch1_mvvm.databinding.FragmentTaskEditBinding

class TaskEditFragment : Fragment() {

    companion object {
        fun newInstance() = TaskEditFragment()
    }

    private lateinit var viewModel: TaskViewModel

    private lateinit var viewDataBinding: FragmentTaskEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(TaskViewModel::class.java)

        viewDataBinding = FragmentTaskEditBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        return viewDataBinding.root
    }
}
