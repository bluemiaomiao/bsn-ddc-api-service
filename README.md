# BSN DDC API SERVICE

用于将 BSN DDC SDK 转化为可伸缩的微服务。

# 接入开发

- OpenAPI 数据：`/docs`
- OpenAPI 调试：`/swagger-ui/index.html`

# 运行环境

- Oracle Java Development Kit 1.8.x
- 容器化支持（可选的）
- Systemd（推荐的）

# 部署

- 安装 JDK 环境
- 打包源代码
- 直接使用预编译的 jar 文件
- 拷贝服务单元
- 配置服务单元
- 启动
- 业务可用性检测（健康检查）

你也可以直接使用容器启动, 并将日志和配置文件挂载到本地, 例如：

```shell
docker build -t bsn-ddc-api:1.0.0 -f Dockerfile . # 构建容器镜像
```