import Vue from 'vue'
import VueRouter from 'vue-router'
import { checkAuthorization } from '@/utils/request'
import { message } from 'ant-design-vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Test')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },
  {
    path: '*',
    name: '404',
    component: () => import('@/views/exception/404')
  }
]

const router = new VueRouter({
  routes
})

/**
 * 不需要登录拦截的路由配置
 * @type {{names: string[], paths: string[], includes(*): boolean}}
 */
const loginIgnore = {
  names: ['404', '403'], // 根据路由名称匹配
  paths: ['/login'], // 根据路由fullPath匹配
  /**
   * 判断路由是否包含在该配置中
   * @param route vue-router 的 route 对象
   * @returns {boolean}
   */
  includes (route) {
    return this.names.includes(route.name) || this.paths.includes(route.path)
  }
}

/**
 * 登录守卫
 */
router.beforeEach((to, from, next) => {
  if (!loginIgnore.includes(to) && !checkAuthorization()) {
    message.warning('登录已失效，请重新登录')
    next({ path: '/login' })
  } else {
    next()
  }
})

export default router
