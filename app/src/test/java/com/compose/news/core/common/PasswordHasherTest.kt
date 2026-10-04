package com.compose.news.core.common

import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordHasherTest {
    @Test
    fun same_password_and_salt_produce_same_hash() {
        val salt = "abcd"
        val hash = PasswordHasher.hash("Demo1234", salt)
        assertTrue(PasswordHasher.matches("Demo1234", salt, hash))
        assertTrue(!PasswordHasher.matches("wrong", salt, hash))
    }
}
