package com.maku.composefoodorderapp.ui.screens.menu.events

// this is an action that the UI triggers
sealed class FetchMenuItemsEvent {
  object RequestMenuItemsList: FetchMenuItemsEvent()
}
