# ===== 变量定义 =====

# 项目根目录
PROJECT_ROOT := $(shell pwd)

# 镜像仓库地址
REGISTRY := ccr.ccs.tencentyun.com
# 项目名称/命名空间
PROJECT_NAMESPACE := ns_newhker
# 服务名称
SERVICE_NAME := newhker

# 镜像版本（可通过命令行覆盖：make docker-build VERSION=v2.0）
VERSION ?= v1.0

# 默认构建环境
# ENVIRONMENT ?= prod

# Maven 可执行文件路径（优先使用系统 mvn，如果找不到则使用 IntelliJ 的）
MAVEN := $(shell which mvn 2>/dev/null || echo "/Applications/IntelliJ\ IDEA.app/Contents/plugins/maven/lib/maven3/bin/mvn")

# 完整镜像名称
IMAGE_NAME := $(REGISTRY)/$(PROJECT_NAMESPACE)/$(SERVICE_NAME):$(VERSION)

# Docker 登录凭证
DOCKER_USERNAME := 100031301327
DOCKER_PASSWORD := 123qweasdzxc

# ===== 主要命令 =====
.PHONY: all help clean package docker-login docker-build rm-image docker-push

# 默认目标：显示帮助
all: help

# 显示帮助信息
help:
	@echo "=========================================="
	@echo "  NewHKer 项目 Makefile 命令"
	@echo "=========================================="
	@echo ""
	@echo "构建命令:"
	@echo "  make package        - 打包 JAR 文件"
	@echo ""
	@echo "Docker 镜像命令:"
	@echo "  make docker-login   - 登录 Docker 镜像仓库"
	@echo "  make docker-build   - 构建 Docker 镜像（默认版本: $(VERSION)）"
	@echo "  make docker-push    - 推送镜像到仓库"
	@echo "  make rm-image       - 删除本地镜像"
	@echo "=========================================="

# 清理编译产物
clean:
	@echo "===== 清理编译产物 ====="
	$(MAVEN) clean
# 	@rm -f ./newhker-server/target/newhker-server.jar
	@echo "清理完成"

# 打包 JAR 文件
package:
	@echo "===== 打包 JAR 文件 ====="
	$(MAVEN) clean package -DskipTests
	@echo "复制 JAR 文件到项目根目录..."
	@cp $(PROJECT_ROOT)/nhk-start/target/nhk-start-1.0.0.jar .
	@echo "打包完成: nhk-start-1.0.0.jar"

# ===== Docker 镜像命令 =====

# 登录 Docker 镜像仓库
docker-login:
	@echo "===== 登录 Docker 镜像仓库 ====="
	@echo "仓库地址: $(REGISTRY)"
	@echo "用户名: $(DOCKER_USERNAME)"
	@echo $(DOCKER_PASSWORD) | docker login $(REGISTRY) --username $(DOCKER_USERNAME) --password-stdin
	@echo "登录成功"

# 删除本地镜像
rm-image:
	@echo "===== 删除本地镜像 ====="
	-docker rmi $(IMAGE_NAME)

# 构建 Docker 镜像
docker-build: package rm-image
	@echo "===== 构建 Docker 镜像 ====="
	@echo "镜像名称: $(IMAGE_NAME)"
	docker build --no-cache \
		-t $(IMAGE_NAME) \
		-f $(PROJECT_ROOT)/devops/Dockerfile \
		.
	@echo "镜像构建完成: $(IMAGE_NAME)"

# 推送镜像到仓库（自动登录）
docker-push: docker-build
	@echo "===== 推送镜像到仓库 ====="
	docker push $(IMAGE_NAME)
	@echo "镜像推送完成: $(IMAGE_NAME)"