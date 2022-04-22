# 1.简介

yadmin 基于springboot 开发后台

# 2. 目录结构

```
yadmin-ui									# yadmin前端

yadmin-server								# 后端代码
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│
├── yadmin-common                            # 公共库
│   └── yadmin-common-core                   # 基础库
│   └── yadmin-common-db                     # 存放dao和model
│  
├── yadmin-tools                             # 工具库此模块
│   └── yadmin-tools-document                # knife4j文档整合             
│   └── yadmin-tools-security                # spring security + jwt 安全
```
