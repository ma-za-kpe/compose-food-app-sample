package com.maku.composefoodorderapp.ui.screens.menu

import com.maku.composefoodorderapp.core.cache.model.Menu
import com.maku.composefoodorderapp.ui.screens.cart.state.Event

// this is the class that stores the current state of your View.
data class MenuItemsViewState(
    val loading: Boolean = false,
    val items: List<Menu> = emptyList(),
    // Using Event prevents your app from handling the error more than once.
    val failure: Event<Throwable>? = null
)

class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
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
