package com.compose.news.navigation

import androidx.navigation3.runtime.NavKey

/**
 * 把「前进 / 返回」事件写成对 [NavigationState] 的更新（单向数据流）。
 * NavDisplay 只观察状态，不直接改栈。
 */
class Navigator(val state: NavigationState) {
    fun navigate(route: NavKey) {
        if (route in state.backStacks.keys) {
            state.topLevelRoute = route
        } else {
            state.backStacks[state.topLevelRoute]?.add(route)
        }
    }

    fun goBack(): Boolean {
        val currentStack = state.backStacks[state.topLevelRoute] ?: return false
        val currentRoute = currentStack.lastOrNull() ?: return false
        if (currentRoute != state.topLevelRoute) {
            currentStack.removeLastOrNull()
            return true
        }
        if (state.topLevelRoute != state.startRoute) {
            state.topLevelRoute = state.startRoute
            return true
        }
        return false
    }
}
