package com.example.presentation.shop.single.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.example.domain.core.result.LoadResult
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.presentation.core.UnidirectionalViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ShopSingleViewModel :
    UnidirectionalViewModel<ShopSingleViewModel.Event, ShopSingleViewModel.Effect, ShopSingleViewModel.State> {
    data class State(
        val shopLoadResult: LoadResult<Shop> = LoadResult.Loading,
    )

    sealed class Effect {
        object Init : Effect()
    }

    sealed class Event {
        data class FetchShop(val shopId: ShopId) : Event()
        data class RetryFetchShop(val shopId: ShopId) : Event()
    }

    override val state: StateFlow<State>
    override val effectFlow: Flow<Effect>
    override fun event(event: Event)
}

private val LocalShopSingleViewModelFactory =
    compositionLocalOf<@Composable () -> ShopSingleViewModel> {
        {
            error("not LocalShopSingleViewModelFactory provided")
        }
    }

fun provideShopSingleViewModelFactory(viewModelFactory: @Composable () -> ShopSingleViewModel) =
    LocalShopSingleViewModelFactory provides viewModelFactory

@Composable
fun shopSingleViewModel() = LocalShopSingleViewModelFactory.current()
