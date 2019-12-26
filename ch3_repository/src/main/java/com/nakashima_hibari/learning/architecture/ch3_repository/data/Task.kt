package com.nakashima_hibari.learning.architecture.ch3_repository.data

import androidx.lifecycle.ViewModel
import java.util.*

data class Task (
    var title : String = "",
    var description : String = "",
    var isCompleted: Boolean = false,
    var id: String = UUID.randomUUID().toString()
): ViewModel(){
    val titleForList: String
        get() = if (title.isNotEmpty()) title else description

    val isActive
        get() = !isCompleted

    val isEmpty
        get() = title.isEmpty() || description.isEmpty()
}