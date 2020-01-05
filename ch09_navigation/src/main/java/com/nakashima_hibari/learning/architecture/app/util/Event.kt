package com.nakashima_hibari.learning.architecture.app.util

import androidx.lifecycle.Observer

/**
 * イベントを表すLiveDataを介して公開されるデータのラッパーとして使用されます。
 */
open class Event<out T>(private val content: T) {

    @Suppress("MemberVisibilityCanBePrivate") // 警告を無視する
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * コンテンツを返し、再び使用できないようにします。
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}


// -- Ch_Navigation --

/**
 * [イベント]の[オブザーバー]。[イベント]のコンテンツがすでに処理されています。
 *
 * [onEventUnhandledContent]は、[Event]のコンテンツが処理されていない場合にのみ呼び出されます。
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}