import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/login/reset', component: () => import('@/views/login/reset'), hidden: true },
  { path: '/register/admin', component: () => import('@/views/register/admin/index'), hidden: true },
  { path: '/register/employee', component: () => import('@/views/register/employee/index'), hidden: true },
  { path: '/register/employer', component: () => import('@/views/register/employer/index'), hidden: true },
  { path: '/register/employee/next', component: () => import('@/views/register/employee/next'), hidden: true },
  { path: '/register/employer/next', component: () => import('@/views/register/employer/next'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/job',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Job',
        component: () => import('@/views/employee/job/index'),
        meta: { title: 'Job', icon: 'table', userType: 2 }
      }
    ]
  },
  {
    path: '/offer',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Offer',
        component: () => import('@/views/employee/offer/index'),
        meta: { title: 'Offer', icon: 'table', userType: 2 }
      }
    ]
  },
  {
    path: '/ee_profile',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Profile',
        component: () => import('@/views/employee/profile/index'),
        meta: { title: 'Profile', icon: 'table', userType: 2 }
      }
    ]
  },
  {
    path: '/ee_notification',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Notification',
        component: () => import('@/views/employee/notification/index'),
        meta: { title: 'Notification', icon: 'table', userType: 2 }
      }
    ]
  },
  {
    path: '/myjob',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Job',
        component: () => import('@/views/employer/job/index'),
        meta: { title: 'Job', icon: 'form', userType: 1 }
      }
    ]
  },
  {
    path: '/applicants',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Applicants',
        component: () => import('@/views/employer/applicants/index'),
        meta: { title: 'Applicants', icon: 'form', userType: 1 }
      }
    ]
  },
  {
    path: '/er_profile',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Profile',
        component: () => import('@/views/employer/profile/index'),
        meta: { title: 'Profile', icon: 'form', userType: 1 }
      }
    ]
  },
  {
    path: '/er_notification',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Notification',
        component: () => import('@/views/employer/notification/index'),
        meta: { title: 'Notification', icon: 'form', userType: 1 }
      }
    ]
  },
  {
    path: '/ad_employee',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Employee',
        component: () => import('@/views/admin/employee/index'),
        meta: { title: 'Employee', icon: 'tree', userType: 0 }
      }
    ]
  },
  {
    path: '/ad_employer',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Employer',
        component: () => import('@/views/admin/employer/index'),
        meta: { title: 'Employer', icon: 'tree', userType: 0 }
      }
    ]
  },
  {
    path: '/ad_notification',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Notification',
        component: () => import('@/views/admin/notification/index'),
        meta: { title: 'Notification', icon: 'tree', userType: 0 }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]
