
# 🌾 Crop Management System (农作物信息管理系统)

这是为后端组招新考核开发的第一个 Spring Boot 全栈练习项目。本项目旨在实现一个规范的农作物信息管理后台，打通了从 RESTful 接口到数据库持久化的全链路。

## 🚀 技术栈 (Tech Stack)

* **Framework**: Spring Boot v3.3.0
* **ORM**: MyBatis Framework
* **Database**: MySQL 8.0
* **Documentation**: SpringDoc OpenAPI (Swagger UI)
* **Tools**: Lombok (用于简化代码), Maven (构建工具)

## 🏗️ 项目结构 (Project Structure)

遵循标准的三层架构设计，确保代码的高内聚与低耦合：

```plaintext
src/main/java/com/example/cropmanagement/
├── controller/  # 控制层：负责处理 HTTP 请求与响应
├── service/     # 业务逻辑层：负责核心业务处理
├── mapper/      # 数据持久层：负责与 MySQL 进行数据交互
└── entity/      # 实体类：定义农作物数据模型 (POJO)
````

## 🛠️ 快速启动 (Quick Start)

1.  **数据库准备**：
    - 在本地 MySQL 中创建名为 `crop_db` 的数据库。
    - 执行 `src/main/resources` 下（或文档中）的 SQL 脚本创建 `crop` 表。
2.  **配置修改**：
    - 修改 `src/main/resources/application.yml` 中的数据库用户名及密码。
3.  **运行项目**：
    - 使用 IntelliJ IDEA 运行 `CropManagementApplication.java`。

## 📝 接口规范 (API Specification)

本项目完整实现了考核要求的 5 个核心 CRUD 接口：

| 功能 | 请求方式 | 接口路径 | 描述 |
| :--- | :--- | :--- | :--- |
| **添加** | `POST` | `/crop` | 新增农作物信息，ID 自动生成 |
| **详情** | `GET` | `/crop/{id}` | 根据唯一 ID 获取作物详情 |
| **列表** | `GET` | `/crop/list` | 获取所有农作物的详细列表 |
| **更新** | `PUT` | `/crop/{id}` | 修改已存在的农作物信息 |
| **删除** | `DELETE` | `/crop/{id}` | 移除指定的农作物记录 |

## 🔍 接口测试 (API Testing)

项目集成了 **Swagger UI**，启动后访问以下链接即可进行可视化测试：

🔗 [http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)

-----

*“种一棵树最好的时间是十年前，其次是现在。” —— 或者是写代码的时候。*

