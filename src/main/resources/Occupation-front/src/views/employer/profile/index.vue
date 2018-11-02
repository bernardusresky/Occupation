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

           <el-form-item label="Address" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.address" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.description" ></el-input>
            </el-form-item>

             <el-form-item label="Domain" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.domain" ></el-input>
            </el-form-item>

            <el-form-item label="Scale" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.scale" ></el-input>
            </el-form-item>

             <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleSave()">save</el-button>
            </el-form-item>
        </el-form>
  </div>
</template>

<script>
import { getProfile, modifyProfile } from '@/api/employer'

export default {
  data() {
    return {
      formLabelWidth: '160px',
      detail: {
        email: '',
        name: '',
        phone: '',
        address: '',
        description: '',
        domain: '',
        scale: ''
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
