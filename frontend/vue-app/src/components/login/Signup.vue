<template>
  <div>
    <el-form ref="form" :model="form" label-width="250px" style="margin-top: 150px">
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Email">
            <el-input v-model="form.email"></el-input>
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
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Confirm Password">
            <el-input v-model="form.password2"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item label="Contact Number">
            <el-input v-model="form.contact"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="16" id="centralise">
          <el-form-item>
            <p id="error">{{error}}</p>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item id="centralise">
        <el-button type="primary" @click="onSubmit" id="login-button" :plain="true">Sign Up</el-button>
        <el-button @click="reset" id="reset-button">Reset</el-button>
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
        uername: '',
        email: '',
        password: '',
        password2: '',
        contact: ''
      },
      error: ''
    }
  },
  methods: {
    onSubmit() {
      const emailSource = this.form.email.split('@')[1]
      console.log(emailSource)
      if (this.form.password === this.form.password2 && (emailSource === 'sportsschool.edu.sg' || emailSource === 'nysi.org.sg') && this.form.password.length >= 8) {
        axios.post('http://localhost:8080/api/auth/signup', {
          username: this.form.username,
          email: this.form.email,
          password: this.form.password,
          contactNum: this.form.contact,
          role: ['user']
        })
          .then(response => {
            this.$message({
              message: response.data.message,
              type: 'success'
            })
            this.$router.push('/login')
          })
          .catch(error => {
            const e = error
            this.$message({
              message: 'Account with this email already exists!',
              type: 'error'
            })
          })
      } else if (this.form.password !== this.form.password2) {
        this.error = 'Your passwords do not match'
      } else if (this.form.password.length < 8) {
        this.error = 'Password length should be 8 or above!'
      } else {
        this.error = 'Please use a valid email'
      }
    },
    reset() {
      this.form.username = ''
      this.form.email = ''
      this.form.password = ''
      this.form.password2 = ''
      this.form.contact = ''
    }
  }
}
</script>
<style>
  #login-button{
    background-color: #df4354;
    border-color: #df4354;
    color: white;
  }
  #login-button:hover{
    opacity: 80%;
  }
  #centralise{
    margin-left:10%;
  }
  #reset-button:hover{
    background-color: rgba(223, 67, 84, 0.1);
    border-color: rgba(223, 67, 84, 0.1);
    color: #606266;
  }
  #error{
    color: rgba(223, 67, 85, 0.8);
    margin: 0px 0px;
  }
</style>
