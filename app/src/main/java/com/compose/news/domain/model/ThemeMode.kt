package com.compose.news.domain.model

/**
 * 主题策略。写入 DataStore 后，Compose 主题会立即重组。
 * SYSTEM 跟随系统深色模式，符合 Material 3 默认行为。
 */
enum class ThemeMode {
    SYSTEM,
    LIGHT,
    DARK,
}
