package com.compose.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer

/**
 * 创建可保存的 Navigation 3 状态：每个顶层 Tab 一条回退栈。
 * 对应官方迁移指南 Step 3.1。
 */
@Composable
fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: List<NavKey>,
): NavigationState {
    val topLevelRoute = rememberSerializable(
        serializer = MutableStateSerializer(NavKeySerializer()),
    ) {
        mutableStateOf(startRoute)
    }
    val backStacks = topLevelRoutes.associateWith { key ->
        rememberNavBackStack(key)
    }
    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks,
            topLevelRoutes = topLevelRoutes,
        )
    }
}

/**
 * Navigation 3 的导航状态（你拥有回退栈，而不是 NavController 拥有）。
 */
class NavigationState(
    val startRoute: NavKey,
    topLevelRoute: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>,
    val topLevelRoutes: List<NavKey>,
) {
    var topLevelRoute: NavKey by topLevelRoute

    val stacksInUse: List<NavKey>
        get() = if (topLevelRoute == startRoute) {
            listOf(startRoute)
        } else {
            listOf(startRoute, topLevelRoute)
        }

    val currentRoute: NavKey?
        get() = backStacks[topLevelRoute]?.lastOrNull()
}

/**
 * 把多栈状态展成 NavDisplay 需要的 [NavEntry] 列表，并为每条栈挂上
 * Saveable + ViewModelStore 装饰器（条目离开栈时 ViewModel 才会被清掉）。
 */
@Composable
fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>,
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratedEntries = topLevelRoutes.associateWith { route ->
        val stack = backStacks.getValue(route)
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
                rememberViewModelStoreNavEntryDecorator<NavKey>(),
            ),
            entryProvider = entryProvider,
        )
    }
    return stacksInUse
        .flatMap { decoratedEntries[it].orEmpty() }
        .toMutableStateList()
}
