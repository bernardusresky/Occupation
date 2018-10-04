<template>
  <div class="app-container">
        <el-form :model="detail">
          <el-form-item label="Email" :label-width="formLabelWidth">
          <el-input v-model="detail.email" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="Name" :label-width="formLabelWidth">
          <el-input v-model="detail.name"></el-input>
          </el-form-item>

          <el-form-item label="Phone" :label-width="formLabelWidth">
          <el-input v-model="detail.phone" ></el-input>
          </el-form-item>

            <el-form-item label="Birthday" :label-width="formLabelWidth">
          <el-date-picker type="date" v-model="detail.birthday" ></el-date-picker>
          </el-form-item>

            <el-form-item label="Gender" :label-width="formLabelWidth">
          <el-input v-model="detail.gender" ></el-input>
          </el-form-item>

          <el-form-item label="Edu Background" :label-width="formLabelWidth">
          <el-input v-model="detail.edu_background" ></el-input>
          </el-form-item>

          <el-form-item label="Work Experience" :label-width="formLabelWidth">
          <el-input v-model="detail.work_experience" ></el-input>
          </el-form-item>

          <el-form-item label="Expect Salary" :label-width="formLabelWidth">
          <el-input v-model="detail.expect_salary" ></el-input>
          </el-form-item>

          <el-form-item label="Expect City" :label-width="formLabelWidth">
          <el-input v-model="detail.expect_city" ></el-input>
          </el-form-item>

          <el-form-item label="Address" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="detail.address" ></el-input>
          </el-form-item>

          <el-form-item label="Description" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="detail.description" ></el-input>
          </el-form-item>

            <el-form-item :label-width="formLabelWidth">
            <el-button type="primary" @click="handleSave()">save</el-button>
          </el-form-item>
      </el-form>
  </div>
</template>

<script>
import { getProfile } from '@/api/employee'

export default {
  data() {
    return {
      profileLoading: true,
      formLabelWidth: '160px',
      detail: {
        email: '',
        name: '',
        phone: '',
        birthday: '',
        gender: '',
        edu_background: '',
        work_experience: '',
        expect_salary: '',
        expect_city: '',
        address: '',
        description: ''
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.profileLoading = true
      getProfile().then(response => {
        this.detail = response.data
        this.profileLoading = false
      })
    },
    handleSave() {

    }
  }
}
</script>
