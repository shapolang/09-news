package com.compose.news.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Navigation 3 的回退栈键。
 * 必须 `@Serializable` 且实现 [NavKey]，才能用 [androidx.navigation3.runtime.rememberNavBackStack]
 * 在旋转屏幕 / 进程被杀后恢复导航状态。
 *
 * 顶层 Tab：Home / Search / Bookmarks / Profile（各自独立回退栈）。
 * 二级页：Article / Auth（压入当前 Tab 的栈）。
 */
@Serializable
sealed interface NewsRoute : NavKey {
    @Serializable
    data object Home : NewsRoute

    @Serializable
    data object Search : NewsRoute

    @Serializable
    data object Bookmarks : NewsRoute

    @Serializable
    data object Profile : NewsRoute

    @Serializable
    data class Article(val articleId: Long) : NewsRoute

    @Serializable
    data object Auth : NewsRoute
}

val TopLevelRoutes: List<NewsRoute> = listOf(
    NewsRoute.Home,
    NewsRoute.Search,
    NewsRoute.Bookmarks,
    NewsRoute.Profile,
)
