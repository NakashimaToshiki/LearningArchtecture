package com.nakashima_hibari.learning.architecture.app.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.nakashima_hibari.learning.architecture.app.R
import com.nakashima_hibari.learning.architecture.app.databinding.StatisticsFragBinding
import com.nakashima_hibari.learning.architecture.app.util.getViewModelFactory

class StatisticsFragment : Fragment() {

    private val viewModel by viewModels< StatisticsViewModel >{getViewModelFactory()}

    private lateinit var viewDataBinding: StatisticsFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = StatisticsFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

}
