# 🌾 Crop Management System (农作物信息管理系统)

本项目是为后端组招新考核开发的 Spring Boot 练习项目。系统实现了农作物信息的全生命周期管理 (CRUD)，并严格遵循企业级分层架构规范，同时针对高并发场景引入了 Redis 缓存优化。

## 🛠️ 技术选型 (Tech Stack)

* **后端框架**: Spring Boot v4.0.4 (提供核心容器与 Web 支持) c.e.c.CropManagementApplication]
* **持久层**: MyBatis (实现 SQL 与 Java 对象的解耦映射)
* **数据库**: MySQL 8.0 (持久化存储农作物数据)
* **性能缓存**: Redis (通过内存缓存实现接口毫秒级响应)
* **API文档**: Swagger UI / SpringDoc (自动化生成交互式测试界面)
* **效率工具**: Lombok & Jackson (集成 JavaTimeModule 解决 Java 8 时间序列化难题)

## 🏗️ 架构设计 (Architecture)

系统采用经典的 **Controller-Service-Mapper** 三层架构，并加入了全局增强处理：
1.  **Controller 层**: 负责接收请求，并使用 `@Valid` 开启参数校验。
2.  **Service 层**: 业务逻辑核心。**集成 Redis 缓存逻辑**，实现“缓存命中则秒回，失效则查库并回填”的闭环。
3.  **DAO/Mapper 层**: 直接操作数据库，负责执行具体的 CRUD SQL 语句。
4.  **Global Handler**: 全局异常拦截器，统一捕获校验失败及运行错误并返回标准化 JSON。

## 📝 接口实现与测试证明 (API Implementation & Testing)

本项目全接口采用 **统一响应格式 (Result<T>)**，确保前端获取到的数据结构始终为 `{code, msg, data}`。

### 1. 添加农作物 (POST /crop)
* **功能**: 接收 JSON 数据并进行合法性校验，通过后持久化到数据库并返回生成的 ID。
* **测试截图**:
  ![POST测试](https://github.com/user-attachments/assets/1070c35f-20b1-4ce9-988b-3d096eceb9e0)
  ![](https://github.com/user-attachments/assets/2f7382d0-e80c-409b-b384-8f411dc4b1da)

### 2. 数据库持久化验证
* **证明**: 验证数据已跨越内存，成功写入本地 MySQL 数据库 `crop` 表中。
* **测试截图**:
  ![数据库记录](https://github.com/user-attachments/assets/fd2300c9-b399-4567-9b73-d64cf49ea8a9)

### 3. 按 ID 精准查询 (GET /crop/{id})
* **功能**: 根据主键 ID 获取详情。引入 **Redis 缓存**，第二次查询实现零延迟响应。
* **缓存命中证明（控制台输出）**:
  ![](https://github.com/user-attachments/assets/3d7b9e61-333b-4359-9f41-c217f0072187)
* **接口测试截图**:
  ![GET详情测试](https://github.com/user-attachments/assets/236c3907-f263-4392-ba6c-38fc4eaf0a97)

### 4. 获取作物全量列表 (GET /crop/list)
* **功能**: 获取当前数据库中登记的所有农作物列表，同样支持 Redis 缓存优化。
* **接口测试截图**:
  ![](https://github.com/user-attachments/assets/518ba42c-395b-44de-a936-1d970660a47e)

### 5. 健壮性测试 (全局异常拦截)
* **功能**: 当输入非法参数（如 `growth_cycle: -999`）时，系统通过拦截器优雅捕获异常并返回错误码，而非崩溃。
* **拦截效果证明**:
  ![](https://github.com/user-attachments/assets/8a0faa12-1f11-4f11-a94b-da39d6a40bb3)

### 6. 更新与删除 (PUT & DELETE)
* **功能**: 实现信息的动态修改与安全移除。系统在操作数据库的同时会自动执行 **缓存双删**，确保数据一致性。
* **测试截图**:
  ![更新测试](https://github.com/user-attachments/assets/f6cc6af7-afc3-431f-b157-6e7abcfe8a37)
  ![数据库记录更新](https://github.com/user-attachments/assets/fd2300c9-b399-4567-9b73-d64cf49ea8a9)
  ![删除测试](https://github.com/user-attachments/assets/3aecd1e7-7f56-4fdf-a002-c20ce9c4fe97)
  ![删除结果证明](https://github.com/user-attachments/assets/f86bdaaa-e9f4-4338-8ac9-4de43d701ec0)

### 7. 系统监控演示 (AOP + Logging)
* **功能**: 系统已集成 AOP 监控切面，全自动记录请求路径、参数、响应结果及执行耗时。如图所示，系统精准捕捉到了 /crop/list 的访问，并展示了标准化响应结果。
* **测试截图**:
  ![](https://github.com/user-attachments/assets/e79b6348-aba0-4fc2-8bfc-60f9ce2f1fe5)

## 🚀 如何运行 (How to Run)

1.  克隆本项目到本地。
2.  在 MySQL 中执行 `src/main/resources` 下的建表语句。
3.  **启动本地 Redis 服务** (确保 `redis-cli ping` 返回 `PONG`)。
4.  根据实际环境修改 `application.yml` 中的数据库及 Redis 连接参数。
5.  运行 `CropManagementApplication.java`。
6.  访问 Swagger UI 进行全接口自测: `http://localhost:8080/swagger-ui/index.html`。

---
*Created by [陈利奇] @ 2026.03.28*
*(Advanced Development Phase: Redis Caching & Robustness Implemented)*