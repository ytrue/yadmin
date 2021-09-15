// 视图组件
const view = {
    tabs: () => import('@/layouts/tabs'),
    blank: () => import('@/layouts/BlankView')
}

// 路由组件注册
const routerMap = {
    login: {
        authority: '*',
        path: '/login',
        component: () => import('@/views/login')
    },
    root: {
        path: '/',
        name: '首页',
        redirect: '/login',
        component: view.tabs
    },

    //系统--------------------------------------------------start
    system: {
        path: 'system',
        icon: 'setting',
        name: '系统',
        component: view.blank
    },
        system_auth: {
            path: 'auth',
            icon: 'lock',
            name: '权限',
            component: view.blank,
            redirect: '/system/auth/admin'
        },
            system_auth_admin: {
                path: 'admin',
                icon: 'user',
                name: '管理员',
                component: () => import('@/views/system/auth/admin')
            },
            system_auth_role: {
                path: 'role',
                icon: 'team',
                name: '角色',
                component: () => import('@/views/system/auth/role')
            },
            system_auth_menu: {
                path: 'menu',
                icon: 'menu',
                name: '菜单',
                component: () => import('@/views/system/auth/menu')
            },

            system_auth_log: {
                path: 'log',
                icon: 'solution',
                name: '操作日志',
                component: () => import('@/views/system/auth/log')
            },

            system_auth_test: {
                path: 'test',
                icon: 'solution',
                name: '测试',
                component: () => import('@/views/system/auth/test')
            },

        system_files: {
            path: 'files',
            icon: 'folder',
            name: '文件库',
            component: view.blank,
            redirect: '/system/files/file'
        },
            system_files_file: {
                    path: 'file',
                    icon: 'file-image',
                    name: '文件列表',
                    component: () => import('@/views/system/files/file')
                },
            system_files_group: {
                    path: 'group',
                    icon: 'folder-open',
                    name: '文件分组',
                    component: () => import('@/views/system/files/group')
                },
            system_files_setting: {
                path: 'setting',
                icon: 'setting',
                name: '上传设置',
                component: () => import('@/views/system/files/setting')
            },

        system_monitor: {
            path: 'monitor',
            icon: 'eye',
            name: '监控',
            component: view.blank,
            redirect: '/system/monitor/quartz'
        },
            system_monitor_quartz: {
                path: 'quartz',
                icon: 'history',
                name: '定时任务',
                component: () => import('@/views/system/monitor/quartz')
            },
            system_monitor_application: {
                path: 'application',
                icon: 'appstore',
                name: '应用监控',
                component: () => import('@/views/system/monitor/application')
            },
    //系统--------------------------------------------------end

    //商城--------------------------------------------------start
    mall: {
        path: 'mall',
        name: '商城',
        component: view.blank
    },
        mall_product: {
            path: 'product',
            name: '商品管理',
            component: view.blank,
        },
            mall_product_goods: {
                path: 'goods',
                name: '商品列表',
                component: () => import('@/views/mall/product/goods')
            },
            mall_product_category: {
                path: 'category',
                name: '商品分类',
                component: () => import('@/views/mall/product/category')
            },
            mall_product_promise: {
                path: 'promise',
                name: '商品承诺',
                component: () => import('@/views/mall/product/promise')
            },
            mall_product_comment: {
                path: 'comment',
                name: '商品评论',
                component: () => import('@/views/mall/product/comment')
            },

        mall_delivery: {
            path: 'setting',
            name: '配送设置',
            component: view.blank,
        },
            mall_delivery_template: {
                path: 'delivery',
                name: '配送模板',
                component: () => import('@/views/mall/delivery/template')
            },
            mall_delivery_express: {
                path: 'delivery',
                name: '物流公司',
                component: () => import('@/views/mall/delivery/express')
            },
    //商城--------------------------------------------------end


    form: {
        name: '表单页',
        icon: 'form',
        component: view.blank
    },
    basicForm: {
        path: 'basic',
        name: '基础表单',
        component: () => import('@/views/form/basic')
    },
    //结束

    //异常页面，这个要保留
    exp403: {
        authority: '*',
        name: 'exp403',
        path: '403',
        component: () => import('@/views/exception/403')
    },
    exp404: {
        name: 'exp404',
        path: '404',
        component: () => import('@/views/exception/404')
    }
}
export default routerMap

