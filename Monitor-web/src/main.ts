import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import vue3SeamlessScroll from "vue3-seamless-scroll";
const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.use(vue3SeamlessScroll)
app.mount('#app')
