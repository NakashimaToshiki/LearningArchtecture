package com.nakashima_hibari.learning.architecture.app.util

import androidx.fragment.app.Fragment
import com.nakashima_hibari.learning.architecture.app.ViewModelFactory
import com.nakashima_hibari.learning.architecture.app.data.source.DefualtTasksRepository

/*
 コードの簡素化のためグローバルで宣言
 */
object FragmentExt {
    var repository = DefualtTasksRepository()
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(FragmentExt.repository)
}