<template>
  <div>
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
      style="margin-top: 150px"
    >
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Password">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item id="centralise">
        <el-button type="primary" @click="onSubmit" id="login-button"
          >Login</el-button
        >
        <el-button id="reset-button" @click="reset">Cancel</el-button>
        <p>
          No account? Sign up
          <a href="/signup" style="text-decoration: none; color: #df4354"
            >here</a
          >
        </p>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    onSubmit() {
      axios
        .post('http://localhost:8080/api/auth/signin', {
          username: this.form.username,
          password: this.form.password
        })
        .then((response) => {
          localStorage.setItem('user', JSON.stringify(response.data))
          const userDetails = JSON.parse(localStorage.getItem('user'))
          axios
            .post('http://localhost:8080/api/test/user/userLogin', {
              employeeID: userDetails.id,
              name: userDetails.username,
              email: userDetails.email,
              contactNum: userDetails.contactNum,
              password: 'no password'
            })
            .then((response) => {
              if (response.data.slice(0, 1) === '1') {
                localStorage.setItem('role', null)
                this.$message({
                  message: 'Your account has been disabled',
                  type: 'error'
                })
              } else {
                console.log(response.data)
                console.log(userDetails.id)
                axios
                  .get('http://localhost:8080/api/test/user/getAdminStatus/' + userDetails.id)
                  .then((response) => {
                    localStorage.setItem('role', response.data)
                    this.$message({
                      message: 'Successfully logged in!',
                      type: 'success'
                    })
                    console.log(response.data)
                    if (response.data === 1) {
                      this.$router.push('/borrower-loan-pass')
                    } else {
                      this.$router.push('/')
                    }
                  })
                  .catch((error) => {
                    console.log(error.message)
                  })
              }
            })
            .catch((error) => {
              console.log(error.message)
              this.$message({
                message: 'Please try again',
                type: 'error'
              })
            })
        })
        .catch((error) => {
          console.log(error.message)
          this.$message({
            message: 'Your credentials are incorrect',
            type: 'error'
          })
        })
      // const userDetails = localStorage.getItem('user')
      // axios.post('http://localhost:8080/api/test/user/userLogin', {
      //   employeeID: 'heelo',
      //   name: 'ds',
      //   email: 'fds',
      //   contactNum: 'fds',
      //   password: 'no password'
      // })
      //   .then(response => {
      //     console.log(response.data)
      //   })
      //   .catch(error => {
      //     console.log(error.message)
      //   })
      setTimeout(location.reload.bind(location), 500)
    },
    reset() {
      this.form.username = ''
      this.form.password = ''
    }
  }
}
</script>
<style>
#login-button {
  background-color: #df4354;
  border-color: #df4354;
}
#login-button:hover {
  opacity: 80%;
}
#centralise {
  margin-left: 10%;
}
#reset-button:hover {
  background-color: rgba(223, 67, 84, 0.1);
  border-color: rgba(223, 67, 84, 0.1);
  color: #606266;
}
</style>
