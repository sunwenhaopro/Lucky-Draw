import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import China from '@/views/China.vue'
import AL from '@/views/AL.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path:'/china',
      name:'china',
      component:China
    }
   
  ]
})

export default router
