import Vue from 'vue'
import Antd from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import store from './store'
// qs字符串解析和序列化字符串的库
import qs from 'qs'

// Lemon-IMUI 基于 VUE 2.0 的 IM 聊天组件
import IMUI from 'lemon-imui'
import 'lemon-imui/dist/index.css'

Vue.config.productionTip = false

// 全局注册,针对Vue编写的插件用Vue use导入,不是针对Vue编写的插件用Vue prototype导入
Vue.prototype.$qs = qs
Vue.use(IMUI)
Vue.use(Antd)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
