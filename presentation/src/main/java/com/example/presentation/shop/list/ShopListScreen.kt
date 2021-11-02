package com.example.presentation.shop.list

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.model.Shop
import com.example.presentation.core.theme.AppThemeWithBackground
import com.example.presentation.core.use
import com.example.presentation.dialog.SystemSettingDialog
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.SearchState
import com.example.presentation.shop.list.view.BottomSheetContent
import com.example.presentation.shop.list.view.ShopList
import com.example.presentation.shop.list.view.ShopListPreparingView
import com.example.presentation.shop.list.viewmodel.ShopListViewModel
import com.example.presentation.shop.list.viewmodel.shopListViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Composable
fun ShopListScreen(
    onClickShopItem: (Shop) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val hapticFeedback = LocalHapticFeedback.current
    val coroutineScope = rememberCoroutineScope()
    val (
        state,
        effectFlow,
        dispatch,
    ) = use(shopListViewModel())

    // dialogの状態までViewModelで管理したくないので、ここで保持
    val systemSettingDialogVisible = remember { mutableStateOf(false) }
    val locationPersmission = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    LaunchedEffect(key1 = effectFlow) {
        effectFlow.collect { effect ->
            when (effect) {
                ShopListViewModel.Effect.SearchResult.Success -> {
                    sheetState.hide()
                }
                ShopListViewModel.Effect.SearchResult.FailedForNoLocationPermission -> {
                    if (locationPersmission.shouldShowRationale) {
                        systemSettingDialogVisible.value = true
                    } else {
                        locationPersmission.launchMultiplePermissionRequest()
                    }
                }
            }
        }
    }

    ShopListScreen(
        scaffoldState = scaffoldState,
        sheetState = sheetState,
        searchQueryBuilder = state.searchQueryBuilder,
        searchState = state.searchState,
        systemSettingDialogVisible = systemSettingDialogVisible.value,
        emptyImageType = state.emptyImageType,
        onClickShopItem = onClickShopItem,
        onClickSearchBar = {
            coroutineScope.launch {
                sheetState.apply {
                    if (isVisible) hide() else show()
                }
            }
        },
        onClickSearchRange = {
            // FIXME:
            // textHandleMoveは適していないかもしれないが、現状で他に選択肢がないのでこのままにする
            // 他にいい方法があったらそちらに変える
            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            dispatch(ShopListViewModel.Event.ChangeSearchRange(it))
        },
        onClickSearchButton = {
            dispatch(ShopListViewModel.Event.ClickSearchButton)
        },
        onDismissSystemSettingDialogRequest = {
            systemSettingDialogVisible.value = false
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun ShopListScreen(
    scaffoldState: ScaffoldState,
    sheetState: ModalBottomSheetState,
    systemSettingDialogVisible: Boolean,
    searchQueryBuilder: SearchQuery.Builder,
    searchState: SearchState,
    emptyImageType: EmptyImageType,
    onClickShopItem: (Shop) -> Unit,
    onClickSearchBar: () -> Unit,
    onClickSearchRange: (SearchRange) -> Unit,
    onClickSearchButton: () -> Unit,
    onDismissSystemSettingDialogRequest: () -> Unit,
) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(8.dp),
        sheetContent = {
            BottomSheetContent(
                searchQueryBuilder = searchQueryBuilder,
                onClickSearchRange = onClickSearchRange,
                onClickSearchButton = onClickSearchButton,
            )
        },
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                Box {
                    when (searchState) {
                        is SearchState.Preparing -> ShopListPreparingView(
                            searchState = searchState,
                            onClickSearchBar = onClickSearchBar,
                        )
                        is SearchState.Searching -> ShopList(
                            searchQuery = searchState.searchQuery,
                            pagingDataFlow = searchState.shopPagingDataFlow,
                            emptyImageType = emptyImageType,
                            onClickShopItem = onClickShopItem,
                            onClickSearchBar = onClickSearchBar,
                        )
                    }
                }
            }
        )
    }
    if (systemSettingDialogVisible) {
        SystemSettingDialog(
            onDismissRequest = onDismissSystemSettingDialogRequest,
        )
    }
}

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewShopListScreen() {
    AppThemeWithBackground {
        ShopListScreen(
            onClickShopItem = {}
        )
    }
}
