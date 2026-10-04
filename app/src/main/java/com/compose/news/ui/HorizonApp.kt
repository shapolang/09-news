package com.compose.news.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.compose.news.navigation.NewsNavHost
import com.compose.news.ui.theme.HorizonTheme

/**
 * Compose 根节点：先应用主题，再挂导航。
 * 主题来自 DataStore，切换后这里重组，所有页面立即换肤。
 */
@Composable
fun HorizonApp(viewModel: AppViewModel = hiltViewModel()) {
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    HorizonTheme(themeMode) {
        NewsNavHost()
    }
}
