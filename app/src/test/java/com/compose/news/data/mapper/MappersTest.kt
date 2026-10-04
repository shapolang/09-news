package com.compose.news.data.mapper

import com.compose.news.data.remote.dto.NetworkArticle
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {
    @Test
    fun networkArticle_maps_to_domain_and_keeps_ids() {
        val network = NetworkArticle(
            id = 42L,
            title = "Starship",
            url = "https://example.com",
            imageUrl = "https://img",
            newsSite = "NASA",
            summary = "summary",
            publishedAt = "2026-01-01T00:00:00Z",
            featured = true,
        )
        val domain = network.toDomain()
        assertEquals(42L, domain.id)
        assertEquals("NASA", domain.newsSite)
        val entity = network.toEntity("feed:all")
        assertEquals("feed:all", entity.cacheKey)
        assertEquals("feed:nasa", cacheKeyForFeed("nasa"))
    }
}
