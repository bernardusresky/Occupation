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

       <el-table-column label="Birthday" width="120" prop="Birthday" sortable>
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
          {{scope.row.eduBackground}}
        </template>
      </el-table-column>

      <el-table-column label="Work Experience" width="170" align="center" sortable>
        <template slot-scope="scope">
          {{scope.row.workExperience}}
        </template>
      </el-table-column>

       <el-table-column label="Expect Salary" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.expectSalary}}
        </template>
      </el-table-column>

       <el-table-column label="Expect City" width="80" align="center">
        <template slot-scope="scope">
          {{scope.row.expectCity}}
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

       <el-table-column label="Operation" width="260" align="center">
        <template slot-scope="scope">
        <el-button
          size="mini"
          @click="dialogFormVisible = true, detail = scope.row">detail</el-button>
        <el-button
          size="mini"
          type="success"
          @click="dialogFormVisible1 = true, choseEe = scope.row.email">offer</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="reportEmployee(scope.row.email)">report</el-button>
      </template>
      </el-table-column>
    </el-table>

    <el-dialog title="Employee Detail" :visible.sync="dialogFormVisible">
        <el-form :model="detail">
            <el-form-item label="Email" :label-width="formLabelWidth">
            <el-input v-model="detail.email" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Name" :label-width="formLabelWidth">
            <el-input v-model="detail.name" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Phone" :label-width="formLabelWidth">
            <el-input v-model="detail.phone" :disabled="true"></el-input>
            </el-form-item>

             <el-form-item label="Birthday" :label-width="formLabelWidth">
            <el-date-picker type="date" v-model="detail.birthday" :disabled="true"></el-date-picker>
            </el-form-item>

             <el-form-item label="Gender" :label-width="formLabelWidth">
            <el-input v-model="detail.gender" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Edu Background" :label-width="formLabelWidth">
            <el-input v-model="detail.eduBackground" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Work Experience" :label-width="formLabelWidth">
            <el-input v-model="detail.workExperience" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Expect Salary" :label-width="formLabelWidth">
            <el-input v-model="detail.expectSalary" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Expect City" :label-width="formLabelWidth">
            <el-input v-model="detail.expectCity" :disabled="true"></el-input>
            </el-form-item>

           <el-form-item label="Address" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.address" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="Description" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="detail.description" :disabled="true"></el-input>
            </el-form-item>
        </el-form>
    </el-dialog>

     <el-dialog title="Choose Job" :visible.sync="dialogFormVisible1">
        <el-form >        

            <el-form-item label="Job Id" :label-width="formLabelWidth">
            <el-input-number v-model="choseJobId" ></el-input-number>
            </el-form-item>

            <el-form-item :label-width="formLabelWidth">
              <el-button type="primary" @click="handleOffer()">Send</el-button>
            </el-form-item>
            
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getApplicants, report, offer } from '@/api/employer'

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
        eduBackground: '',
        workExperience: '',
        expectSalary: '',
        expectCity: '',
        address: '',
        description: ''
      },
      choseJobId: '',
      choseEe: ''
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
      getApplicants().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    reportEmployee(email) {
      report(email).then(response => {
        this.fetchData()
      })
    },
    handleOffer() {
      offer(this.choseJobId, this.choseEe).then(response => {
        this.dialogFormVisible1 = false
        this.fetchData()
      })
    }
  }
}
</script>
