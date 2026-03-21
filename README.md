# 🌾 Crop Management System (农作物信息管理系统)

本项目是为后端组招新考核开发的 Spring Boot 练习项目。系统实现了农作物信息的全生命周期管理 (CRUD)，并严格遵循企业级分层架构规范。

## 🛠️ 技术选型 (Tech Stack)

* **后端框架**: Spring Boot v3.3.0 (提供核心容器与 Web 支持)
* **持久层**: MyBatis (实现 SQL 与 Java 对象的解耦映射)
* **数据库**: MySQL 8.0 (持久化存储农作物数据)
* **API文档**: Swagger UI / SpringDoc (自动化生成接口测试界面)
* **效率工具**: Lombok (通过注解简化 POJO 开发)

## 🏗️ 架构设计 (Architecture)

系统采用经典的 **Controller-Service-DAO (Mapper)** 三层架构：
1.  **Controller 层**: 暴露 RESTful 接口，处理 HTTP 请求与响应格式化。
2.  **Service 层**: 封装业务逻辑（如校验、计算等），作为 Controller 与 Mapper 的纽带。
3.  **DAO/Mapper 层**: 直接操作数据库，负责执行具体的 CRUD SQL 语句。



## 📝 接口实现与测试证明 (API Implementation & Testing)

本项目已完成全部 5 个核心接口的开发与自测：

### 1. 添加农作物 (POST /crop)
* **功能**: 接收 JSON 数据并持久化到数据库，ID 自动生成。
* **测试截图**:
  ![POST测试](https://github.com/user-attachments/assets/94634dfb-d922-4148-b1f3-374c71e2c6f1)
* ![](https://github.com/user-attachments/assets/2f7382d0-e80c-409b-b384-8f411dc4b1da)
### 2. 数据库持久化验证
* **证明**: 数据已成功写入本地 MySQL 数据库 `crop` 表中。
* **测试截图**:
  ![数据库记录](https://github.com/user-attachments/assets/fd2300c9-b399-4567-9b73-d64cf49ea8a9)

### 3. 获取农作物详情 (GET /crop/{id} & GET /crop/list)
* **功能**: 根据主键 ID 精准查询单条记录，通过list能查询所有作物的记录。
* **测试截图**:
  ![GET详情测试](https://github.com/user-attachments/assets/236c3907-f263-4392-ba6c-38fc4eaf0a97)

### 4. 更新与删除 (PUT & DELETE)
* **功能**: 实现信息的动态修改与安全移除。
* **测试截图**:
  ![更新测试](https://github.com/user-attachments/assets/f6cc6af7-afc3-431f-b157-6e7abcfe8a37)
  ![更新测试](https://github.com/user-attachments/assets/fd2300c9-b399-4567-9b73-d64cf49ea8a9)
  ![删除测试](https://github.com/user-attachments/assets/3aecd1e7-7f56-4fdf-a002-c20ce9c4fe97)
  ![删除测试](https://github.com/user-attachments/assets/f86bdaaa-e9f4-4338-8ac9-4de43d701ec0)
## 🚀 如何运行 (How to Run)

1.  克隆本项目到本地。
2.  在 MySQL 中执行 `src/main/resources` 下的建表语句。
3.  修改 `application.yml` 中的数据库账号密码。
4.  运行 `CropManagementApplication.java`。
5.  访问 Swagger UI 进行在线测试: `http://localhost:8080/swagger-ui.html`。

---
*Created by [你的名字] @ 2026.03.21*