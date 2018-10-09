<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95" prop="ID" sortable>
        <template slot-scope="scope">
          {{scope.row.noteId}}
        </template>
      </el-table-column>

      <el-table-column label="From" width="150" align="center" prop="From">
        <template slot-scope="scope">
          {{scope.row.crtEmail}}
        </template>
      </el-table-column>

      <el-table-column label="To" width="150" align="center" prop="To">
        <template slot-scope="scope">
          {{scope.row.targetEmail}}
        </template>
      </el-table-column>

      <el-table-column label="Content" prop="Content">
        <template slot-scope="scope">
          {{scope.row.content}}
        </template>
      </el-table-column>

     
      <el-table-column label="CreateTime" width="200" align="center" prop="CreateTime" sortable>
         <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
    </el-table>
     <el-button type="primary" icon="el-icon-edit" circle style="margin-top:30px;" @click="dialogFormVisible = true"></el-button>

      <el-dialog title="New Notification" :visible.sync="dialogFormVisible">
        <el-form :model="newNotification">        

            <el-form-item label="To" :label-width="formLabelWidth">
            <el-input type="text" v-model="newNotification.to" ></el-input>
            </el-form-item>

            <el-form-item label="Content" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="newNotification.content" ></el-input>
            </el-form-item>

            <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleNew()">create</el-button>
            </el-form-item>
            
        </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { getNotification, newNotification } from '@/api/employee'

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
        content: ''
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
      newNotification(this.newNotification).then(response => {
        this.dialogFormVisible = false
        this.fetchData()
      })
    }
  }
}
</script>
