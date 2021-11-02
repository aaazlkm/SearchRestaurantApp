package com.example.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.shop.model.ShopId
import com.example.presentation.core.theme.AppTheme
import com.example.presentation.core.theme.appThemeColors
import com.example.presentation.shop.list.ShopListScreen
import com.example.presentation.shop.single.ShopSingleScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(appThemeColors.background)
        systemUiController.setNavigationBarColor(appThemeColors.surface)
    }
    val appNavController = rememberNavController().let { remember(it) { AppNavController(it) } }
    AppTheme(
        content = {
            NavHost(
                navController = appNavController.navHostController,
                startDestination = Routes.ShopList.path,
                modifier = modifier
            ) {
                composable(
                    route = Routes.ShopList.path,
                ) {
                    ShopListScreen(
                        onClickShopItem = { appNavController.navigateToShopSingle(it.id) }
                    )
                }
                composable(
                    route = Routes.ShopSingle.path,
                    arguments = listOf(
                        navArgument(Routes.ShopSingle.Argument.ShopId.key) {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val shopId =
                        backStackEntry.arguments?.getString(Routes.ShopSingle.Argument.ShopId.key)
                            ?.let { ShopId(it) }
                            ?: throw IllegalArgumentException("引数が渡されていません")
                    ShopSingleScreen(shopId)
                }
            }
        }
    )
}
