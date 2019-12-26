package com.nakashima_hibari.learning.architecture.ch3_repository.util

import androidx.fragment.app.Fragment
import com.nakashima_hibari.learning.architecture.ch3_repository.ServiceLocator
import com.nakashima_hibari.learning.architecture.ch3_repository.ViewModelFactory


fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory()
}