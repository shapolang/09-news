package com.compose.news.ui.theme

import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.compose.news.domain.model.ThemeMode

private val LightColors = lightColorScheme(primary = HorizonBlue)
private val DarkColors = darkColorScheme(primary = HorizonBlue)

/**
 * 应用主题。
 *
 * 动态取色必须走 [android.content.Context.getApplicationContext]。
 * Compose 的 LocalContext 是 ContextThemeWrapper，部分系统（含 API 35/36 模拟器）
 * 上 `getOpPackageName()` 会变成空字符串，WallpaperManager / AppOps 随即抛出
 * `SecurityException: Specified package "" under uid …` 并导致闪退。
 */
@Composable
fun HorizonTheme(
    themeMode: ThemeMode,
    content: @Composable () -> Unit,
) {
    val systemDark = isSystemInDarkTheme()
    val dark = when (themeMode) {
        ThemeMode.SYSTEM -> systemDark
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }
    val appContext = LocalContext.current.applicationContext
    val rawScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        runCatching {
            if (dark) dynamicDarkColorScheme(appContext) else dynamicLightColorScheme(appContext)
        }.getOrElse {
            if (dark) DarkColors else LightColors
        }
    } else {
        if (dark) DarkColors else LightColors
    }
    MaterialTheme(
        colorScheme = rawScheme.animate(),
        typography = HorizonTypography,
        content = content,
    )
}

@Composable
private fun ColorScheme.animate(): ColorScheme {
    return copy(
        primary = animateColor(primary),
        background = animateColor(background),
        surface = animateColor(surface),
        onBackground = animateColor(onBackground),
        onSurface = animateColor(onSurface),
    )
}

@Composable
private fun animateColor(target: Color): Color {
    val animated by animateColorAsState(targetValue = target, animationSpec = tween(400), label = "theme")
    return animated
}
