<template>
  <div class="app-container">
    <el-input placeholder="Search" v-model="filterText" style="margin-bottom:30px;"></el-input>
    <el-table :data="filteredList" v-loading="listLoading" element-loading-text="Loading" border fit highlight-current-row>

      <el-table-column align="center" label='Email' width="110" prop="Email">
        <template slot-scope="scope">
          {{scope.row.email}}
        </template>
      </el-table-column>

      <el-table-column label="Name" width="90" align="center" prop="Name" sortable>
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>

      <el-table-column label="Phone" width="90" prop="Phone">
        <template slot-scope="scope">
          {{scope.row.phone}}
        </template>
      </el-table-column>

      <el-table-column label="Address" width="90">
        <template slot-scope="scope">
          {{scope.row.address}}
        </template>
      </el-table-column>

      <el-table-column label="Description">
        <template slot-scope="scope">
          {{scope.row.description}}
        </template>
      </el-table-column>

      <el-table-column label="Domain" width="90">
        <template slot-scope="scope">
          {{scope.row.domain}}
        </template>
      </el-table-column>

      <el-table-column label="Scale" width="90">
        <template slot-scope="scope">
          {{scope.row.scale}}
        </template>
      </el-table-column>

      <el-table-column label="ReportedNum" width="90">
        <template slot-scope="scope">
          {{scope.row.reportedNum}}
        </template>
      </el-table-column>


       <el-table-column label="Operation" width="250" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">modify</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.row.email)">delete</el-button>
           <el-button
          size="mini"
          type="danger"
          @click="ban(scope.row.email)">ban</el-button>
      </template>
      </el-table-column>
    </el-table>
    <el-dialog title="Employer Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="Email" :label-width="formLabelWidth">
            <el-input v-model="detail.email" ></el-input>
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

            <el-form-item label="ReportedNum" :label-width="formLabelWidth">
            <el-input-number v-model="detail.reportedNum"></el-input-number>
            </el-form-item>


             <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleSave()">save</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getEmployerList, saveEmployer, deleteEmployer, banEmployer } from '@/api/admin'

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
        email: '',
        name: '',
        phone: '',
        address: '',
        description: '',
        domain: '',
        scale: '',
        isForbidden: 0,
        reportedNum: 0
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
      getEmployerList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleSave() {
      saveEmployer(this.detail).then(response => {
        this.dialogFormVisible = false
        this.fetchData()
      })
    },
    handleDelete(email) {
      deleteEmployer(email).then(response => {
        this.fetchData()
      })
    },
    ban(email) {
      banEmployer(email).then(response => {
        this.fetchData()
      })
    }
  }
}
</script>
