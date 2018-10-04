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

      <el-table-column label="Phone" width="110" prop="Phone">
        <template slot-scope="scope">
          {{scope.row.phone}}
        </template>
      </el-table-column>

       <el-table-column label="Birthday" width="110" prop="Birthday" sortable>
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{scope.row.birthday}}</span>
        </template>
      </el-table-column>

       <el-table-column label="Gender" width="100" prop="Gender" sortable>
        <template slot-scope="scope">
          {{scope.row.gender}}
        </template>
      </el-table-column>

      <el-table-column label="Edu Background" width="170" align="center" sortable>
        <template slot-scope="scope">
          {{scope.row.edu_background}}
        </template>
      </el-table-column>

      <el-table-column label="Work Experience" width="170" align="center" sortable>
        <template slot-scope="scope">
          {{scope.row.work_experience}}
        </template>
      </el-table-column>

       <el-table-column label="Expect Salary" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.expect_salary}}
        </template>
      </el-table-column>

       <el-table-column label="Expect City" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.expect_city}}
        </template>
      </el-table-column>

      <el-table-column label="Address">
        <template slot-scope="scope">
          {{scope.row.address}}
        </template>
      </el-table-column>

      <el-table-column label="Description">
        <template slot-scope="scope">
          {{scope.row.description}}
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
    

    <el-dialog title="Employee Detail" :visible.sync="dialogFormVisible">
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

             <el-form-item label="Birthday" :label-width="formLabelWidth">
            <el-date-picker type="date" v-model="detail.birthday" ></el-date-picker>
            </el-form-item>

             <el-form-item label="Gender" :label-width="formLabelWidth">
            <el-input v-model="detail.gender" ></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="detail.edu_background" ></el-input>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="detail.work_experience" ></el-input>
            </el-form-item>

            <el-form-item label="Expect Salary" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_salary" ></el-input>
            </el-form-item>

            <el-form-item label="Expect City" :label-width="formLabelWidth">
            <el-input v-model="detail.expect_city" ></el-input>
            </el-form-item>

           <el-form-item label="Address" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.address" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.description" ></el-input>
            </el-form-item>

             <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleSave()">save</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
     <el-dialog title="New Employee" :visible.sync="dialogFormVisible1">
        <el-form :model="newEmployee">
            <el-form-item label="Email" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.email" ></el-input>
            </el-form-item>

            <el-form-item label="Name" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.name"></el-input>
            </el-form-item>

            <el-form-item label="Phone" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.phone" ></el-input>
            </el-form-item>

             <el-form-item label="Birthday" :label-width="formLabelWidth">
            <el-date-picker type="date" v-model="newEmployee.birthday" ></el-date-picker>
            </el-form-item>

             <el-form-item label="Gender" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.gender" ></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.edu_background" ></el-input>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.work_experience" ></el-input>
            </el-form-item>

            <el-form-item label="Expect Salary" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.expect_salary" ></el-input>
            </el-form-item>

            <el-form-item label="Expect City" :label-width="formLabelWidth">
            <el-input v-model="newEmployee.expect_city" ></el-input>
            </el-form-item>

           <el-form-item label="Address" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="newEmployee.address" ></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="newEmployee.description" ></el-input>
            </el-form-item>

             <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleNew()">create</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>


  </div>
</template>

<script>
import { getEmployeeList } from '@/api/admin'

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
        birthday: '',
        gender: '',
        edu_background: '',
        work_experience: '',
        expect_salary: '',
        expect_city: '',
        address: '',
        description: ''
      },
      newEmployee: {
        email: '',
        name: '',
        phone: '',
        birthday: '',
        gender: '',
        edu_background: '',
        work_experience: '',
        expect_salary: '',
        expect_city: '',
        address: '',
        description: ''
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
      getEmployeeList().then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    },
    handleSave() {

    },
    handleDelete() {

    },
    handleNew() {

    }
  }
}
</script>
