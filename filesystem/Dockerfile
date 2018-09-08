# 基础 JRE 镜像
FROM store/orcale/servejre:8
# 修改时区，使得容器时间为北京时间
RUN echo "Asia/Shanghai" > /etc/timezone

# 将所需项目文件复制进入容器中
WORKDIR /app
ADD ./target/filesystem-0.0.1-SNAPSHOT.jar /app

# 暴露容器的端口
EXPOSE 8082

# 运行项目
CMD ["java","-jar","-Dfile.encoding=UTF-8","base-0.0.1-SNAPSHOT.jar"]
