<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column label="InteractionId" width="150" align="center" prop="interactionId" sortable>
        <template slot-scope="scope">
          {{scope.row.interId}}
        </template>
      </el-table-column>

      <el-table-column label="JobId" width="90" align="center" prop="jobId" sortable>
        <template slot-scope="scope">
          {{scope.row.jobId}}
        </template>
      </el-table-column>

      <el-table-column label="Employer Email" prop="employerEmail" sortable>
        <template slot-scope="scope">
          {{scope.row.employerEmail}}
        </template>
      </el-table-column>


       <el-table-column label="Create Time" width="180" prop="createTime" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>

      <el-table-column label="Status" width="170" align="center" sortable>
        <template slot-scope="scope">
          <el-tag :type="scope.row.checkStatus | statusFilter">{{interactionStatus(scope.row.checkStatus)}}</el-tag>
        </template>
      </el-table-column>

       <el-table-column label="Operation" width="320" align="center">
        <template slot-scope="scope">
        <el-button v-if="scope.row.checkStatus === 1"
          size="mini"
          type="success"
          @click="handAccept(scope.row.interId,1)">accept</el-button>
        <el-button v-if="scope.row.checkStatus === 1"
          size="mini"
          type="danger"
          @click="handAccept(scope.row.interId,0)">reject</el-button>
      </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getInteractionList, accept } from '@/api/employee'

export default {
  data() {
    return {
      filterText: '',
      list: null,
      listLoading: true,
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelWidth: '160px'
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'info',
        2: 'danger',
        3: 'success'
      }
      if (status < 0) return statusMap[-status]
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
        if (item.email.toLowerCase().indexOf(filterText) !== -1 || item.name.toLowerCase().indexOf(filterText) !== -1 || item.description.toLowerCase().indexOf(filterText) !== -1) {
          return item
        }
      })
      return filteredList
    }
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getInteractionList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handAccept(interId, isAccept) {
      accept(interId, isAccept).then(response => {
        this.fetchData()
      })
    },
    interactionStatus(status) {
      if (status === -1) {
        return 'waiting'
      } else if (status === -2) {
        return 'disagreed'
      } else if (status === -3) {
        return 'agreed'
      } else if (status === 1) {
        return 'waiting'
      } else if (status === 2) {
        return 'rejected'
      } else {
        return 'accepted'
      }
    }
  }
}
</script>
