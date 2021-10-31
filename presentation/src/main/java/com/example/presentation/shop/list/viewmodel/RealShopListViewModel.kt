package com.example.presentation.shop.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.shop.model.Location
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.usecase.ShopUseCase
import com.example.presentation.shop.list.ShopSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.annotation.meta.Exhaustive
import javax.inject.Inject

@HiltViewModel
class RealShopListViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase,
) : ViewModel(), ShopListViewModel {

    private val _effect = Channel<ShopListViewModel.Effect>(Channel.UNLIMITED)
    override val effectFlow: Flow<ShopListViewModel.Effect> = _effect.receiveAsFlow()

    override val state: StateFlow<ShopListViewModel.State> =
        MutableStateFlow(
            ShopListViewModel.State(
                pagindDataFlow = Pager(
                    PagingConfig(pageSize = ShopSource.PAGE_SIZE)
                ) {
                    ShopSource(shopUseCase, Location(34.67, 135.52), SearchRange.L_2000)
                }.flow
                    .cachedIn(viewModelScope)
            )
        )

    override fun event(event: ShopListViewModel.Event) {
        viewModelScope.launch {
            @Exhaustive
            when (event) {
            }
        }
    }
}
