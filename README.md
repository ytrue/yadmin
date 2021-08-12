# 1.简介
此项目开发中, 变化比较大,不稳定, 看看就好....

yadmin 基于springboot 开发后台

[后台基于 Ant Design Vue  实现 ](https://github.com/ytrue/yadmin-web)

# 2. 目录结构

```
yadmin
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│   └── yadmin-application-api               # api应用
│   └── yadmin-application-monitor           # spring boot admin 监控
│
├── yadmin-common                            # 公共库
│   └── yadmin-common-core                   # 基础库,tools都又依赖这个库
│   └── yadmin-common-db                     # 存放dao和实体
│  
├── yadmin-tools                             # 工具库此模块下都是启动器
│   └── yadmin-tools-document                # knife4j文档整合             
│   └── yadmin-tools-log                     # 操作日志库
│   └── yadmin-tools-oss                     # aliyun,qcloud,qiniu,本地 上传（目前只有上传）
│   └── yadmin-tools-security                # oauth2 + jwt+ spring security 安全
│   └── yamin-tools-xss                      # xss过滤
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
