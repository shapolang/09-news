package com.compose.news

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp

/**
 * 应用进程入口。
 *
 * 实现 [ImageLoaderFactory] 让 Coil 始终用 Application 上下文建单例，
 * 避免 AsyncImage 走到 ContextThemeWrapper 后 AppOps 读到空包名而崩溃。
 */
@HiltAndroidApp
class NewsApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .respectCacheHeaders(false)
            .build()
    }
}
