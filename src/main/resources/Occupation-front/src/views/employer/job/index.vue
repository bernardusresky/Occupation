<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column align="center" label='jobId' width="90" prop="jobId" sortable>
        <template slot-scope="scope">
          {{scope.row.jobId}}
        </template>
      </el-table-column>

      <el-table-column label="Number" width="90" align="center" prop="Number" sortable>
        <template slot-scope="scope">
          {{scope.row.number}}
        </template>
      </el-table-column>

      <el-table-column label="Salary" width="90" prop="Salary" sortable>
        <template slot-scope="scope">
          {{scope.row.salary}}
        </template>
      </el-table-column>

      <el-table-column label="Work Experience" width="90" align="center">
        <template slot-scope="scope">
          {{scope.row.expectWorkExperience}}
        </template>
      </el-table-column>

      <el-table-column label="Edu Background" width="90" align="center">
        <template slot-scope="scope">
          {{scope.row.expectEduBackground}}
        </template>
      </el-table-column>

      <el-table-column label="Requirement">
        <template slot-scope="scope">
          {{scope.row.jobRequirement}}
        </template>
      </el-table-column>

      <el-table-column label="Description">
        <template slot-scope="scope">
          {{scope.row.description}}
        </template>
      </el-table-column>

      <el-table-column label="Create Time" width="110" align="center" prop="Create Time" sortable>
         <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>

      <el-table-column label="Status" width="90" align="center" prop="Status" sortable>
         <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column>

       <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">modify</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.jobId)">delete</el-button>
      </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" icon="el-icon-edit" circle style="margin-top:30px;" @click="dialogFormVisible1 = true"></el-button>
    <el-dialog title="Job Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="jobId" :label-width="formLabelwidth">
            <el-input-number v-model="detail.jobId" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Number" :label-width="formLabelwidth">
            <el-input-number v-model="detail.number" ></el-input-number>
            </el-form-item>

            <el-form-item label="Salary" :label-width="formLabelwidth">
            <el-input v-model="detail.salary" ></el-input>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelwidth">
            <el-input v-model="detail.expectWorkExperience" ></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelwidth">
            <el-input v-model="detail.expectEduBackground" ></el-input>
            </el-form-item>

            <el-form-item label="Requirement" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="detail.jobRequirement" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="detail.description" ></el-input>
            </el-form-item>

            <el-form-item :label-width="formLabelwidth">
              <el-button type="primary" @click="handleSave()">save</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>

    
    
     <el-dialog title="New Job" :visible.sync="dialogFormVisible1">
        <el-form :model="new_job">
            <el-form-item label="Number" :label-width="formLabelwidth">
            <el-input-number v-model="new_job.number" ></el-input-number>
            </el-form-item>
            <el-form-item label="Salary" :label-width="formLabelwidth">
            <el-input v-model="new_job.salary" ></el-input>
            </el-form-item>
            <el-form-item label="Work Experience" :label-width="formLabelwidth">
            <el-input v-model="new_job.expectWorkExperience" ></el-input>
            </el-form-item>
            <el-form-item label="Edu Background" :label-width="formLabelwidth">
            <el-input v-model="new_job.expectEduBackground" ></el-input>
            </el-form-item>
            <el-form-item label="Requirement" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="new_job.jobRequirement" ></el-input>
            </el-form-item>
            <el-form-item label="Description" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="new_job.description" ></el-input>
            </el-form-item>
            <el-form-item :label-width="formLabelwidth">
            <el-button type="primary" @click="handleNew()">create</el-button>
            </el-form-item>

        </el-form>
    </el-dialog>



  </div>
</template>

<script>
import { getMyJobList, modifyJob, deleteJob, newJob } from '@/api/employer'
import store from '../../../store'
export default {
  data() {
    return {
      filterText: '',
      list: null,
      listLoading: true,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelwidth: '160px',
      detail: {
        jobId: '',
        number: '',
        salary: '',
        expectWorkExperience: '',
        expectEduBackground: '',
        jobRequirement: '',
        description: '',
        createTime: '',
        status: '',
        employer: {
          email: '',
          name: '',
          phone: '',
          address: '',
          description: '',
          domain: '',
          scale: '',
          isForbidden: false,
          reportedNum: 0
        }
      },
      new_job: {
        jobId: '',
        number: '',
        salary: '',
        expectWorkExperience: '',
        expectEduBackground: '',
        jobRequirement: '',
        description: '',
        createTime: '',
        status: 0,
        createEmployerEmail: store.getters.token
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        valid: 'success',
        invalid: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    this.fetchData()
  },
  computed: {
    filteredList() {
      var filteredList = this.list
      var filterText = this.filterText
      if (!filterText) {
        return filteredList
      }
      filterText = filterText.trim().toLowerCase()
      filteredList = filteredList.filter(function(item) {
        if (item.description.toLowerCase().indexOf(filterText) !== -1 || item.jobRequirement.toLowerCase().indexOf(filterText) !== -1) {
          return item
        }
      })
      return filteredList
    }
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getMyJobList().then(response => {
        this.list = response.data
        this.list.filter(function(item) {
          if (item.employer.email === store.getters.token) {
            return item
          }
        })
        this.listLoading = false
      })
    },
    handleSave() {
      modifyJob(this.detail).then(response => {
        this.dialogFormVisible = false
        this.fetchData()
      })
    },
    handleDelete(jobId) {
      deleteJob(jobId).then(response => {
        this.fetchData()
      })
    },
    handleNew() {
      newJob(this.new_job).then(response => {
        this.dialogFormVisible1 = false
        this.fetchData()
      })
    }
  }
}
</script>
