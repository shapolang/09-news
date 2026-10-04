package com.compose.news.core.common

/** SNAPI 时间为 ISO-8601，这里做轻量展示格式化，避免 minSdk 24 依赖 java.time。 */
object DateFormatter {
    fun pretty(iso: String): String = iso.replace("T", " ").take(16)
}
