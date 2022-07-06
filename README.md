# 1.简介

yadmin 基于springboot 开发后台

# 2. 目录结构

```text
yadmin-server                                # 后端代码
├── yadmin-application
│   └── yadmin-application-admin             # 后台应用
│       └── modules
│           └── generator                    # 代码生成器
│           └── quartz                       # 定时任务
│           └── system                       # rbac

│   └── yadmin-application-monitor           # spring-boot-admin监控
│
├── yadmin-common                            # 公共库
│   └── yadmin-common-core                   # 基础库
│   └── yadmin-common-db                     # 存放dao和model
│  
├── yadmin-tools                             # 工具库-独立模块-该模块下大部分是启动器
│
│   └── yadmin-tools-document                # knife4j 文档整合             
│   └── yadmin-tools-security                # spring security + jwt 安全
│   └── yadmin-tools-log                     # 操作日志
│   └── yadmin-tools-mp-query                # mybatis-plus 条件查询
```
# 3.注意
修改mapstruct 衍射的类 maven 要 clean
