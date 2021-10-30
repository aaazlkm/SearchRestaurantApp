package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.shop.list.viewmodel.RealShopListViewModel
import com.example.presentation.shop.list.viewmodel.provideShopListViewModelFactory

@Composable
fun ProvideViewModels(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        provideShopListViewModelFactory { hiltViewModel<RealShopListViewModel>() },
        content = content
    )
}
