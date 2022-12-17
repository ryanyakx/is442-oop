<template>
  <div id="app">
    <el-row>
      <div id="nav">
        <el-menu
          class="el-menu-demo"
          mode="horizontal"
          background-color="white"
          text-color="black"
          active-text-color="#606266"
          router
        >
          <el-menu-item
            ><div class="logo">
              <img src="./assets/SSS Logo.png" width="50" height="50"> Corporate Pass Application
            </div></el-menu-item
          >
          <el-menu-item
            v-if="!loginVisible"
            index="/login"
            class="dock-right"
            style="font-family: Seoul Hangang"
            @click="loginOn()"
            >Log Out</el-menu-item
          >
          <el-menu-item
            index="/config"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Config</el-menu-item
          >
          <el-menu-item
            index="/borrower"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Borrower</el-menu-item
          >
          <el-menu-item
            index="/corporate-pass"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Corporate Pass</el-menu-item
          >
          <el-menu-item
            index="/membership"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Memberships</el-menu-item
          >
          <el-menu-item
            index="/admin-loan-records"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Loan Records</el-menu-item
          >
          <el-menu-item
            index="/loan-records-himself"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Admin's loan Records
          </el-menu-item>
          <el-menu-item
            index="/"
            class="dock-right"
            v-if="adminVisible && !loginVisible"
            >Loan Pass</el-menu-item
          >
          <el-menu-item
            index="/borrower-loan-records"
            class="dock-right"
            v-if="borrowerVisible && !loginVisible"
            >Loan Records</el-menu-item
          >
          <el-menu-item
            index="/borrower-loan-pass"
            class="dock-right"
            v-if="borrowerVisible && !loginVisible"
            >Loan Pass</el-menu-item
          >
          <el-menu-item index="/login" class="dock-right" v-if="loginVisible"
            >Login</el-menu-item
          >
        </el-menu>
      </div>
      <router-view />
    </el-row>
  </div>
</template>

<script>
export default {
  created() {
    console.log('vue app starting')
    this.borrowerOn()
    this.adminOn()
  },
  data() {
    return {
      adminVisible: false,
      borrowerVisible: false,
      loginVisible: true
    }
  },
  methods: {
    adminOn() {
      if (localStorage.getItem('role') === '2') {
        this.adminVisible = true
        this.borrowerVisible = false
        this.loginVisible = false
      }
    },
    borrowerOn() {
      if (localStorage.getItem('role') === '1') {
        this.adminVisible = false
        this.borrowerVisible = true
        this.loginVisible = false
      }
    },
    loginOn() {
      localStorage.clear()
      this.adminVisible = false
      this.borrowerVisible = false
      this.loginVisible = true
    }
  }
}
</script>

<style lang="scss">
@font-face {
  font-family: 'Seoul Hangang';
  font-style: normal;
  font-weight: 400;
  src: url('https://cdn.jsdelivr.net/gh/jbfactory/jb-skin-fonts/fonts/seoulhangangjungm.woff2')
      format('woff2'),
    url('https://cdn.jsdelivr.net/gh/jbfactory/jb-skin-fonts/fonts/seoulhangangjungm.woff')
      format('woff');
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  font-size: 16px;
  margin: auto;
}

#nav {
  position: sticky;
  top: 0px;
  z-index: 10;
}

.el-menu-item.is-active {
  background-color: #df4354 !important;
  color: white !important;
}

.el-menu--horizontal > .el-menu-item.dock-right {
  float: right;
  font-family: 'Seoul Hangang';
}
.el-menu--horizontal > .el-submenu.dock-right {
  float: right;
  font-family: 'Seoul Hangang';
}

i.el-icon-printer {
  color: #df4354 !important;
  // standardized color
  font-size: 30px !important;
}

.logo {
  font-size: 30px !important;
}
</style>
