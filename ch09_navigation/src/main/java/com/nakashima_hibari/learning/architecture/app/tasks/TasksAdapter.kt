package com.nakashima_hibari.learning.architecture.app.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nakashima_hibari.learning.architecture.app.data.Task
import com.nakashima_hibari.learning.architecture.app.databinding.TaskItemBinding


class TasksAdapter(private val viewModel: TasksViewModel) :
    ListAdapter<Task, TasksAdapter.ViewHolder>(TaskDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    /**
     * findViewByIdメソッドによるUI呼び出しは低速なので、
     * このViewHolderパターンを使って参照をキャッシュする
     * */
    class ViewHolder private constructor(val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(viewModel: TasksViewModel, item : Task){
            binding.viewmodel = viewModel
            binding.task = item
            binding.executePendingBindings() // UIスレッドに即座に反映
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
 * submitListメソッドに渡される。
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Task>(){

    /**
     ２つのアイテムのIDが同じであるか判定
     */
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    /**
    ２つのアイテムのデータ内容が同じであるか判定
     */
    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}