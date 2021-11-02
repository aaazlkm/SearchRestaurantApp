package com.example.presentation.core.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets

val grey900 = Color(0XFF212121)

val appThemeColors = lightColors(
    primary = Color(0xFFf6ba33),
    primaryVariant = Color(0xFFd39c2d),
    onPrimary = Color.Black,
    background = Color(0xFFfafafa),
    onBackground = grey900,
    surface = Color.White,
    onSurface = grey900,
    error = Color(0xffB00020),
    onError = Color.White,
)

@Composable
fun AppTheme(
    content: @Composable
    () -> Unit,
) {
    ProvideWindowInsets {
        MaterialTheme(
            colors = appThemeColors,
            typography = MaterialTheme.typography,
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}

@Composable
fun AppThemeWithBackground(
    content: @Composable () -> Unit,
) {
    Surface {
        AppTheme(content)
    }
}
