<template>
  <div class="login-container">
    <el-form class="login-form" autoComplete="on" :model="registerForm" :rules="loginRules" ref="registerForm" label-position="left">
      <h3 class="title">Complete Information</h3>

      <el-form-item prop="name">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="name" type="text" v-model="registerForm.name" autoComplete="on" placeholder="name" />
      </el-form-item>

      <el-form-item prop="phone">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="phone" type="text" v-model="registerForm.phone" autoComplete="on" placeholder="phone" />
      </el-form-item>

      <el-form-item prop="birthday">
        <el-date-picker type="date" placeholder="birthday" v-model="registerForm.birthday" style="padding-left: 30px;"></el-date-picker>
      </el-form-item>

      <el-form-item prop="gender" style="background: transparent;border: 0px">
        <el-radio-group v-model="registerForm.gender" style="width:450px">
          <el-radio label="male" border style="width:220px;border: 1px solid rgba(255, 255, 255, 0.1);background: rgba(0, 0, 0, 0.1);color:#dddddd"></el-radio>
          <el-radio label="female" border style="width:220px;border: 1px solid rgba(255, 255, 255, 0.1);background: rgba(0, 0, 0, 0.1);color:#dddddd"></el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item prop="address">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="address" type="text" v-model="registerForm.address" autoComplete="on" placeholder="address" />
      </el-form-item>

      <el-form-item prop="expect_salary">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="expect_salary" type="text" v-model="registerForm.expect_salary" autoComplete="on" placeholder="expect_salary" />
      </el-form-item>

      <el-form-item prop="expect_city">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="expect_city" type="text" v-model="registerForm.expect_city" autoComplete="on" placeholder="expect_city" />
      </el-form-item>

      <el-form-item prop="edu_background">
        <el-input name="edu_background" type="textarea" v-model="registerForm.edu_background" autoComplete="on" placeholder="edu_background" />
      </el-form-item>

      <el-form-item prop="work_experience">
        <el-input name="work_experience" type="textarea" v-model="registerForm.work_experience" autoComplete="on" placeholder="work_experience" />
      </el-form-item>

      <el-form-item prop="description">
        <el-input name="description" type="textarea" v-model="registerForm.description" autoComplete="on" placeholder="description" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleRegister">
          Sign up
        </el-button>
      </el-form-item>

      <div class="tips">
        <span style="margin-right:170px;"><a class="link" href="#/register/employee">Back</a></span>
      </div>

    </el-form>
  </div>
</template>

<script>
import store from '../../../store'
export default {
  name: 'register',
  data() {
    return {
      registerForm: {
        email: store.getters.register_email,
        name: '',
        password: store.getters.register_pwd,
        phone: '',
        birthday: '',
        gender: '',
        address: '',
        expect_salary: '',
        expect_city: '',
        edu_background: '',
        work_experience: '',
        description: ''
      },
      loginRules: {
      },
      loading: false
    }
  },
  methods: {
    handleRegister() {
      this.$refs.registerForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('RegisterByEmployee', this.registerForm).then(() => {
            this.loading = false
            this.$router.push({ path: '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$light_gray:#eee;

/* reset element-ui css */
.login-container {
  overflow:auto;
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }
  .el-textarea{
      display: inline-block;
      height: 65px;
      width: 100%;
      textarea{
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 60px;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: #fff !important;
        }
      }

  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}

</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  background-color: $bg;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
    &_login {
      font-size: 20px;
    }
  }
  .title {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
