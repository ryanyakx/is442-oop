import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'

import BorrowerLoanPass from '../components/borrower/LoanPass.vue'
import BorrowerLoanRecords from '../components/borrower/LoanRecords.vue'

import Borrower from '../components/admin/Borrower.vue'
import CorporatePass from '../components/admin/CorporatePass.vue'
import LoanRecordsHimself from '../components/admin/LoanRecordsHimself.vue'
import AdminLoanPass from '../components/admin/LoanPass.vue'
import AdminLoanRecords from '../components/admin/LoanRecords.vue'
import Membership from '../components/admin/Membership.vue'
import Config from '../components/admin/Config.vue'

import Login from '../components/login/Login.vue'
import Signup from '../components/login/Signup.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/borrower-loan-pass',
    name: 'BorrowerLoanPass',
    component: BorrowerLoanPass
  },
  {
    path: '/borrower-loan-records',
    name: 'BorrowerLoanRecords',
    component: BorrowerLoanRecords
  },
  {
    path: '/',
    name: 'AdminLoanPass',
    component: AdminLoanPass
  },
  {
    path: '/admin-loan-records',
    name: 'AdminLoanRecords',
    component: AdminLoanRecords
  },
  {
    path: '/loan-records-himself',
    name: 'LoanRecordsHimself',
    component: LoanRecordsHimself
  },
  {
    path: '/corporate-pass',
    name: 'CorporatePass',
    component: CorporatePass
  },
  {
    path: '/borrower',
    name: 'Borrower',
    component: Borrower
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/membership',
    name: 'Membership',
    component: Membership
  },
  {
    path: '/config',
    name: 'Config',
    component: Config
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
