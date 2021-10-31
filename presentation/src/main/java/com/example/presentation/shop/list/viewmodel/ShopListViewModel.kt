package com.example.presentation.shop.list.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.paging.PagingData
import com.example.domain.shop.model.Shop
import com.example.presentation.core.UnidirectionalViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

interface ShopListViewModel :
    UnidirectionalViewModel<ShopListViewModel.Event, ShopListViewModel.Effect, ShopListViewModel.State> {
    data class State(
        val pagindDataFlow: Flow<PagingData<Shop>> = flow { PagingData.empty<Shop>() },
    )

    sealed class Effect

    sealed class Event

    override val state: StateFlow<State>
    override val effectFlow: Flow<Effect>
    override fun event(event: Event)
}

private val LocalShopListViewModelFactory =
    compositionLocalOf<@Composable () -> ShopListViewModel> {
        {
            error("not LocalShopListViewModelFactory provided")
        }
    }

fun provideShopListViewModelFactory(viewModelFactory: @Composable () -> ShopListViewModel) =
    LocalShopListViewModelFactory provides viewModelFactory

@Composable
fun shopListViewModel() = LocalShopListViewModelFactory.current()
