package com.example.presentation

import androidx.navigation.NavHostController
import com.example.domain.shop.model.ShopId

sealed class Routes {
    object ShopList : Routes()
    object ShopSearch : Routes()
    object ShopSingle : Routes() {
        sealed class Argument(val key: String) {
            object ShopId : Argument("shopId")
        }

        fun pathWithArgument(shopId: ShopId): String = path + "/${shopId.value}"
    }

    val path: String
        get() = when (this) {
            ShopList -> "shop/list"
            ShopSearch -> "shop/search"
            is ShopSingle -> "shop/single/${ShopSingle.Argument.ShopId.key}"
        }
}

class AppNavController constructor(
    val navHostController: NavHostController,
) {
    fun navigateToShopSingle(shopId: ShopId) {
        navHostController.navigate(Routes.ShopSingle.pathWithArgument(shopId))
    }
}
