package com.compose.news.ui.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.compose.news.ui.components.ArticleCard
import com.compose.news.ui.components.EmptyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    onOpenArticle: (Long) -> Unit,
    onLogin: () -> Unit,
    viewModel: BookmarksViewModel = hiltViewModel(),
) {
    val session by viewModel.session.collectAsStateWithLifecycle()
    val articles by viewModel.articles.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { TopAppBar(title = { Text("收藏") }) },
        contentWindowInsets = WindowInsets(0),
    ) { padding ->
        when {
            session == null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    EmptyState("登录后同步收藏", "收藏按账号隔离，存储在本地 Room 数据库")
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onLogin) { Text("去登录") }
                }
            }
            articles.isEmpty() -> {
                EmptyState("还没有收藏", "在资讯列表点书签即可保存到本地")
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(articles, key = { it.id }) { article ->
                        ArticleCard(
                            article = article,
                            bookmarked = true,
                            onClick = { onOpenArticle(article.id) },
                            onBookmark = { viewModel.remove(article) },
                        )
                    }
                }
            }
        }
    }
}
