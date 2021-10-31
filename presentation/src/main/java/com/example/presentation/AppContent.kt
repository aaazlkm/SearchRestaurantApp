package com.example.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.shop.model.ShopId
import com.example.presentation.shop.list.ShopListScreen
import com.example.presentation.shop.single.ShopSingleScreen
import com.google.accompanist.insets.ProvideWindowInsets

@ExperimentalMaterialApi
@Composable
fun AppContent(
    modifier: Modifier = Modifier,
) {
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

val blue200 = Color(0xFF00B5E2)
val blue700 = Color(0xFF00639D)
val green200 = Color(0xFF48D597)
val greenDroid = Color(0xFF3DDC84)

val blue300 = Color(0xFF0095C4)
val gray = Color(0xFF232323)

internal val LocalFilterMuskColor = staticCompositionLocalOf<Color> {
    error("No Musk Color Provided")
}

private val DarkColorPalette = darkColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = green200,
)

private val LightColorPalette = lightColors(
    primary = blue200,
    primaryVariant = blue700,
    secondary = green200,
)

private val DarkFilterMuskColor = gray
private val LightFilterMuskColor = blue300

@Composable
fun AppTheme(
    theme: Theme? = Theme.SYSTEM,
    content: @Composable
    () -> Unit,
) {
    val filterMuskColor = theme.filterMuskColor()
    ProvideWindowInsets {
        CompositionLocalProvider(LocalFilterMuskColor provides filterMuskColor) {
            MaterialTheme(
                colors = colorPalette(theme),
                typography = MaterialTheme.typography,
                shapes = MaterialTheme.shapes,
                content = content
            )
        }
    }
}

@Composable
private fun colorPalette(theme: Theme?): Colors {
    return when (theme) {
        Theme.SYSTEM -> systemColorPalette()
        Theme.DARK -> DarkColorPalette
        Theme.LIGHT -> LightColorPalette
        else -> systemColorPalette()
    }
}

@Composable
private fun systemColorPalette(): Colors {
    return if (isSystemInDarkTheme()) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
}

@Composable
fun Theme?.filterMuskColor(): Color {
    return when (this) {
        Theme.SYSTEM -> systemFilterMuskColor()
        Theme.DARK -> DarkFilterMuskColor
        Theme.LIGHT -> LightFilterMuskColor
        else -> systemFilterMuskColor()
    }
}

@Composable
private fun systemFilterMuskColor(): Color {
    return if (isSystemInDarkTheme()) {
        DarkFilterMuskColor
    } else {
        LightFilterMuskColor
    }
}

@Composable
fun AppThemeWithBackground(
    theme: Theme? = Theme.SYSTEM,
    content: @Composable
    () -> Unit,
) {
    Surface {
        AppTheme(theme, content)
    }
}

object ConferenceAppFeederTheme {
    val filterMuskColor: Color
        @Composable
        get() = LocalFilterMuskColor.current
}

enum class Theme {
    SYSTEM,
    DARK,
    LIGHT
}
