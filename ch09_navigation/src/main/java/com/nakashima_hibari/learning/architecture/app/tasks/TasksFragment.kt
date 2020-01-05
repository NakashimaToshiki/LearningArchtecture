package com.nakashima_hibari.learning.architecture.app.tasks

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nakashima_hibari.learning.architecture.app.R
import com.nakashima_hibari.learning.architecture.app.databinding.TasksFragBinding
import com.nakashima_hibari.learning.architecture.app.util.EventObserver
import com.nakashima_hibari.learning.architecture.app.util.getViewModelFactory
import com.nakashima_hibari.learning.architecture.app.util.setupSnackbar
import java.util.*

class TasksFragment : Fragment(){

    private val viewModel by viewModels<TasksViewModel> { getViewModelFactory()}

    private lateinit var viewDataBinding: TasksFragBinding

    private lateinit var listAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = TasksFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    /**
     * オプションメニューの生成
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu)
    }

    /**
     * オプションメニューのイベント
     */
    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_clear -> {
                viewModel.clearCompletedTasks()
                true
            }
            R.id.menu_filter -> {
                showFilteringPopUpMenu()
                true
            }
            R.id.menu_refresh -> {
                viewModel.loadTasks()
                true
            }
            else -> false
        }


    private fun showFilteringPopUpMenu() {
        val view = activity?.findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(requireContext(), view).run {
            menuInflater.inflate(R.menu.filter_tasks, menu)

            setOnMenuItemClickListener {
                viewModel.setFiltering(
                    when (it.itemId) {
                        R.id.active -> TasksFilterType.ACTIVE_TASKS
                        R.id.completed -> TasksFilterType.COMPLETED_TASKS
                        else -> TasksFilterType.ALL_TASKS
                    }
                )
                viewModel.loadTasks()
                true
            }
            show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupSnackbar()
        setupListAdapter()
        setupNavigation()
        viewModel.loadTasks()
    }

    private fun setupListAdapter(){
        val viewModel = viewDataBinding.viewmodel
        if(viewModel != null){
            listAdapter = TasksAdapter(viewModel)
            viewDataBinding.tasksList.adapter = listAdapter
        }
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    // -- Ch_Navigation //

    private fun setupNavigation(){
        viewModel.openTaskEvent.observe(this, EventObserver {
            openTaskDetails(it)
        })
    }

    private fun openTaskDetails(taskId: String) {
        val action = TasksFragmentDirections.actionTasksFragmentDestToTaskDetailFragmentDest(taskId)
        findNavController().navigate(action)
    }
}

