package com.nakashima_hibari.learning.architecture.app.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar


/**
 * snackbarTaskMessageLiveEventに含まれる値が変更されたときにスナックバーメッセージをトリガーします。
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), timeLength) // it とは StringResoucrsのID
        }
    })
}


/**
 * 静的Java関数Snackbar.make（）をViewの拡張関数に変換します。
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
    // 非同期処理をテストするEspresso Idling Resourceに関するコードは消した
}