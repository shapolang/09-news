package com.compose.news.domain.model

/**
 * 资讯频道。`newsSite` 会原样传到 Spaceflight News API 的 `news_site` 查询参数；
 * `ALL` 表示不筛选来源。
 */
enum class NewsChannel(val label: String, val newsSite: String?) {
    ALL("全部", null),
    NASA("NASA", "NASA"),
    SPACE_NEWS("SpaceNews", "SpaceNews"),
    ARS("Ars Technica", "Ars Technica"),
    ESA("ESA", "ESA"),
    SPACEFLIGHT_NOW("Spaceflight Now", "Spaceflight Now"),
}
