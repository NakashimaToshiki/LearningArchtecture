package com.nakashima_hibari.learning.architecture.ch2_viewmodelfactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    private val _title = MutableLiveData<String>()
    val title  = _title

    private val _description = MutableLiveData<String>()
    val description = _description
}
