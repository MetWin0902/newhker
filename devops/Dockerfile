# 使用 openJDK 21 作为基础镜像
FROM openjdk:21-jdk

LABEL maintainer="Heary"

WORKDIR /app

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN echo 'Asia/Shanghai' >/etc/timezone

COPY nhk-start-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "nhk-start-1.0.0.jar"]