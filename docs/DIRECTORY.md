# 目录与文件职责

根目录：`09-news/`

## 构建与工程配置

| 路径 | 作用 |
| --- | --- |
| `settings.gradle.kts` | 插件仓库、依赖仓库、包含 `:app` |
| `build.gradle.kts` | 根工程插件声明（不应用到自身） |
| `gradle.properties` | JVM 内存、AndroidX、Kotlin 官方代码风格、AGP 9 KSP 兼容 |
| `gradle/libs.versions.toml` | Version Catalog：所有库版本的唯一来源（Google 推荐） |
| `gradle/wrapper/` | Gradle Wrapper，保证每人使用同一 Gradle 9.2.1 |
| `gradlew` / `gradlew.bat` | 命令行构建入口 |
| `.gitignore` | 忽略 build、local.properties、IDE 缓存 |
| `README.md` | 项目总览与运行方式 |
| `docs/ARCHITECTURE.md` | 分层、UDF、离线优先、登录与主题设计 |
| `docs/DIRECTORY.md` | 本文件 |

## `:app` 模块

| 路径 | 作用 |
| --- | --- |
| `app/build.gradle.kts` | Application 插件、Compose、Hilt、KSP、依赖 |
| `app/proguard-rules.pro` | R8 规则（当前未开启 minify） |
| `app/src/main/AndroidManifest.xml` | 权限、Application、Launcher Activity、Splash 主题 |
| `app/src/main/res/values/` | 字符串、颜色、窗口主题 |
| `app/src/main/res/xml/` | 备份 / 数据提取规则 |
| `app/src/main/res/drawable/` | 自适应图标前景/背景 |
| `app/src/main/res/mipmap-anydpi-v26/` | 启动器自适应图标 |

## 应用入口

| 文件 | 作用 |
| --- | --- |
| `NewsApplication.kt` | `@HiltAndroidApp`，创建应用级依赖图 |
| `MainActivity.kt` | 唯一 Activity：Splash、边到边、setContent |

## `core/common` — 跨层工具

| 文件 | 作用 |
| --- | --- |
| `PasswordHasher.kt` | 加盐 SHA-256，仅用于本地演示账号 |
| `Result.kt` | 业务成功/失败封装 |
| `DateFormatter.kt` | ISO 时间展示 |

## `domain` — 领域层（无 Android 框架依赖）

| 路径 | 作用 |
| --- | --- |
| `model/Article.kt` | UI 使用的新闻实体 |
| `model/NewsChannel.kt` | 频道，对应 API `news_site` |
| `model/UserSession.kt` | 登录会话 |
| `model/ThemeMode.kt` | 主题枚举 |
| `repository/*.kt` | 仓库接口，供 UI/UseCase 依赖 |
| `usecase/ObserveFeedUseCase.kt` | 订阅分页资讯 |
| `usecase/SearchNewsUseCase.kt` | 搜索 |
| `usecase/ObserveArticleUseCase.kt` | 观察单篇（Room 流） |
| `usecase/BookmarkUseCases.kt` | 收藏观察与切换 |
| `usecase/AuthUseCases.kt` | 登录注册登出 |

## `data` — 数据层

| 路径 | 作用 |
| --- | --- |
| `remote/api/SpaceflightNewsApi.kt` | Retrofit 接口 |
| `remote/dto/NetworkArticle.kt` | 网络 JSON 模型 |
| `remote/NetworkMonitor.kt` | 在线/离线 Flow |
| `remote/paging/NewsRemoteMediator.kt` | 网络写入 Room 的分页胶水 |
| `local/db/NewsDatabase.kt` | Room 数据库定义 |
| `local/dao/ArticleDao.kt` | 资讯缓存 CRUD + PagingSource |
| `local/dao/RemoteKeysDao.kt` | 分页 offset |
| `local/dao/BookmarkDao.kt` | 收藏 |
| `local/dao/UserDao.kt` | 本地用户 |
| `local/entity/*.kt` | 表结构 |
| `local/prefs/SessionDataSource.kt` | DataStore 会话 id |
| `local/prefs/ThemeDataSource.kt` | DataStore 主题 |
| `mapper/Mappers.kt` | DTO ↔ Entity ↔ Domain |
| `repository/*Impl.kt` | 仓库实现，UI 不得直接依赖 |

## `di` — Hilt 模块

| 文件 | 作用 |
| --- | --- |
| `AppModule.kt` | 提供 Retrofit、OkHttp、Json、Room、DataStore、DAO |
| `RepositoryModule.kt` | `@Binds` 接口到实现 |

## `navigation`

| 文件 | 作用 |
| --- | --- |
| `NewsRoute.kt` | Navigation 3 的 NavKey（类型安全目的地） |
| `NavigationState.kt` | 多 Tab 回退栈状态，rememberNavBackStack 持久化 |
| `Navigator.kt` | 把 navigate / back 事件写成对状态的更新 |
| `NewsNavHost.kt` | Scaffold + BottomBar + NavDisplay |

## `ui` — 界面层

| 路径 | 作用 |
| --- | --- |
| `HorizonApp.kt` | 根 Composable：主题 + 导航 |
| `AppViewModel.kt` | 根级主题状态 |
| `theme/Color.kt` | 种子色 |
| `theme/Type.kt` | 字体排版 |
| `theme/Theme.kt` | Material 3 深浅色与动态取色 |
| `components/ArticleCard.kt` | 列表卡片 |
| `components/FeedbackStates.kt` | 加载/空/错误 |
| `home/` | 资讯流 + 频道 Chip + 下拉刷新 |
| `search/` | 搜索框防抖 + 结果分页 |
| `bookmarks/` | 收藏列表与登录引导 |
| `article/` | 详情、外链原文、收藏 |
| `auth/` | 登录/注册 AnimatedContent |
| `profile/` | 账号、主题切换、API 说明 |

## 测试

| 路径 | 作用 |
| --- | --- |
| `app/src/test/.../MappersTest.kt` | 映射纯函数 |
| `app/src/test/.../PasswordHasherTest.kt` | 哈希一致性 |
