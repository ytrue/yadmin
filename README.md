# 1.简介

yadmin 基于springboot 开发后台

# 2. 目录结构

```
yadmin       			                     					
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│
├── yadmin-common                            # 公共库
│   └── yadmin-common-core                   # 基础库
│   └── yadmin-common-db                     # 存放dao和model
│  
├── yadmin-tools                             # 工具库此模块下大部分是启动器
│   └── yadmin-tools-document                # knife4j文档整合             
│   └── yadmin-tools-security                # oauth2 + jwt+ spring security 安全

```
