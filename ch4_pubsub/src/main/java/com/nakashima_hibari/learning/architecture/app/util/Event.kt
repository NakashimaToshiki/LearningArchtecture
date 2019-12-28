package com.nakashima_hibari.learning.architecture.app.util

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
