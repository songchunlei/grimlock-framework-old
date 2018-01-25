FROM java
# 将当前目录下的*.jar添加到镜像中去，并且命名为app.jar
ADD *.jar app.jar
# 开放tcp端口
EXPOSE 	8761/tcp
# 镜像启动的时候执行这个命令
CMD exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar