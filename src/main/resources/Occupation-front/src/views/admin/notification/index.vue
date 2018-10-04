<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95" prop="ID" sortable>
        <template slot-scope="scope">
          {{scope.row.id}}
        </template>
      </el-table-column>

      <el-table-column label="From" width="110" align="center" prop="From">
        <template slot-scope="scope">
          {{scope.row.from}}
        </template>
      </el-table-column>

      <el-table-column label="To" width="110" align="center" prop="To">
        <template slot-scope="scope">
          {{scope.row.to}}
        </template>
      </el-table-column>

      <el-table-column label="Message" prop="Message">
        <template slot-scope="scope">
          {{scope.row.message}}
        </template>
      </el-table-column>

     
      <el-table-column label="Time" width="200" align="center" prop="Time" sortable>
         <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.time}}</span>
        </template>
      </el-table-column>

       <!-- <el-table-column label="Operation" width="200" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">detail</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">apply</el-button>
      </template>
      </el-table-column> -->
    </el-table>
     <el-button type="primary" icon="el-icon-edit" circle style="margin-top:30px;" @click="dialogFormVisible = true"></el-button>

      <el-dialog title="New Notification" :visible.sync="dialogFormVisible">
        <el-form :model="newNotification">        

            <el-form-item label="To" :label-width="formLabelWidth">
            <el-input type="text" v-model="newNotification.to" ></el-input>
            </el-form-item>

            <el-form-item label="Message" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="newNotification.message" ></el-input>
            </el-form-item>

            <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleNew()">create</el-button>
            </el-form-item>
            
        </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { getNotification } from '@/api/admin'

export default {
  data() {
    return {
      dialogFormVisible: false,
      filterText: '',
      list: null,
      listLoading: true,
      formLabelWidth: '160px',
      newNotification: {
        to: '',
        message: ''
      }
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
        if (item.from.toLowerCase().indexOf(filterText) !== -1 || item.to.toLowerCase().indexOf(filterText) !== -1 || item.message.toLowerCase().indexOf(filterText) !== -1) {
          return item
        }
      })
      return filteredList
    }
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getNotification().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleNew() {
    }
  }
}
</script>
