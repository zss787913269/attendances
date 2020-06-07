import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/home'
import Login from '@/components/login'
import myHome from '@/components/home/myHome'
import hr from '@/components/home/hr'
import attendance from '@/components/home/attendance'
import Information from '@/components/system/informationManagement'
import repassword from '@/components/system/repassword'
import absence from '@/components/system/absence'
import teamAttendance from '../components/home/teamAttendance.vue'
import retroactive from '../components/home/retroactive.vue'
import myleave from '../components/home/myleave.vue'
import contacts from '../components/home/contacts.vue'
import pcontacts from '../components/home/pcontacts.vue'
import approval from '../components/home/approval.vue'
import bussiness from '../components/home/bussiness.vue'
import notice from '../components/home/notice.vue'
import trip from '../components/home/trip'
import permissionSign from '../components/home/permissionSign'
import employeeNotice from '../components/home/employeeNotice'
Vue.use(Router)


export default new Router({
  mode: 'history',
  routes: [
    
    { path: '/',
     redirect: '/myHome'},
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      children:[
        {
          path: '/myHome',
          name: 'myHome',
          component: myHome
        } ,{
          path: '/hr',
          name: 'hr',
          component: hr
        },
        ,{
          path: '/permissionSign',
          name: 'permissionSign',
          component: permissionSign
        }
        ,{
          path: '/employeeNotice',
          name: 'employeeNotice',
          component: employeeNotice
        },
        {
          path: '/attendance',
          name: 'attendance',
          component: attendance
        },
        {
          path: '/trip',
          name: 'trip',
          component: trip
        },
        {
          path: '/notice',
          name: 'notice',
          component: notice
        },
        {
          path: '/bussiness',
          name: 'bussiness',
          component: bussiness
        }, 
        {
          path: '/contacts',
          name: 'contacts',
          component: contacts
        },
        {
          path: '/pcontacts',
          name: 'pcontacts',
          component: pcontacts
        }, 
        {
          path: '/teamAttendance',
          name: 'teamAttendance',
          component: teamAttendance
        }, 
        {
          path: '/approval',
          name: 'approval',
          component: approval
        }, 
        {
          path: '/retroactive',
          name: 'retroactive',
          component: retroactive
        }, 
        {
          path: '/myleave',
          name: 'myleave',
          component: myleave
        }, 
        {
          path: '/information',
          name: 'Information',
          component: Information
        },
          {
          path: '/repassword',
          name: 'repassword',
          component: repassword
        }, {
          path: '/absence',
          name: 'absence',
          component: absence
        },
      ]
    },
   
  ]
})
