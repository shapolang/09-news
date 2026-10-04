# 星闻 Horizon — 完整新闻客户端

Jetpack Compose 新闻客户端，架构对齐 [Google 官方应用架构指南](https://developer.android.com/topic/architecture) 与 [Now in Android](https://github.com/android/nowinandroid) 的实践：单向数据流、离线优先、Hilt、Paging 3、Room 单一数据源。

## 公开 API

使用 **Spaceflight News API v4**（无需 Key）：

- 文档：https://api.spaceflightnewsapi.net/v4/docs/
- 列表：`GET https://api.spaceflightnewsapi.net/v4/articles/?limit=20&offset=0`
- 搜索：`?search=Starship`
- 来源：`?news_site=NASA`
- 详情：`GET /v4/articles/{id}/`

选择原因：完全公开、分页与搜索齐全，适合作为教学级「真实网络 + 本地缓存」示例。NewsAPI / Guardian 都需要申请 Key，不适合开箱即用。

## 已实现能力

| 能力 | 实现 |
| --- | --- |
| 本地导航 | **Navigation 3**：NavKey + 每 Tab 独立回退栈 + NavDisplay |
| 网络请求 | Retrofit + OkHttp + kotlinx.serialization |
| 本地缓存 | Room + Paging 3 RemoteMediator（离线可滚已缓存页） |
| 登录 / 注册 | 本地 Room 账号 + DataStore 会话 + 加盐 SHA-256 |
| 收藏 | 按 userId 隔离，Room 持久化 |
| 动画 | 页面共享轴滑动、主题色过渡、登录模式 AnimatedContent、离线横幅 |
| 深浅色 | DataStore 持久化 SYSTEM / LIGHT / DARK，Android 12+ 动态取色 |
| 搜索 | 400ms 防抖 + SNAPI `search` |

演示账号：`demo@horizon.news` / `Demo1234`

## 如何运行

1. 用 Android Studio / Cursor 打开目录 `09-news`
2. 等待 Gradle 同步
3. 运行 `app` 到 API 24+ 设备或模拟器

```bash
.\gradlew.bat :app:assembleDebug
```

## 文档

- [架构说明](docs/ARCHITECTURE.md)
- [目录与文件职责](docs/DIRECTORY.md)
