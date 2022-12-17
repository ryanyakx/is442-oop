<template>
  <div v-if="accessible">
    <el-table
      :data="LoanRecordsTest"
      style="width: 100%"
      border
      :header-cell-style="{background: '#bfffca'}"
    >
      <el-table-column label="UnReturned Loan Records">
        <el-table-column fixed prop="loan_ID" label="Loan_ID" width="250">
        </el-table-column>
        <el-table-column
          fixedcd
          prop="user.employeeID"
          label="Employee_ID"
          width="235"
        >
        </el-table-column>
        <el-table-column
          fixed
          prop="user.email"
          label="Employee_Email"
          width="235"
        >
        </el-table-column>
        <el-table-column
          fixed
          prop="dateCreation"
          label="Date_Of_Creation"
          width="235"
          :formatter="formatter4"
        >
        </el-table-column>
        <el-table-column
          fixed
          prop="dateVisit"
          label="Date_Of_Visit"
          width="235"
          :formatter="formatter2"
        >
        </el-table-column>
        <el-table-column
          fixed
          prop="dateExpectedReturn"
          label="Date_Of_Expected_Return"
          width="235"
          :formatter="formatter3"
        >
        </el-table-column>
        <el-table-column
          fixed
          prop="status"
          label="Status"
          width="235"
          :formatter="formatter"
        >
        </el-table-column>
        <el-table-column fixed label="Operations" width="235">
          <template slot-scope="scope">
            <el-button
              v-if="LoanRecordsTest[scope.$index].status !== 3"
              type="text"
              size="small"
              @click="changetoReturned(scope.$index, scope.row)"
            >
              Change to Returned
            </el-button>
            <el-button
              v-if="LoanRecordsTest[scope.$index].status !== 1"
              type="text"
              size="small"
              @click="changetoCancelled(scope.$index, scope.row)"
            >
              Change to Cancelled
            </el-button>
            <el-button
              v-if="LoanRecordsTest[scope.$index].status !== 2"
              type="text"
              size="small"
              @click="changetoCollected(scope.$index, scope.row)"
            >
              Change to Collected
            </el-button>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
const axios = require('axios').default
export default {
  data() {
    return {
      unReturnedLoanRecords: [
        {
          Loan_ID: 1,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'NotReturned'
        },
        {
          Loan_ID: 2,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'NotReturned'
        },
        {
          Loan_ID: 3,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'NotReturned'
        },
        {
          Loan_ID: 4,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'NotReturned'
        },
        {
          Loan_ID: 5,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'NotReturned'
        }
      ],
      returnedLoanRecords: [
        {
          Loan_ID: 1,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'Returned'
        },
        {
          Loan_ID: 2,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'Returned'
        },
        {
          Loan_ID: 3,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'Returned'
        },
        {
          Loan_ID: 4,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'Returned'
        },
        {
          Loan_ID: 5,
          Employee_ID: '01423974',
          Employee_Email: 'employee1@gmail.com',
          Date_Of_Creation: 'Date1',
          Date_Of_Visit: 'Date1',
          Date_Of_Expected_Return: 'Date1',
          Status: 'Returned'
        }
      ],
      LoanRecordsTest: this.getLoanRecords()
    }
  },
  methods: {
    formatter(row) {
      if (row.status === 0) {
        return 'Ongoing'
      } else if (row.status === 1) {
        return 'Cancelled'
      } else if (row.status === 2) {
        return 'Collected'
      } else if (row.status === 3) {
        return 'Returned'
      }
    },
    // date of visit remove time from , date of expected return 9am
    formatter2(row) {
      const DateVariable = new Date(row.dateVisit)
      const result = DateVariable.toLocaleString('en-SG', {
        timeZone: 'Asia/Singapore',
        hour12: true
      })
      const result2 = result.split(',')
      return result2[0]
    },
    formatter3(row) {
      const DateVariable = new Date(row.dateExpectedReturn)
      const result = DateVariable.toLocaleString('en-SG', {
        timeZone: 'Asia/Singapore',
        hour12: true
      })
      const result2 = result.split(',')
      return result2[0] + ',' + '09:00:00 AM'
    },
    formatter4(row) {
      const DateVariable = new Date(row.dateCreation)
      return DateVariable.toLocaleString('en-SG', {
        timeZone: 'Asia/Singapore',
        hour12: true
      })
    },
    // Calling backend apis
    changetoReturned(index, row) {
      console.log(this.LoanRecordsTest[index].loan_ID)
      this.putLoanRecords(this.LoanRecordsTest[index].loan_ID, 3)
      // location.reload()
      // can input the loan id for backend handling
    },
    changetoCancelled(index, row) {
      console.log(this.LoanRecordsTest[index].loan_ID)
      this.putLoanRecords(this.LoanRecordsTest[index].loan_ID, 1)
      // location.reload()
      // can input the loan id for backend handling
    },
    changetoCollected(index, row) {
      console.log(this.LoanRecordsTest[index].loan_ID)
      this.putLoanRecords(this.LoanRecordsTest[index].loan_ID, 2)
      // location.reload()
      // can input the loan id for backend handling
    },
    getLoanRecords() {
      axios
        .get(
          'http://localhost:8080/api/test/user/getLoanByEmployee/' +
            JSON.parse(window.localStorage.getItem('user')).id
        )
        .then((response) => (this.LoanRecordsTest = response.data))
    },
    putLoanRecords(loanID, status) {
      axios
        .put(
          'http://localhost:8080/api/test/user/updateLoanRecord/' +
            loanID +
            '/' +
            status
        )
        .then((response) => {
          if (response.data.slice(0, 1) === 'a') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'error',
              message: '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
          } else if (response.data.slice(0, 1) === 'b') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'success',
              message: '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
          } else if (response.data.slice(0, 1) === 'c') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'warning',
              message: '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
          } else if (response.data.slice(0, 1) === 'd') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'success',
              message: '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
          } else if (response.data.slice(0, 1) === 'e') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'success',
              message: '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
          } else {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'error',
              message: '<h2>' + response.data + '</h2>'
            })
          }
          console.log(response.data)
        })
      setTimeout(location.reload.bind(location), 1500)
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
