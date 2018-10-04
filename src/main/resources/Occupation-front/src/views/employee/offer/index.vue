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
          type="success"
          @click="handleDelete(scope.$index, scope.row)">accept</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">reject</el-button>
      </template>
      </el-table-column>
    </el-table>


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
      formLabelWidth: '160px'
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
