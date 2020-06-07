// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import axios from 'axios'
import qs from 'qs'
import XLSX from 'xlsx'
Vue.prototype.qs=qs 
Vue.prototype.XLSX=XLSX 
axios.defaults.baseURL="http://127.0.0.1:8080"
axios.defaults.timeout=5000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
//修改原型链，全局使用axios,这样之后可在每个组件的methods中调用$axios命令完成数据请求
Vue.prototype.axios=axios 


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import { Message } from 'element-ui';
Vue.prototype.$message=Message 

Vue.use(ElementUI);
Vue.use(iView)

// Vue.use(iView);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
