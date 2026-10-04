package com.compose.news.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.compose.news.ui.article.ArticleDetailScreen
import com.compose.news.ui.auth.AuthScreen
import com.compose.news.ui.bookmarks.BookmarksScreen
import com.compose.news.ui.home.HomeScreen
import com.compose.news.ui.profile.ProfileScreen
import com.compose.news.ui.search.SearchScreen

private data class TopLevelTab(
    val route: NewsRoute,
    val label: String,
    val icon: ImageVector,
)

/**
 * Navigation 3 宿主：自有回退栈 + [NavDisplay]，不再使用 NavHost / NavController。
 */
@Composable
fun NewsNavHost() {
    val navigationState = rememberNavigationState(
        startRoute = NewsRoute.Home,
        topLevelRoutes = TopLevelRoutes,
    )
    val navigator = remember(navigationState) { Navigator(navigationState) }
    val activity = LocalActivity.current
    val tabs = listOf(
        TopLevelTab(NewsRoute.Home, "资讯", Icons.Outlined.Home),
        TopLevelTab(NewsRoute.Search, "搜索", Icons.Outlined.Search),
        TopLevelTab(NewsRoute.Bookmarks, "收藏", Icons.Outlined.BookmarkBorder),
        TopLevelTab(NewsRoute.Profile, "我的", Icons.Outlined.Person),
    )
    val current = navigationState.currentRoute
    val showBar = current in TopLevelRoutes

    val newsEntryProvider = entryProvider<NavKey> {
        entry<NewsRoute.Home> {
            HomeScreen(
                onOpenArticle = { navigator.navigate(NewsRoute.Article(it)) },
                onNeedLogin = { navigator.navigate(NewsRoute.Auth) },
            )
        }
        entry<NewsRoute.Search> {
            SearchScreen(
                onOpenArticle = { navigator.navigate(NewsRoute.Article(it)) },
                onNeedLogin = { navigator.navigate(NewsRoute.Auth) },
            )
        }
        entry<NewsRoute.Bookmarks> {
            BookmarksScreen(
                onOpenArticle = { navigator.navigate(NewsRoute.Article(it)) },
                onLogin = { navigator.navigate(NewsRoute.Auth) },
            )
        }
        entry<NewsRoute.Profile> {
            ProfileScreen(onLogin = { navigator.navigate(NewsRoute.Auth) })
        }
        entry<NewsRoute.Article> { key ->
            ArticleDetailScreen(
                articleId = key.articleId,
                onBack = { navigator.goBack() },
                onNeedLogin = { navigator.navigate(NewsRoute.Auth) },
            )
        }
        entry<NewsRoute.Auth> {
            AuthScreen(
                onBack = { navigator.goBack() },
                onLoggedIn = { navigator.goBack() },
            )
        }
    }

    Scaffold(
        bottomBar = {
            if (showBar) {
                NavigationBar {
                    tabs.forEach { tab ->
                        NavigationBarItem(
                            selected = tab.route == navigationState.topLevelRoute,
                            onClick = { navigator.navigate(tab.route) },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) },
                        )
                    }
                }
            }
        },
    ) { padding ->
        NavDisplay(
            entries = navigationState.toEntries(newsEntryProvider),
            onBack = {
                if (!navigator.goBack()) {
                    activity?.finish()
                }
            },
            transitionSpec = {
                (fadeIn(tween(220)) + slideInHorizontally { it / 4 }) togetherWith
                    fadeOut(tween(220))
            },
            popTransitionSpec = {
                fadeIn(tween(220)) togetherWith
                    (fadeOut(tween(220)) + slideOutHorizontally { it / 4 })
            },
            modifier = Modifier.padding(padding),
        )
    }
}
