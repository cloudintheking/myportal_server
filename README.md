# myportal_server
**门户网站服务端**


## 项目结构
- backsystem: cms系统模块，提供门户所需的restful API，同时也是rabbitmq生产者模块
- filesystem: 文件系统模块，提供文件上传下载的restful API
- base: 基础模块，提供公共基类、接口、工具等
- rabbitmq-common: rabbitmq的公共配置模块
- rabbitmq-consumer: rabbitmq消费者模块

## 环境（windows）
- jdk 1.8.0_181
- erlang 20.1
- rabbitmq 3.7.3
- mongodb 3.6
- maven 3.5.4

>上述环境请确保你的系统中都安装了。
>>如果上述环境你一个也不想装😥，但你装了`docker`，ok，打开终端进入工程根目录下，run `docker-compose up`,愉快玩耍吧😀

## 运行
如果环境都安装无误的话

>打开[http://http://localhost:8881/swagger-ui.html#](http://localhost:8881/swagger-ui.html#),查看cms系统API文档

>打开[http://http://localhost:8882/swagger-ui.html#](http://localhost:8882/swagger-ui.html#),查看文件系统API文档

>rabbitmaq的消息打印在console里，暂时只写了用户注册队列。

>项目使用jasypt加密配置文件,`application.yml`中参数`jasypt.encryptor.password`，可以看到值是cross，容易泄露。

>为了防止salt(盐)泄露,反解出密码.可以在项目部署的时候使用命令传入salt(盐)值

>`java -jar -Djasypt.encryptor.password=xxxxx yyyy.jar`


## 后续
最近忙着刷题，以后有时间我会把shiro也整合进来
