package com.compose.news.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf

/**
 * 网络可达性监视器。
 *
 * 注册 NetworkCallback / 读取 activeNetwork 时，部分模拟器会因 AppOps 空包名抛
 * SecurityException。这里全部包在 try/catch 里，失败时退回「视为在线」，避免闪退。
 */
@Singleton
class NetworkMonitor @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isOnline: Flow<Boolean> = runCatching {
        callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    trySend(true)
                }

                override fun onLost(network: Network) {
                    trySend(currentOnline())
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities,
                ) {
                    trySend(currentOnline())
                }
            }
            trySend(currentOnline())
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
            try {
                connectivityManager.registerNetworkCallback(request, callback)
            } catch (_: SecurityException) {
                trySend(true)
                close()
                return@callbackFlow
            }
            awaitClose {
                runCatching { connectivityManager.unregisterNetworkCallback(callback) }
            }
        }.distinctUntilChanged()
    }.getOrElse {
        flowOf(true)
    }

    private fun currentOnline(): Boolean {
        return runCatching {
            val network = connectivityManager.activeNetwork ?: return false
            val caps = connectivityManager.getNetworkCapabilities(network) ?: return false
            caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }.getOrDefault(true)
    }
}
