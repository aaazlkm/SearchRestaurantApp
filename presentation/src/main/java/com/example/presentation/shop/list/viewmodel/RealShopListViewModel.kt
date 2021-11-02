package com.example.presentation.shop.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.error.AppError
import com.example.domain.core.result.Result
import com.example.domain.location.usecase.LocationUseCase
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.usecase.ShopUseCase
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.meta.Exhaustive
import javax.inject.Inject

@HiltViewModel
class RealShopListViewModel @Inject constructor(
    private val shopUseCase: ShopUseCase,
    private val locationUseCase: LocationUseCase,
) : ViewModel(), ShopListViewModel {

    private val _effect = Channel<ShopListViewModel.Effect>(Channel.UNLIMITED)
    override val effectFlow: Flow<ShopListViewModel.Effect> = _effect.receiveAsFlow()

    private val _searchQueryBuilder: MutableStateFlow<SearchQuery.Builder> =
        MutableStateFlow(SearchQuery.Builder())

    private val _searchState: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.Preparing)

    private val _emptyImageType: MutableStateFlow<EmptyImageType> =
        MutableStateFlow(EmptyImageType.FISH)

    override val state: StateFlow<ShopListViewModel.State> =
        combine(
            _searchQueryBuilder,
            _searchState,
            _emptyImageType,
        ) { searchQueryBuilder, searchState, emptyImageType ->
            ShopListViewModel.State(
                searchQueryBuilder = searchQueryBuilder,
                searchState = searchState,
                emptyImageType = emptyImageType,
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, ShopListViewModel.State())

    override fun event(event: ShopListViewModel.Event) {
        viewModelScope.launch {
            @Exhaustive
            when (event) {
                is ShopListViewModel.Event.ChangeSearchRange -> {
                    _searchQueryBuilder.value =
                        _searchQueryBuilder.value.setSearchRange(event.searchRange)
                }
                ShopListViewModel.Event.ClickSearchButton -> {
                    when (val result = locationUseCase.lastLocation()) {
                        is Result.Success -> {
                            val searchQuery = _searchQueryBuilder.value.build(result.value)
                            _searchState.value = SearchState.Searching.from(
                                searchQuery = searchQuery,
                                shopUseCase = shopUseCase,
                                coroutineScope = viewModelScope,
                            )
                            _effect.send(ShopListViewModel.Effect.SearchResult.Success)
                        }
                        is Result.Error -> {
                            if (result.e is AppError.Permission.NoLocationPermissionException) {
                                _effect.send(ShopListViewModel.Effect.SearchResult.FailedForNoLocationPermission)
                            }
                            result.e.printStackTrace()
                        }
                    }
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            _searchState.collect {
                // search queryが変更されるたびに、画像を変更する
                if (it is SearchState.Searching) {
                    _emptyImageType.value =
                        EmptyImageType.generateRandomly(without = _emptyImageType.value)
                }
            }
        }
    }
}
