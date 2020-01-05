package com.nakashima_hibari.learning.architecture.app.tasks

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.nakashima_hibari.learning.architecture.app.data.Task
import com.nakashima_hibari.learning.architecture.app.util.Event
import kotlinx.coroutines.launch
import com.nakashima_hibari.learning.architecture.app.R
import com.nakashima_hibari.learning.architecture.app.data.source.DefualtTasksRepository

/**
 * MVVMパターンのViewModelに該当するクラス
 * */
class TasksViewModel(
    private val tasksRepository : DefualtTasksRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Task>>().apply { value = emptyList() }
    val items: LiveData<List<Task>> = _items

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    /**
     * データベースからデータを取得しているという疑似的な処理
     * */
    fun loadTasks() {
        val tasks = tasksRepository.getTasks()

        val tasksToShow = ArrayList<Task>()
        for(task in tasks){
            when(_currentFiltering){
                TasksFilterType.ALL_TASKS ->tasksToShow.add(task)
                TasksFilterType.ACTIVE_TASKS -> if(task.isActive){
                    tasksToShow.add(task)
                }
                TasksFilterType.COMPLETED_TASKS -> if(task.isCompleted){
                    tasksToShow.add(task)
                }
            }
        }

        _items.value = ArrayList(tasksToShow)
    }

    /**
     * リストにデータが何もない状態であるか判定する
     * */
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    fun completeTask(task: Task, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            task.isCompleted = true
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            task.isCompleted = false
            showSnackbarMessage(R.string.task_marked_active)
        }
    }

    /**
     * スナックバーを表示
     * */
    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = Event(message)
    }

    private var _currentFiltering = TasksFilterType.ALL_TASKS

    fun clearCompletedTasks() {
        viewModelScope.launch {
            tasksRepository.clearCompletedTasks()
            showSnackbarMessage(R.string.completed_tasks_cleared)
            // Refresh list to show the new state
            loadTasks()
        }
    }

    /**
     * 現在のタスクフィルタリングタイプを設定します。
     */
    fun setFiltering(requestType: TasksFilterType) {
        _currentFiltering = requestType

        // Depending on the filter type, set the filtering label, icon drawables, etc.
        when (requestType) {
            TasksFilterType.ALL_TASKS -> {
                setFilter(
                    R.string.label_all, R.string.no_tasks_all,
                    R.drawable.logo_no_fill, true
                )
            }
            TasksFilterType.ACTIVE_TASKS -> {
                setFilter(
                    R.string.label_active, R.string.no_tasks_active,
                    R.drawable.ic_check_circle_96dp, false
                )
            }
            TasksFilterType.COMPLETED_TASKS -> {
                setFilter(
                    R.string.label_completed, R.string.no_tasks_completed,
                    R.drawable.ic_verified_user_96dp, false
                )
            }
        }
    }


    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noTasksLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int> = _noTasksLabel

    private val _noTaskIconRes = MutableLiveData<Int>()
    val noTaskIconRes: LiveData<Int> = _noTaskIconRes

    private val _tasksAddViewVisible = MutableLiveData<Boolean>()
    val tasksAddViewVisible: LiveData<Boolean> = _tasksAddViewVisible

    private fun setFilter(
        @StringRes filteringLabelString: Int, @StringRes noTasksLabelString: Int,
        @DrawableRes noTaskIconDrawable: Int, tasksAddVisible: Boolean
    ) {
        _currentFilteringLabel.value = filteringLabelString
        _noTasksLabel.value = noTasksLabelString
        _noTaskIconRes.value = noTaskIconDrawable
        _tasksAddViewVisible.value = tasksAddVisible

        // xml上でContextCompat関係でエラーがでるので、スナックバーで代用
        showSnackbarMessage(filteringLabelString)
    }

    // -- Ch09_Navigation --

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    fun openTask(taskId : String){
        _openTaskEvent.value = Event(taskId)
    }

}