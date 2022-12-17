<script>
import axios from 'axios'
export default {
  data() {
    return {
      dialogFormVisible: false,
      allMembership: [],
      ruleForm: {
        membershipID: '',
        name: '',
        placeOfInterest: '',
        maxPassPerLoan: undefined,
        status: 1
      },
      rules: {
        name: [
          {
            required: true,
            message: 'Please enter membership name',
            trigger: 'blur'
          }
        ],
        placeOfInterest: [
          {
            required: true,
            message: 'Please enter place of interest',
            trigger: 'blur'
          }
        ],
        maxPassPerLoan: [
          {
            required: true,
            message: 'Please enter maximum number of passes per loan',
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
      .get('http://localhost:8080/api/test/user/allMembership')
      .then((response) => {
        console.log(response.data[0])
        for (let i = 0; i < response.data.length; i++) {
          if (response.data[i].membershipStatus === 1) {
            this.allMembership.push(response.data[i])
          }
        }
        // this.allMembership = response.data
        // console.log(this.allMembership)
      })
  },
  methods: {
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // The index of the column we want to merge
      if (![0, 2, 3, 4].includes(columnIndex)) {
        return {
          rowspan: 1,
          colspan: 1
        }
      }

      const allMembership = this.allMembership
      const property = column.property
      const tableLen = allMembership.length
      let _row = 1
      const _col = 1
      const preRow = allMembership[rowIndex - 1] || {}

      if (
        row.name === preRow.name &&
        (property === 'name' || preRow[property] === row[property])
      ) {
        _row = 0
      } else if (rowIndex + 1 < tableLen) {
        for (let i = rowIndex + 1; i < tableLen; i++) {
          const nextRow = allMembership[i]
          if (
            nextRow.name === row.name &&
            (property === 'name' ||
              (nextRow.category === row.category &&
                row[property] === nextRow[property]))
          ) {
            _row++
          } else {
            break
          }
        }
      }

      return {
        rowspan: _row,
        colspan: _col
      }
    },
    formatter(row) {
      if (row.membershipStatus === 1) {
        return 'Active'
      } else {
        return 'Disabled'
      }
    },
    handleDisable(index, row) {
      console.log(index, row)
      this.$confirm(
        'Are you sure you to disable ' + row.name + '?',
        'Disable ' + row.name,
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }
      )
        .then(() => {
          // call put api to disable at localhost:8080/memberships, passing in the membership id
          axios
            .delete(
              'http://localhost:8080/api/test/user/disableMembership/' +
                row.name,
              {}
            )
            .then((response) => {
              console.log(response)
              this.$message({
                dangerouslyUseHTMLString: true,
                type: 'success',
                message: '<h2>Disable ' + row.name + ' successfully!</h2>'
              })
              setTimeout(location.reload.bind(location), 1500)
              // this.allMembership.splice(index, 1)
            })
            .catch((error) => {
              console.log(error)
              this.$message({
                dangerouslyUseHTMLString: true,
                type: 'error',
                message: '<h2>Disable ' + row.name + ' failed!</h2>'
              })
            })
        })
        .catch(() => {
          this.$message({
            dangerouslyUseHTMLString: true,
            type: 'info',
            message: '<h2>Disable canceled</h2>'
          })
        })
    },
    handleEnable(index, row) {
      console.log(index, row)
      this.$confirm(
        'Are you sure you to enable ' + row.name + '?',
        'Enable ' + row.name,
        {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }
      )
        .then(() => {
          axios
            .put(
              'http://localhost:8080/api/test/user/enableMembership/' +
                row.name,
              {
                status: 1
              }
            )
            .then((response) => {
              console.log(response)
              this.$message({
                dangerouslyUseHTMLString: true,
                type: 'success',
                message: '<h2>Enabled ' + row.name + ' successfully!</h2>'
              })
              setTimeout(location.reload.bind(location), 1500)
              // this.allMembership.splice(index, 1)
            })
            .catch((error) => {
              console.log(error)
              this.$message({
                dangerouslyUseHTMLString: true,
                type: 'error',
                message: '<h2>Enable ' + row.name + ' failed!</h2>'
              })
            })
        })
        .catch(() => {
          this.$message({
            dangerouslyUseHTMLString: true,
            type: 'info',
            message: '<h2>Enable canceled</h2>'
          })
        })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // call post api to add at localhost:8080/memberships
          axios
            .post('http://localhost:8080/api/test/user/createMembership', {
              name: this.ruleForm.name,
              placeInterest: this.ruleForm.placeOfInterest,
              max_pass: this.ruleForm.maxPassPerLoan
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
            .catch((error) => {
              console.log(error)
              this.$message({
                dangerouslyUseHTMLString: true,
                type: 'error',
                message: '<h2>Add ' + this.ruleForm.name + ' failed!</h2>'
              })
            })
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style></style>

<template>
  <div>
    <el-table
      :data="allMembership"
      style="width: 100%"
      border
      :span-method="objectSpanMethod"
    >
      <!-- <el-table-column fixed prop="id" label="Membership ID"   >
        </el-table-column> -->
      <el-table-column fixed prop="name" label="Membership Name">
      </el-table-column>
      <el-table-column fixed prop="poi" label="Place of Interest">
      </el-table-column>
      <el-table-column fixed prop="maxPassPerLoan" label="Max Passes per Loan">
      </el-table-column>
      <el-table-column
        fixed
        prop="membershipStatus"
        label="Status"
        :formatter="formatter"
      >
      </el-table-column>
      <el-table-column fixed label="Operations" prop="membershipStatus">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            v-if="scope.row.membershipStatus === 1"
            @click="handleDisable(scope.$index, scope.row)"
            >Disable</el-button
          >
          <el-button
            size="mini"
            type="success"
            v-if="scope.row.membershipStatus === 0"
            @click="handleEnable(scope.$index, scope.row)"
            >Enable</el-button
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
        Create a new membership
      </el-button>
    </div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="220px"
      class="demo-ruleForm"
      v-show="dialogFormVisible"
    >
      <el-form-item label="Membership Name" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="Place of Interest" prop="placeOfInterest">
        <el-input v-model="ruleForm.placeOfInterest"></el-input>
      </el-form-item>
      <el-form-item label="Max number of passes per loan" prop="maxPassPerLoan">
        <el-input-number
          v-model="ruleForm.maxPassPerLoan"
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
