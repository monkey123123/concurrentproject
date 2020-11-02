#下载java8的镜像
FROM java:8
#将本地文件挂到到/tmp目录
VOLUME /tmp
#复制文件到容器
ADD concurrent-system-2.1.jar /demo.jar
#暴露8080端口
EXPOSE 8080
#配置启动容器后执行的命令
ENTRYPOINT ["java","-jar","/demo.jar"]

#MAINTAINER yihj "yihj@yinghaikeji.com"

# 添加
#ADD concurrent-system-2.1.jar app.jar
#RUN bash -c 'touch /app.jar'
# Define default command.
#ENTRYPOINT ["java","-Dspring.profiles.active=online","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#设置时区
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone