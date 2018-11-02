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
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status == 0 ? 'valid': 'invalid'}}</el-tag>
        </template>
      </el-table-column>

       <el-table-column label="Operation" width="260" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">detail</el-button>
        <el-button
          size="mini"
          type="success"
          @click="handApply(scope.row.jobId, scope.row.employer.email)">apply</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="reportEmployer(scope.row.employer.email)">report</el-button>
      </template>
      </el-table-column>
    </el-table>

    <el-dialog title="Job Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="jobId" :label-width="formLabelwidth">
            <el-input-number v-model="detail.jobId" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Number" :label-width="formLabelwidth">
            <el-input-number v-model="detail.number" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Salary" :label-width="formLabelwidth">
            <el-input v-model="detail.salary" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelwidth">
            <el-input v-model="detail.expectWorkExperience" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelwidth">
            <el-input v-model="detail.expectEduBackground" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="jobRequirement" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="detail.jobRequirement" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelwidth">
            <el-input type="textarea" v-model="detail.description" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Create Time" :label-width="formLabelwidth">
            <el-date-picker type="datetime" v-model="detail.createTime" :disabled="true"></el-date-picker>
            </el-form-item>

            <el-form-item label="Employer email" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.email" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer name" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.name" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer phone" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.phone" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer address" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.address" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item type="textarea" label="Employer description" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.description" :disabled="true"></el-input>
            </el-form-item>

             <el-form-item label="Employer domain" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.domain" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer scale" :label-width="formLabelwidth">
            <el-input v-model="detail.employer.scale" :disabled="true"></el-input>
            </el-form-item>
        </el-form>
    </el-dialog>


  </div>
</template>

<script>
import { getRecommendJobList, report, apply } from '@/api/employee'

export default {
  data() {
    return {
      filterText: '',
      list: null,
      listLoading: true,
      dialogFormVisible: false,
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
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'danger'
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
      getRecommendJobList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    reportEmployer(email) {
      report(email).then(response => {
        this.fetchData()
      })
    },
    handApply(jobId, ere) {
      apply(jobId, ere).then(response => {
        this.fetchData()
      })
    }
  }
}
</script>
