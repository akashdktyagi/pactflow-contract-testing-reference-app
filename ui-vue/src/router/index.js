import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/components/Home.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/employee',
      name: 'employee',
      component: () => import('@/components/Employee.vue')
    }
  ]
})

export default router
