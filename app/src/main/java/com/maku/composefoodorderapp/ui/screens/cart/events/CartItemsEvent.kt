package com.maku.composefoodorderapp.ui.screens.cart.events

// this is an action that the UI triggers
sealed class CartItemsEvent {
  object RequestAllCartItemsList: CartItemsEvent()
  object deleteCartItem: CartItemsEvent()
}
