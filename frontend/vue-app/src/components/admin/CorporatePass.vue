<template>
  <div v-if="accessible">
    <el-table
      :data="allCoporatePasses"
      style="width: 100%"
      border
      :default-sort="{prop: 'memberObject.name', order: ''}"
    >
      <el-table-column fixed prop="cardNumber" label="Card Number">
      </el-table-column>
      <el-table-column fixed prop="memberObject.name" label="Membership">
      </el-table-column>
      <el-table-column fixed prop="memberObject.poi" label="Place of Interest">
      </el-table-column>
      <el-table-column fixed prop="cardType" label="Pass Type">
      </el-table-column>
      <el-table-column
        fixed
        prop="cardStatus"
        label="Status"
        :formatter="formatterStatus"
      >
      </el-table-column>
      <el-table-column
        fixed
        prop="replacementFee"
        label="Replacement Fee"
        :formatter="formatter"
      >
      </el-table-column>
      <el-table-column fixed prop="cardStatus" label="Operations">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            v-if="scope.row.cardStatus === 1"
            @click="handleLost(scope.$index, scope.row)"
            >Mark as lost</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 1rem; margin-bottom: 2rem">
      <el-button
        text
        @click="dialogFormVisible = !dialogFormVisible"
        style="float: right; margin-right: 3rem; margin-bottom: 1rem"
      >
        Create a new corporate pass
      </el-button>
    </div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="150px"
      class="demo-ruleForm"
      v-show="dialogFormVisible"
    >
      <el-form-item label="Card Number" prop="cardNumber">
        <el-input v-model="ruleForm.cardNumber"></el-input>
      </el-form-item>
      <!-- <el-form-item label="Membership" prop="placeInterest">
        <el-select v-model="ruleForm.Membership" placeholder="Membership">
          <el-option
            label="Mandai Wildlife Reserve"
            value="Mandai Wildlife Reserve"
          ></el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item label="Place of Interest" prop="placeInterest">
        <el-select
          v-model="ruleForm.placeInterest"
          placeholder="Place of Interest"
        >
          <el-option v-for="POI in allPOI" :key="POI" :label="POI" :value="POI">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Pass Type" prop="cardType">
        <el-radio-group v-model="ruleForm.cardType">
          <el-radio label="Electronic" value="Electronic"></el-radio>
          <el-radio label="Physical" value="Physical"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Replacement Fee" prop="replacementFee">
        <el-input-number
          v-model="ruleForm.replacementFee"
          :controls="false"
        ></el-input-number>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >Create</el-button
        >
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      dialogFormVisible: false,
      allCoporatePasses: [],
      allPOI: [],
      ruleForm: {
        cardNumber: '',
        placeInterest: '',
        cardType: '',
        replacementFee: undefined
      },
      rules: {
        cardNumber: [
          {
            required: true,
            message: 'Please input card number',
            trigger: 'blur'
          }
        ],
        placeInterest: [
          {
            required: true,
            message: 'Please select place of interest',
            trigger: 'change'
          }
        ],
        cardType: [
          {
            required: true,
            message: 'Please select pass type',
            trigger: 'change'
          }
        ],
        replacementFee: [
          {
            required: true,
            message: 'Please input replacement fee',
            trigger: 'blur'
          },
          {
            type: 'number',
            required: true,
            message: 'Please input a number',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/api/test/user/allCorporatePass')
      .then((response) => {
        this.allCoporatePasses = response.data
        // console.log(this.allCoporatePasses)
      })
    axios.get('http://localhost:8080/api/test/user/allPOI').then((response) => {
      this.allPOI = response.data
      console.log(this.allPOI)
    })
  },
  methods: {
    formatter(row) {
      return '$' + row.replacementFee
    },
    formatterStatus(row) {
      if (row.cardStatus === 1) {
        return 'Active'
      } else {
        return 'Disabled'
      }
    },
    handleLost(index, row) {
      this.$confirm(
        'Are you sure you want to mark this pass as lost?',
        'Mark as lost',
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel'
        }
      )
        .then(({ value }) => {
          console.log(row)
          axios
            .put(
              'http://localhost:8080/api/test/user/cardLost/' + row.cardNumber,
              {}
            )
            .then((response) => {
              this.$message({
                type: 'success',
                message: 'Mark as lost successfully!'
              })
              window.location.reload()
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: 'Input canceled'
          })
        })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // call post api to create new corporate pass
          axios
            .post('http://localhost:8080/api/test/user/createCorporatePass', {
              cardNumber: this.ruleForm.cardNumber,
              placeInterest: this.ruleForm.placeInterest,
              cardType: this.ruleForm.cardType,
              replacementFee: this.ruleForm.replacementFee
            })
            .then((response) => {
              if (response.data.slice(0, 1) === 'S') {
                this.$message({
                  dangerouslyUseHTMLString: true,
                  type: 'success',
                  message: '<h2>' + response.data + '</h2>'
                })
                setTimeout(location.reload.bind(location), 1500)
              } else {
                this.$message({
                  dangerouslyUseHTMLString: true,
                  type: 'error',
                  message: '<h2>' + response.data + '</h2>'
                })
                setTimeout(location.reload.bind(location), 1500)
              }
            })
        } else {
          console.log('error submit!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  },
  created() {
    if (localStorage.getItem('role') === '2') {
      this.accessible = true
    }
  }
}
</script>

<style></style>
