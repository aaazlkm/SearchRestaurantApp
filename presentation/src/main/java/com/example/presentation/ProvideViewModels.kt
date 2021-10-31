package com.example.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.shop.list.viewmodel.RealShopListViewModel
import com.example.presentation.shop.list.viewmodel.provideShopListViewModelFactory
import com.example.presentation.shop.single.viewmodel.RealShopSingleViewModel
import com.example.presentation.shop.single.viewmodel.provideShopSingleViewModelFactory

@Composable
fun ProvideViewModels(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        provideShopListViewModelFactory { hiltViewModel<RealShopListViewModel>() },
        provideShopSingleViewModelFactory { hiltViewModel<RealShopSingleViewModel>() },
        content = content
    )
}
