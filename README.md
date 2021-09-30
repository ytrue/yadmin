# 1.简介
此项目开发中,目前在添加商品模块，基本的后台功能已完成

yadmin 基于springboot 开发后台

vue后台基于ant design vue开发

# 2. 目录结构

```
yadmin-vue-chat                              # vue-聊天页面-暂未开发
yadmin-vue-kefu                              # vue-客服工作-暂未开发
yadmin-vue-admin                             # vue-总后台  -进行中

yadmin       			                     # java后台					
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│   └── yadmin-application-api               # api应用,主要是商城和客服api
│   └── yadmin-application-monitor           # spring boot admin 监控
│
├── yadmin-common                            # 公共库
│   └── yadmin-common-core                   # 基础库,tools都又依赖这个库
│   └── yadmin-common-db                     # 存放dao和实体
│  
├── yadmin-tools                             # 工具库此模块下大部分是启动器
│   └── yadmin-tools-cache                   # spring cache配置和增强可自定义过期时间
│   └── yadmin-tools-document                # knife4j文档整合             
│   └── yadmin-tools-log                     # 操作日志库
│   └── yadmin-tools-oss                     # 支持 阿里云,腾讯云,七牛云,本地 的文件存储
│   └── yadmin-tools-security                # oauth2 + jwt+ spring security 安全
│   └── yadmin-tools-xss                     # xss过滤
│   └── yadmin-tools-unified-dispose         # 统一包装响应和异常返回
```

# 3.截图

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/1.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/2.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/3.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/4.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/5.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/6.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/7.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/8.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/9.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/10.png)
