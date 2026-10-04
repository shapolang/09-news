# 星闻 Horizon 架构说明

本文对应 Google 文档：

- https://developer.android.com/topic/architecture
- https://developer.android.com/topic/architecture/data-layer
- https://developer.android.com/topic/architecture/ui-layer
- https://developer.android.com/topic/libraries/architecture/paging/v3-network-db

## 1. 分层（UI → Domain → Data）

```
┌─────────────────────────────────────────┐
│  UI Layer                               │
│  Compose Screen + ViewModel + UiState   │
│  只依赖 Domain 的 UseCase / Model       │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│  Domain Layer                           │
│  纯 Kotlin：Model、Repository 接口、     │
│  UseCase。无 Android / Retrofit / Room  │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│  Data Layer                             │
│  Repository 实现                        │
│   ├─ Remote：Retrofit SNAPI             │
│   └─ Local ：Room + DataStore           │
│  Room 是资讯列表的 Single Source of Truth│
└─────────────────────────────────────────┘
```

依赖方向只能向下。UI 绝不直接 new Retrofit 或写 SQL。

## 2. 单向数据流（UDF）

1. 用户点击（收藏、切换频道、搜索）
2. Screen 调用 ViewModel 方法
3. ViewModel 调用 UseCase / Repository
4. 数据以 `Flow` / `StateFlow` / `PagingData` 回到 UI
5. Compose 重组，不在 Composable 里做 IO

## 3. 离线优先资讯流

`NewsRemoteMediator`：

1. Paging 需要下一页 → 请求 SNAPI
2. 事务写入 `articles` + `remote_keys`
3. UI 只订阅 `ArticleDao.pagingSource(cacheKey)`
4. 断网时 `NetworkMonitor` 显示横幅，已缓存页仍可滚动
5. 下拉刷新触发 `LoadType.REFRESH`，按 cacheKey 覆盖该频道缓存

`cacheKey` 示例：`feed:all`、`feed:NASA`、`search:starship`。主键为 `(id, cacheKey)`，避免频道互相覆盖。

## 4. 登录与收藏

公开 API 没有账号系统。产品层仍提供完整登录：

- `users` 表保存 email、displayName、salt、SHA-256(salt+password)
- DataStore 只存 `session_user_id`
- `bookmarks` 以 `(userId, articleId)` 为复合主键，并冗余文章字段，避免 RemoteMediator 清缓存后收藏空白
- 未登录点收藏会导航到登录页

这是「演示级本地身份」。上线应换成 Identity Platform / 自建后端 + Credential Manager。

## 5. 主题

`ThemeDataSource`（DataStore）→ `UserPreferencesRepository` → `AppViewModel.themeMode` → `HorizonTheme`。

`ThemeMode.SYSTEM | LIGHT | DARK`。API 31+ 使用动态取色；切换时对 surface/primary 做 400ms 颜色动画。

## 6. 导航（Navigation 3）

Single-Activity。**不使用 Navigation 2 的 NavHost / NavController。**

- 目的地键：`NewsRoute` 实现 `NavKey` 且 `@Serializable`
- 状态：`NavigationState`（每个底部 Tab 一条回退栈，进程被杀可恢复）
- 事件：`Navigator.navigate` / `goBack` 只改状态
- UI：`NavDisplay` 观察 `entries` 并播放转场

底部：资讯 / 搜索 / 收藏 / 我的。  
二级：文章详情、登录（压入当前 Tab 栈，并隐藏 BottomBar）。

参考：https://developer.android.com/guide/navigation/navigation-3

## 7. 依赖注入

Hilt：

- `AppModule`：`@Provides` Retrofit / Room / DataStore
- `RepositoryModule`：`@Binds` 接口到实现（官方推荐，少样板、可校验）

作用域全部挂在 `SingletonComponent`，保证仓库与数据库单例。

## 8. 包结构原则

采用 **按层分包，再按功能分子包**（Google 文档允许的混合方式）：

- `domain/` 按技术角色（model / repository / usecase）
- `data/` 按数据源（remote / local / mapper / repository）
- `ui/` 按功能（home / search / bookmarks / article / auth / profile）

单模块而非多 module，是为了学习路径更短；分层边界与 NIA 一致，后续可按 `feature:*` / `core:*` 原样拆 module。
