


# 1.说明
本代码是来自黑马教育的乐优商城系列
我学习的视频是2018年9月份年左右

主要的技术栈是：vue+Spring Boot+Spring Cloud+Redis+ RabbitMQ+Nginx+ElasticSearch+JWT+FastDFS

实现的功能：实现前后端分离，微服务开发，分布式文件管理，以及高效聚合功能，解决服务间通信问题，缓存热点数据，负载均衡，非对称加密等

#### 系统架构
![img](https://img-blog.csdnimg.cn/20181212215151153.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x5ajIwMThneXE=,size_16,color_FFFFFF,t_70)
#### 项目目录
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200429182434662.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI2NjI0MzI5,size_16,color_FFFFFF,t_70)

其中标记为黄色的（exclusion）的代码是前端代码

Leyou-portal:商城用户访问界面

Leyou-manage-web：商城管理页面


# 2.启动  
### 推荐学习视频：[黑马57期](https://www.bilibili.com/video/BV14E411i7rc?from=search&seid=15891790378128927636)

### 启动顺序
#### linux下nginx+elasticsearch+MQ

```python
#nginx
sudo nginx -c /opt/nginx/conf/nginx.conf #指定配置文件启动
#elasticsearch
/leyou/home/elasticsearch-6/bin/./elasticsearch
#MQ
service rabbitmq-server start    # 启动
```
#### win下
打开redis-server+mysql+idea
```python
#切换到Leyou-portal目录（启动用户界面）
live-server --port=9002
#切换到Leyou-manage-web目录（启动管理界面）
npm run dev
#MQ
再依次启动微服务即可完成
```


# 3.注意
- 没有实现sms短信发送
- 没有实现fastdfs，直接保存到本地
- 由于页面静态化的代码在win中运行，所以直接保存到的win
- cookie的domin设置，直接返回的leyou.com，没有配置nginx
- port文件是对应的端口号
# 关于作者
[个人博客网站](http://www.bothsavage.club/)
[个人GitHub地址](https://github.com/bothsavage)
个人公众号：
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL21tYml6LnFwaWMuY24vbW1iaXpfcG5nL2Y3UEk3eWZpYTNTZUI1MkFDZ1NKdE4yNGljY3VOMmhYbTBsZG1IUHhFREU2SmNCSEkydEFyeHVjMnppYjNxQnJ4bkVtb2lidGJLSG5zU3ZWNjRQblVYUzRpY0EvMA?x-oss-process=image/format,png)   
