package com.maku.composefoodorderapp.ui.screens.menu.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu
import com.maku.composefoodorderapp.ui.screens.cart.state.Event
import com.maku.composefoodorderapp.ui.screens.cart.usecases.UpdateQtyCartItem
import com.maku.composefoodorderapp.ui.screens.cart.usecases.deleteCartItemById
import com.maku.composefoodorderapp.ui.screens.cart.usecases.insertCartItem
import com.maku.composefoodorderapp.ui.screens.menu.MenuItemsViewState
import com.maku.composefoodorderapp.ui.screens.menu.usecases.GetMenuItems
import com.maku.composefoodorderapp.ui.screens.menu.usecases.UpdateAddedCartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenuItems: GetMenuItems,
    private val insertCartItem: insertCartItem,
    private val deleteCartItemById: deleteCartItemById,
    private val updateAddedCartItem: UpdateAddedCartItem,
    private val updateQtyCartItem: UpdateQtyCartItem,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {
    val state: LiveData<MenuItemsViewState> get() = _state
    private val _state = MutableLiveData<MenuItemsViewState>()

    val size: LiveData<Int> get() = _size
    private val _size = MutableLiveData<Int>().apply {
        value = 0
    }

    init {
        _state.value = MenuItemsViewState()
        subscribeToMenuUpdates()
    }

    private fun subscribeToMenuUpdates() {
        getMenuItems()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onNewMenuItems(it) },
                { onFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    fun onMenuItemSelectionEvents(itemId: Long, isChecked: Boolean) {
        // 1. update the item added boolean state in the db to toggle the button
        viewModelScope.launch {
            updateAddedCartItem(isChecked, itemId)
        }
        if(isChecked){
            viewModelScope.launch {
                insertCartItem(cart = Cart(itemId, 1))
                _size.value =  _size.value?.plus(1)
            }
        } else {
            viewModelScope.launch {
                deleteCartItemById(itemId)
                _size.value =  _size.value?.minus(1)
            }
        }

        // 2. Observe the stream of menu items and use the added boolean to add and remove from cart respectively
//        getMenuItems()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    it.forEach { item ->
//                        if (item.added) {
//                            viewModelScope.launch {
//                                insertCartItem(cart = Cart(itemId, 1))
//                            }
//                        }
//                    }
//                },
//                { onFailure(it) }
//            )
//            .addTo(compositeDisposable)

    }

    private fun onFailure(failure: Throwable) {
        _state.value = state.value!!.copy(
            loading = false,
            failure = Event(failure)
        )
    }

    private fun onNewMenuItems(list: List<Menu>) {
        _state.value = state.value!!.copy(loading = true)
        _state.value = state.value!!.copy(loading = false, items = list)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

