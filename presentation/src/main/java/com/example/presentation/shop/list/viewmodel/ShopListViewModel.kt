package com.example.presentation.shop.list.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchRange
import com.example.presentation.core.UnidirectionalViewModel
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.SearchState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ShopListViewModel :
    UnidirectionalViewModel<ShopListViewModel.Event, ShopListViewModel.Effect, ShopListViewModel.State> {
    data class State(
        val searchQueryBuilder: SearchQuery.Builder = SearchQuery.Builder(),
        val searchState: SearchState = SearchState.Preparing,
        val emptyImageType: EmptyImageType = EmptyImageType.SUSHI,
    )

    sealed class Effect {
        sealed class SearchResult : Effect() {
            object FailedForNoLocationPermission : Effect()
            object Success : Effect()
        }
    }

    sealed class Event {
        data class ChangeSearchRange(val searchRange: SearchRange) : Event()
        object ClickSearchButton : Event()
    }

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
