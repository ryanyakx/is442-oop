<template>
<el-container v-if="accessible">
  <el-header>
    <h1>Max Loans Per Month: {{maxLoans}}</h1>
  </el-header>

  <el-main>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
  <el-form-item label="Change Max Loan Limit">
    <el-input-number v-model="formInline.num" :controls="false" ></el-input-number>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">Submit</el-button>
  </el-form-item>
</el-form>
</el-main>
</el-container>
  </template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      maxLoans: 0,
      formInline: {
        num: 1
      }
    }
  },
  methods: {
    getLoans() {
      axios.get('http://localhost:8080/api/test/user/getMaxLoanPerMonth')
        .then(response => {
          this.maxLoans = response.data[0].maxLoanPerMonth
        })
        .catch(error => {
          console.log(error)
        })
    },
    onSubmit() {
      this.$confirm('Are you sure to change the max loan limit?', 'Change max loan', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        axios.post('http://localhost:8080/api/test/user/changeMaxLoan', {
          id: 1,
          max_loan: this.formInline.num
        })
          .then(response => {
            console.log(response)
            this.$message({
              type: 'success',
              message: 'Change max loan limit successfully!'
            })
            window.location.reload()
          })
          .catch(error => {
            console.log(error)
            this.$message({
              type: 'error',
              message: 'Change max loan limit failed!'
            })
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Change max loan limit canceled'
        })
      })
    }
  },
  mounted() {
    this.getLoans()
  },
  created() {
    if (localStorage.getItem('role') === '2') {
      this.accessible = true
    }
  }
}

</script>
