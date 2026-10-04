package com.compose.news.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.compose.news.domain.model.NewsChannel
import com.compose.news.ui.components.ArticleCard
import com.compose.news.ui.components.EmptyState
import com.compose.news.ui.components.ErrorState
import com.compose.news.ui.components.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenArticle: (Long) -> Unit,
    onNeedLogin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val channel by viewModel.channel.collectAsStateWithLifecycle()
    val bookmarkIds by viewModel.bookmarkIds.collectAsStateWithLifecycle()
    val online by viewModel.isOnline.collectAsStateWithLifecycle()
    val items = viewModel.feed.collectAsLazyPagingItems()
    val refreshing = items.loadState.refresh is LoadState.Loading && items.itemCount > 0

    Scaffold(
        topBar = { TopAppBar(title = { Text("星闻 Horizon") }) },
        contentWindowInsets = WindowInsets(0),
    ) { padding ->
        PullToRefreshBox(
            isRefreshing = refreshing,
            onRefresh = { items.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {
                    AnimatedVisibility(visible = !online, enter = fadeIn(), exit = fadeOut()) {
                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                "当前离线，正在展示本地缓存",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(NewsChannel.entries, key = { it.name }) { item ->
                            FilterChip(
                                selected = item == channel,
                                onClick = { viewModel.onChannelSelected(item) },
                                label = { Text(item.label) },
                            )
                        }
                    }
                }
                when {
                    items.loadState.refresh is LoadState.Loading && items.itemCount == 0 -> {
                        item { Box(Modifier.fillParentMaxSize()) { LoadingState() } }
                    }
                    items.loadState.refresh is LoadState.Error && items.itemCount == 0 -> {
                        val error = (items.loadState.refresh as LoadState.Error).error
                        item {
                            ErrorState(
                                message = error.message ?: "网络异常",
                                onRetry = { items.retry() },
                            )
                        }
                    }
                    items.itemCount == 0 -> {
                        item { EmptyState("暂无资讯", "换个频道或下拉刷新试试") }
                    }
                    else -> {
                        items(
                            count = items.itemCount,
                            key = items.itemKey { it.id },
                        ) { index ->
                            val article = items[index] ?: return@items
                            ArticleCard(
                                article = article,
                                bookmarked = article.id in bookmarkIds,
                                onClick = { onOpenArticle(article.id) },
                                onBookmark = { viewModel.onToggleBookmark(article, onNeedLogin) },
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                        if (items.loadState.append is LoadState.Loading) {
                            item { LinearProgressIndicator(Modifier.fillMaxWidth().padding(16.dp)) }
                        }
                    }
                }
            }
        }
    }
}
