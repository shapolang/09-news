package com.compose.news.core.common

/**
 * UI / 仓库共用的一次性结果封装。
 *
 * 不使用 Kotlin Result，是为了在 Compose 层能稳定区分「业务失败」与「成功」，
 * 并携带面向用户的中文消息。
 */
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val message: String, val cause: Throwable? = null) : Result<Nothing>
}

inline fun <T> Result<T>.getOrNull(): T? = (this as? Result.Success)?.data
