package com.example.presentation.shop.single.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.result.LoadResult
import com.example.domain.core.result.flowWithLoading
import com.example.domain.shop.model.Shop
import com.example.domain.shop.model.ShopId
import com.example.domain.shop.usecase.ShopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.meta.Exhaustive
import javax.inject.Inject

@HiltViewModel
class RealShopSingleViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase,
) : ViewModel(), ShopSingleViewModel {

    private val _effectFlow = Channel<ShopSingleViewModel.Effect>(Channel.UNLIMITED)
    override val effectFlow: Flow<ShopSingleViewModel.Effect> = _effectFlow.receiveAsFlow()

    private val _shopLoadResult: MutableStateFlow<LoadResult<Shop>> =
        MutableStateFlow(LoadResult.Loading)

    override val state: StateFlow<ShopSingleViewModel.State> =
        _shopLoadResult
            .map { ShopSingleViewModel.State(shopLoadResult = it) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, ShopSingleViewModel.State())

    override fun event(event: ShopSingleViewModel.Event) {
        viewModelScope.launch {
            @Exhaustive
            when (event) {
                is ShopSingleViewModel.Event.FetchShop -> fetchShop(event.shopId)
                is ShopSingleViewModel.Event.RetryFetchShop -> fetchShop(event.shopId)
            }
        }
    }

    init {
        viewModelScope.launch {
            _effectFlow.send(ShopSingleViewModel.Effect.Init)
        }
    }

    private fun fetchShop(shopId: ShopId) {
        flowWithLoading { shopUseCase.fetchShop(shopId) }
            .onEach { _shopLoadResult.value = it }
            .launchIn(viewModelScope)
    }
}
