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
          <el-input v-model="detail.eduBackground" ></el-input>
          </el-form-item>

          <el-form-item label="Work Experience" :label-width="formLabelWidth">
          <el-input v-model="detail.workExperience" ></el-input>
          </el-form-item>

          <el-form-item label="Expect Salary" :label-width="formLabelWidth">
          <el-input v-model="detail.expectSalary" ></el-input>
          </el-form-item>

          <el-form-item label="Expect City" :label-width="formLabelWidth">
          <el-input v-model="detail.expectCity" ></el-input>
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
import { getProfile, modifyProfile } from '@/api/employee'

export default {
  data() {
    return {
      formLabelWidth: '160px',
      detail: {
        email: '',
        name: '',
        phone: '',
        birthday: '',
        gender: '',
        eduBackground: '',
        workExperience: '',
        expectSalary: '',
        expectCity: '',
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
      getProfile().then(response => {
        this.detail = response.data
      })
    },
    handleSave() {
      modifyProfile(this.detail).then(response => {
        this.fetchData()
      })
    }
  }
}
</script>
