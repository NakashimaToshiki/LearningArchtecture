package com.nakashima_hibari.learning.architecture.app.addedittask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nakashima_hibari.learning.architecture.app.R
import com.nakashima_hibari.learning.architecture.app.databinding.AddtaskFragBinding
import com.nakashima_hibari.learning.architecture.app.util.getViewModelFactory

class AddEditTaskFragment : Fragment() {

    private val viewModel by viewModels<AddEditTaskViewModel> { getViewModelFactory()}

    private lateinit var viewDataBinding : AddtaskFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = AddtaskFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.taskdetail_fragment_menu, menu)
    }
}