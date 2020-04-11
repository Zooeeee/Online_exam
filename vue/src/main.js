import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入 element ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'

Vue.config.productionTip = false
// 引入 element ui
Vue.use(ElementUI)
Vue.prototype.axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
