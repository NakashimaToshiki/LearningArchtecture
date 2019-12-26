package com.nakashima_hibari.learning.architecture.ch3_repository.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nakashima_hibari.learning.architecture.ch3_repository.data.Task
import com.nakashima_hibari.learning.architecture.ch3_repository.tasks.TasksAdapter.ViewHolder
import com.nakashima_hibari.learning.architecture.ch3_repository.databinding.TaskItemBinding

class TasksAdapter(private val viewModel: TasksViewModel) :
    ListAdapter<Task, ViewHolder>(TaskDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(viewModel: TasksViewModel, item : Task){
            binding.viewmodel = viewModel
            binding.task = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * リスト内の2つの非nullアイテム間の差分を計算するためのコールバック。
 *
 * 古いリストと新しいリストの間の変更の最小数を計算するためにListAdapterによって使用されます
 * submitList`に渡されたリスト。
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}