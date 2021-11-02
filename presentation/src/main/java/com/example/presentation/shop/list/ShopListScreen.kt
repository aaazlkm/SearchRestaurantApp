package com.example.presentation.shop.list

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchRange
import com.example.domain.shop.model.Shop
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.R
import com.example.presentation.core.use
import com.example.presentation.dialog.SystemSettingDialog
import com.example.presentation.shop.list.model.EmptyImageType
import com.example.presentation.shop.list.model.SearchState
import com.example.presentation.shop.list.view.BottomSheetContent
import com.example.presentation.shop.list.view.SearchPreparingView
import com.example.presentation.shop.list.view.ShopList
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
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
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
                        is SearchState.Preparing -> SearchPreparingView(
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

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onClickRetry
            ) {
                Text(text = context.getString(R.string.error_retry))
            }
        }
    }
}

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier.clickable { onClickRetry.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Text(text = context.getString(R.string.error_retry))
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
