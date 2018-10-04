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
          @click="dialogFormVisible = true, detail = scope.row">modify</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">delete</el-button>
      </template>
      </el-table-column>
    </el-table>
    <el-button type="primary" icon="el-icon-edit" circle style="margin-top:30px;" @click="dialogFormVisible1 = true"></el-button>

    <el-dialog title="Job Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="ID" :label-width="formLabelWidth">
            <el-input-number v-model="detail.id" :disabled="true"></el-input-number>
            </el-form-item>

            <el-form-item label="Number" :label-width="formLabelWidth">
            <el-input-number v-model="detail.number" ></el-input-number>
            </el-form-item>

            <el-form-item label="Salary" :label-width="formLabelWidth">
            <el-input-number v-model="detail.salary" ></el-input-number>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_work_experience" ></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_edu_background" ></el-input>
            </el-form-item>

            <el-form-item label="Requirement" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.requirement" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.description" ></el-input>
            </el-form-item>

            <el-form-item label="Create Time" :label-width="formLabelWidth">
            <!-- <el-input v-model="detail.create_time" auto-complete="off"></el-input> -->
            <el-date-picker type="datetime" v-model="detail.create_time" :disabled="true"></el-date-picker>
            </el-form-item>
            <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="submitForm('dynamicValidateForm')">save</el-button>
            </el-form-item>
            
        </el-form>
    </el-dialog>


     <el-dialog title="New Job" :visible.sync="dialogFormVisible1">
        <el-form :model="new_job">
            <!-- <el-form-item label="ID" :label-width="formLabelWidth">
            <el-input-number v-model="detail.id" :disabled="true"></el-input-number>
            </el-form-item> -->

            <el-form-item label="Number" :label-width="formLabelWidth">
            <el-input-number v-model="new_job.number" ></el-input-number>
            </el-form-item>

            <el-form-item label="Salary" :label-width="formLabelWidth">
            <el-input-number v-model="new_job.salary" ></el-input-number>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="new_job.expect_work_experience" ></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="new_job.expect_edu_background" ></el-input>
            </el-form-item>

            <el-form-item label="Requirement" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="new_job.requirement" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="new_job.description" ></el-input>
            </el-form-item>

            <!-- <el-form-item label="Create Time" :label-width="formLabelWidth"> -->
            <!-- <el-input v-model="detail.create_time" auto-complete="off"></el-input> -->
            <!-- <el-date-picker type="datetime" v-model="detail.create_time" :disabled="true"></el-date-picker> -->
            <!-- </el-form-item> -->
            <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="submitForm('dynamicValidateForm')">create</el-button>
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
      dialogFormVisible1: false,
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
        status: ''
      },
      new_job: {
        number: '',
        salary: '',
        expect_work_experience: '',
        expect_edu_background: '',
        requirement: '',
        description: ''
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
