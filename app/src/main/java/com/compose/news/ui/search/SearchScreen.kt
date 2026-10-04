package com.compose.news.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.compose.news.ui.components.ArticleCard
import com.compose.news.ui.components.EmptyState
import com.compose.news.ui.components.ErrorState
import com.compose.news.ui.components.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onOpenArticle: (Long) -> Unit,
    onNeedLogin: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val bookmarkIds by viewModel.bookmarkIds.collectAsStateWithLifecycle()
    val items = viewModel.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("搜索") }) },
        contentWindowInsets = WindowInsets(0),
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = viewModel::onQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("搜索标题或摘要，例如 Starship") },
                    leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                    singleLine = true,
                )
            }
            if (query.trim().length < 2) {
                item { EmptyState("输入关键词", "至少 2 个字符，接口会匹配标题与摘要") }
            } else {
                when {
                    items.loadState.refresh is LoadState.Loading && items.itemCount == 0 -> {
                        item { LoadingState() }
                    }
                    items.loadState.refresh is LoadState.Error && items.itemCount == 0 -> {
                        item {
                            ErrorState(
                                message = (items.loadState.refresh as LoadState.Error).error.message ?: "搜索失败",
                                onRetry = { items.retry() },
                            )
                        }
                    }
                    items.itemCount == 0 -> {
                        item { EmptyState("没有结果", "换个英文关键词试试，数据源以航天资讯为主") }
                    }
                    else -> {
                        items(count = items.itemCount, key = items.itemKey { it.id }) { index ->
                            val article = items[index] ?: return@items
                            ArticleCard(
                                article = article,
                                bookmarked = article.id in bookmarkIds,
                                onClick = { onOpenArticle(article.id) },
                                onBookmark = { viewModel.onToggleBookmark(article, onNeedLogin) },
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
