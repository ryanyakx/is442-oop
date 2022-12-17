<template>
  <div v-if="accessible">
    <el-row>
      <el-col :span="12">
        <div class="block">
          <span class="demonstration">Pick a day you wish to visit </span>
          <el-date-picker
            v-model="date_of_visit"
            type="date"
            placeholder="Pick a day"
            :picker-options="pickerOptions"
            @input="onDateChange"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="12">
        <h4>Date of Visit Chosen : {{ date_of_visit }}</h4>
        <h2>Bookers</h2>
        <div class="scroll-bar">
          <el-row>
            <el-col :span="8" v-for="booker in bookerTest" :key="booker">
              <div class="booker-card">
                <el-card :body-style="{padding: '0px'}" shadow="always">
                  <div style="padding: 14px">
                    <h4>Booker name : {{ booker.user.name }}</h4>
                    <h4>
                      Booker's contact info : {{ booker.user.contactNum }}
                    </h4>
                    <h4>Place of Visit : {{ booker.pass.memberObject.poi }}</h4>
                  </div>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
        <!-- card -->
        <div class="form" v-if="dateSelected">
          <h2>Book a Loan Pass</h2>
          <el-form ref="form" :model="bookingForm" label-width="200px">
            <el-form-item label="Place to visit">
              <el-select
                v-model="bookingForm.placeOfVisit"
                placeholder="please select your place to visit"
              >
                <el-option
                  v-for="POI in allPOI"
                  :key="POI"
                  :label="POI"
                  :value="POI"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Number of Passes" style="width: 417px">
              <el-input v-model="bookingForm.numberOfPasses"></el-input>
            </el-form-item>
            <el-form-item label="Date of Visit" style="width: 417px">
              <el-input v-model="date_of_visit"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="postCreateLoan">Book</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
const axios = require('axios').default
export default {
  data() {
    return {
      dateSelected: false,
      allPOI: this.getPlaceOfInterest(),
      bookers: [
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        },
        {
          name: 'sanghil',
          contact: '82504012',
          place_of_visit: 'Singapore Zoo'
        }
      ],
      bookerTest: this.getBookers(),
      numberOfPassesAvailable: 0, // GET method to be added
      pickerOptions: {
        disabledDate(time) {
          return (
            time.getTime() < Date.now() ||
            time.getTime() > Date.now() + 86400000 * 56
            // Must have applied and booked  for loan 1day to 8weeks prior to visiting the place of interest
          )
        },
        shortcuts: [
          {
            text: 'Tomorrow',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() + 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          },
          {
            text: 'A week later',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }
        ]
      },
      date_of_visit: localStorage.getItem('date_chosen'),
      bookingForm: {
        placeOfVisit: '',
        numberOfPasses: 0,
        dateOfVisit: this.date_of_visit
      },
      accessible: false
    }
  },
  mounted() {
    this.date_of_visit = null
  },
  methods: {
    onDateChange(val) {
      this.dateSelected = true
      this.getBookers(val)
      // backend method to check what passes available on that date , bookers' info retreive as well
      console.log(val)
      this.date = val
      localStorage.setItem('date_chosen', val)
    },
    getBookers(date) {
      axios
        .get('http://localhost:8080/api/test/user/getLoanDate/' + date)
        .then((response) => {
          this.bookerTest = response.data
          console.log(this.bookerTest)
        })
    },
    async postCreateLoan() {
      await axios
        .post('http://localhost:8080/api/test/user/createLoan', {
          email: JSON.parse(window.localStorage.getItem('user')).email,
          place_of_interest: this.bookingForm.placeOfVisit,
          num_pass: this.bookingForm.numberOfPasses,
          date: this.date_of_visit
        })
        .then((response) => {
          if (response.data.slice(0, 1) === 'a') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'error',
              message:
                '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
            setTimeout(location.reload.bind(location), 1500)
          } else if (response.data.slice(0, 1) === 'b') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'error',
              message:
                '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
            setTimeout(location.reload.bind(location), 1500)
          } else if (response.data.slice(0, 1) === 'c') {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'error',
              message:
                '<h2>' + response.data.slice(1, response.data.length) + '</h2>'
            })
            setTimeout(location.reload.bind(location), 1500)
          } else {
            this.$message({
              dangerouslyUseHTMLString: true,
              type: 'success',
              message:
                '<h2>' + response.data.slice(0, response.data.length) + '</h2>'
            })
            setTimeout(location.reload.bind(location), 3500)
          }
          console.log(response.data)
        })
      // location.reload()
      console.log(this.bookingForm.placeOfVisit)
      console.log(this.bookingForm.numberOfPasses)
      console.log(this.date_of_visit)
    },
    getPlaceOfInterest() {
      axios
        .get('http://localhost:8080/api/test/user/allPOI')
        .then((response) => {
          this.allPOI = response.data
          console.log(this.allPOI)
        })
    }
  },
  created() {
    if (localStorage.getItem('role') === '2') {
      this.accessible = true
    }
  }
}
</script>

<style>
.booker-card {
  margin: 20px !important;
}

div.scroll-bar {
  height: 300px !important;
  overflow: scroll !important;
}
</style>
