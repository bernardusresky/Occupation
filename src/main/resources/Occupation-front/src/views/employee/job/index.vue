<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95" prop="ID" sortable>
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>

      <el-table-column label="Number" width="110" align="center" prop="Number" sortable>
        <template slot-scope="scope">
          {{scope.row.number}}
        </template>
      </el-table-column>

      <el-table-column label="Salary" width="110" prop="Salary" sortable>
        <template slot-scope="scope">
          {{scope.row.salary}}
        </template>
      </el-table-column>

      <el-table-column label="Work Experience" width="150" align="center">
        <template slot-scope="scope">
          {{scope.row.expect_work_experience}}
        </template>
      </el-table-column>

      <el-table-column label="Edu Background" width="150" align="center">
        <template slot-scope="scope">
          {{scope.row.expect_edu_background}}
        </template>
      </el-table-column>

      <el-table-column label="Requirement">
        <template slot-scope="scope">
          {{scope.row.requirement}}
        </template>
      </el-table-column>

      <el-table-column label="Description">
        <template slot-scope="scope">
          {{scope.row.description}}
        </template>
      </el-table-column>

      <el-table-column label="Create Time" width="200" align="center" prop="Create Time" sortable>
         <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.create_time}}</span>
        </template>
      </el-table-column>

      <el-table-column label="Status" width="110" align="center" prop="Status" sortable>
         <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column>

       <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">detail</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">apply</el-button>
      </template>
      </el-table-column>
    </el-table>

    <el-dialog title="Job Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="ID" :label-width="formLabelWidth">
            <el-input-number v-model="detail.id" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Number" :label-width="formLabelWidth">
            <el-input-number v-model="detail.number" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Salary" :label-width="formLabelWidth">
            <el-input-number v-model="detail.salary" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_work_experience" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_edu_background" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Requirement" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.requirement" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.description" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Create Time" :label-width="formLabelWidth">
            <!-- <el-input v-model="detail.create_time" auto-complete="off"></el-input> -->
            <el-date-picker type="datetime" v-model="detail.create_time" :disabled="true"></el-date-picker>
            </el-form-item>

            <el-form-item label="Employer email" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.email" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer name" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.name" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer phone" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.phone" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer address" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.address" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item type="textarea" label="Employer description" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.description" :disabled="true"></el-input>
            </el-form-item>

             <el-form-item label="Employer domain" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.domain" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Employer scale" :label-width="formLabelWidth">
            <el-input v-model="detail.employer.scale" :disabled="true"></el-input>
            </el-form-item>
        </el-form>
    </el-dialog>


  </div>
</template>

<script>
import { getRecommendJobList } from '@/api/employee'

export default {
  data() {
    return {
      filterText: '',
      list: null,
      listLoading: true,
      dialogFormVisible: false,
      formLabelWidth: '160px',
      detail: {
        id: '',
        number: '',
        salary: '',
        expect_work_experience: '',
        expect_edu_background: '',
        requirement: '',
        description: '',
        create_time: '',
        status: '',
        employer: {
          email: '',
          name: '',
          phone: '',
          address: '',
          description: '',
          domain: '',
          scale: ''
        }
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
        if (item.description.toLowerCase().indexOf(filterText) !== -1 || item.requirement.toLowerCase().indexOf(filterText) !== -1) {
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
        this.list = response.data.items
        this.listLoading = false
      })
    }
  }
}
</script>
