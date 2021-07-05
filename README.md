# 1.简介
此项目开发中, 变化比较大,不稳定, 看看就好....

yadmin 基于springboot 开发后台

[后台基于 Ant Design Vue  hi实现 ](https://github.com/ytrue/yadmin-web)

# 2. 目录结构

```
yadmin
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│   └── yadmin-application-api               # api应用
│   └── yadmin-application-monitor           # spring boot admin 监控
│   
├── yadmin-db                # 实体类和dao文件
│
├── yadmin-common            # 公共库
│  
├── yadmin-tools                             # 工具库此模块下都是启动器
│   └── yamin-tools-document                 # knife4j文档整合             
│   └── yamin-tools-log                      # 操作日志库
│   └── yamin-tools-oss                      # aliyun,qcloud,qiniu,本地 上传（目前只有上传）
│   └── yamin-tools-security                 # oauth2 + jwt+ spring security 安全
│   └── yamin-tools-xss                      # xss过滤
```

# 3.截图

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-01.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-02.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-03.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-04.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-05.png)

![](https://php-yangyi-images.oss-cn-shenzhen.aliyuncs.com/mydocs/yadmin-06.png)
