package com.maku.composefoodorderapp.ui.screens.cart.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maku.composefoodorderapp.core.cache.model.Cart
import com.maku.composefoodorderapp.core.cache.model.Menu
import com.maku.composefoodorderapp.ui.screens.cart.state.CartItemsViewState
import com.maku.composefoodorderapp.ui.screens.cart.usecases.GetAllCartItems
import com.maku.composefoodorderapp.ui.screens.cart.state.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getAllCartItems: GetAllCartItems,
) : ViewModel() {
    val state: LiveData<CartItemsViewState> get() = _state
    private val _state = MutableLiveData<CartItemsViewState>()

    init {
        _state.value = CartItemsViewState()
        subscribeToCartUpdates()
    }

    private fun subscribeToCartUpdates() {
        viewModelScope.launch {
            getAllCartItems().collect { response ->
                Log.d("TAG", "subscribeToCartUpdates: ${response.size}")
                onNewCartItems(response)
            }
        }
    }

//    private fun onFailure(failure: Throwable) {
//        _state.value = state.value!!.copy(
//            loading = false,
//            failure = Event(failure)
//        )
//    }

    private fun onNewCartItems(list: List<Cart>) {
        _state.value = state.value!!.copy(loading = true)
        _state.value = state.value!!.copy(loading = false, items = list)
    }

}

