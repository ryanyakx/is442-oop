<template>
  <div v-if="accessible">
    <div>
      <el-table
        :data="activeEmployee"
        style="width: 100%"
        border
        :default-sort="{prop: 'Employee_ID', order: ''}"
        :header-cell-style="{background: '#bfffca'}"
      >
        <el-table-column
          label="Active Employee Accounts"
          row-class-name="myrow"
        >
          <el-table-column
            fixed
            prop="employeeID"
            label="Employee ID"
            sortable
          >
          </el-table-column>
          <el-table-column fixed prop="name" label="Employee Name">
          </el-table-column>
          <el-table-column fixed prop="email" label="Employee Email">
          </el-table-column>
          <el-table-column fixed prop="status" label="Status" :formatter = "statusFormatter">
          </el-table-column>
          <el-table-column fixed prop="adminStatus" label="Access Level" :formatter = "adminFormatter">
          </el-table-column>
          <el-table-column fixed label="Operations">
            <template slot-scope="scope">
              <el-button
              size="mini"
              type="danger"
              @click="handleDisable(scope.$index, scope.row)"
              >Disable</el-button
              >
              <el-button
                v-if="scope.row.adminStatus === 1"
                @click="handleAdmin(scope.$index, scope.row)"
                type="primary"
                size="mini"
                >Make Admin
              </el-button>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
    <p></p>
    <el-table
      :data="disabledEmployee"
      style="width: 100%"
      border
      :default-sort="{prop: 'Employee_ID', order: ''}"
      :header-cell-style="{background: '#ffbfbf'}"
    >
      <el-table-column label="Disabled Employee Accounts">
        <el-table-column fixed prop="employeeID" label="Employee ID" sortable>
        </el-table-column>
        <el-table-column fixed prop="name" label="Employee Name">
        </el-table-column>
        <el-table-column fixed prop="email" label="Employee Email">
        </el-table-column>
        <el-table-column fixed prop="status" label="Status" :formatter = "statusFormatter">
          </el-table-column>
          <el-table-column fixed prop="adminStatus" label="Access Level" :formatter = "adminFormatter">
          </el-table-column>
        <el-table-column fixed label="Operations">
          <template slot-scope="scope">
            <el-button
            size="mini"
            type="success"
            @click="handleEnable(scope.$index, scope.row)"
            >Enable</el-button
            >
            <el-button
                v-if="scope.row.Access === 'Employee'"
                @click="handleAdmin(scope.$index, scope.row)"
                type="primary"
                size="mini"
                >Make Admin
              </el-button>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      allEmployees: [
      ],
      activeEmployee: [],
      disabledEmployee: []
    }
  },
  mounted() {
    axios.get('http://localhost:8080/api/test/user/allUser')
      .then(response => {
        console.log(response)
        for (let i = 0; i < response.data.length; i++) {
          if (response.data[i].status === 1) {
            this.activeEmployee.push(response.data[i])
          } else {
            this.disabledEmployee.push(response.data[i])
          }
        }
      })
  },
  methods: {
    handleAdmin(index, row) {
      console.log(index, row)
      this.$confirm('Are you sure you want to make ' + row.name + ' an admin?', 'Make Admin', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        axios.put('http://localhost:8080/api/test/user/makeAdmin/' + row.employeeID, {
        })
          .then(response => {
            window.location.reload()
            this.$message({
              type: 'success',
              message: 'Made ' + row.name + 'an admin successfully!'
            })
            // this.allMembership.splice(index, 1)
          })
          .catch(error => {
            console.log(error)
            this.$message({
              type: 'error',
              message: 'Making ' + row.name + 'an admin failed!'
            })
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Canceled'
        })
      })
    },
    handleDisable(index, row) {
      console.log(index, row)
      this.$confirm('Are you sure you to disable ' + row.name + '?', 'Disable ' + row.name, {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        axios.post('http://localhost:8080/api/test/user/disable/' + row.employeeID, {
        })
          .then(response => {
            window.location.reload()
            console.log(response)
            console.log(row.employeeID)
            this.$message({
              type: 'success',
              message: 'Disable ' + row.name + ' successfully!'
            })
          })
          .catch(error => {
            console.log(error)
            this.$message({
              type: 'error',
              message: 'Disable ' + row.name + ' failed!'
            })
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Disable canceled'
        })
      })
    },
    handleEnable(index, row) {
      console.log(index, row)
      this.$confirm('Are you sure enable ' + row.Employee_Name + '\'s account?', 'Enable', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        axios.post('http://localhost:8080/api/test/user/enable/' + row.employeeID, {
        })
          .then(response => {
            window.location.reload()
            this.$message({
              type: 'success',
              message: 'Enable ' + row.name + ' successfully!'
            })
          })
          .catch(error => {
            console.log(error)
            this.$message({
              type: 'error',
              message: 'Enable ' + row.name + ' failed!'
            })
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Enable canceled'
        })
      })
    },
    statusFormatter(row) {
      if (row.status === 1) {
        return 'Active'
      } else {
        return 'Disabled'
      }
    },
    adminFormatter(row) {
      if (row.adminStatus === 2) {
        return 'Admin'
      } else {
        return 'Employee'
      }
    }
    // sortEmployee() {
    //   this.activeEmployee = []
    //   this.disabledEmployee = []
    //   for (let i = 0; i < this.allEmployees.length; i++) {
    //     if (this.allEmployees[i].Status === 'Active') {
    //       this.activeEmployee.push(this.allEmployees[i])
    //     } else {
    //       this.disabledEmployee.push(this.allEmployees[i])
    //     }
    //   }
    // }
  },
  created() {
    if (localStorage.getItem('role') === '2') {
      this.accessible = true
    }
  }
  // created() {
  //   this.sortEmployee()
  // }
}

</script>

<style></style>
