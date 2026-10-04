package com.compose.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compose.news.ui.HorizonApp
import dagger.hilt.android.AndroidEntryPoint

/**
 * 唯一 Activity（Single-Activity 架构，Google 官方推荐）。
 *
 * 职责：
 * 1. 安装 SplashScreen，避免冷启动白屏。
 * 2. 开启边到边显示（status/navigation bar 透明，由 Compose 处理 insets）。
 * 3. 把整棵 Compose 树交给 [HorizonApp]，此后路由、主题、状态全部在 Compose + ViewModel 中完成。
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HorizonApp()
        }
    }
}
