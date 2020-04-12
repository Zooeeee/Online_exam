import Vue from 'vue'
import VueRouter from 'vue-router'
import NotFound from '@/views/NotFound.vue'
import Login from '@/views/Login.vue'
import Exam from '@/views/Exam.vue'
import User from '@/views/User.vue'
import Result from '@/views/Result.vue'
import Problem from '@/views/Problem.vue'
import TakeExam from '@/views/TakeExam.vue'
import Home from '@/views/Home.vue'
import ExamDetail from '@/views/PaperSheet/ExamDetail.vue'
import SeePaper from '@/views/Result/SeePaper.vue'
import Main from '@/views/Main.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: Home,
    meta: ['在线考试系统', '首页', '详情']
  },
  {
    path: '/main',
    name: 'main',
    component: Main,
    children: [
      {
        path: '/exam',
        name: 'exam',
        component: Exam,
        meta: ['在线考试系统', '考试管理', '详情']
      },
      // {
      //   path: '/home',
      //   name: 'home',
      //   component: Home,
      //   meta: ['在线考试系统', '首页', '详情']
      // },
      {
        path: '/result',
        name: 'result',
        component: Result,
        meta: ['在线考试系统', '试卷管理', '详情']
      },
      {
        path: '/user',
        name: 'user',
        component: User,
        meta: ['在线考试系统', '个人设置', '详情']
      },
      {
        path: '/problem',
        name: 'problem',
        component: Problem,
        meta: ['在线考试系统', '问题管理', '详情']
      },
      {
        path: '/TakeExam',
        name: 'takeList',
        component: TakeExam,
        meta: ['在线考试系统', '参加考试', '详情']
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/404',
    name: 'notFound',
    component: NotFound
  },
  {
    path: '/examDetail',
    name: 'examDetail',
    component: ExamDetail
  },
  {
    path: '/seePaper',
    name: 'seePaper',
    component: SeePaper
  },
  {
    path: '*', // 此处需特别注意置于最底部
    redirect: {
      name: 'notFound'
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    const token = sessionStorage.getItem('Authorization')
    if (token === null || token === '') {
      next('/login')
    } else {
      // 根据权限限定路由，防止其通过路由进入页面
      const powerStr = window.sessionStorage.getItem('power')
      const powerArr = powerStr.split(',')
      const path = to.path.substr(1)
      // 所有人都能进去的
      if (['home', '404', 'user', 'login', 'result', 'seePaper'].includes(path)) {
        next()
      } else {
        if (powerArr.includes(path)) {
          next()
        } else {
          next('/404')
        }
        if (powerArr.includes(to.path.substring(1))) {
          next()
        } else {
          next('/404')
        }
      }
    }
  }
})

export default router
