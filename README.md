# myportal_server
**门户网站服务端**


##项目结构

- backsystem: cms系统模块，提供门户所需的restful API，同时也是rabbitmq生产者模块
- filesystem: 文件系统模块，提供文件上传下载的restful API
- base: 基础模块，提供公共基类、接口、工具等
- rabbitmq-common: rabbitmq的公共配置模块
- rabbitmq-consumer: rabbitmq消费者模块

##环境

- windows 10
- jdk 1.8.0_181
- erlang 20.1
- rabbitmq 3.7.3
- mongodb 3.6
- maven 3.5.4
>环境可以跟我的不一样，但一定得都有，否则代码拉下来也运行不了。

##运行
如果环境都安装无误的话

>打开[http://http://localhost:8881/swagger-ui.html#](http://localhost:8881/swagger-ui.html#),查看cms系统API文档

>打开[http://http://localhost:8882/swagger-ui.html#](http://localhost:8882/swagger-ui.html#),查看文件系统API文档

>rabbitmaq的消息打印在console里，暂时只写了用户注册队列。

>另外项目使用jasypt加密了配置文件


##后续

最近忙着刷题，以后有时间我会把shiro也整合进来
