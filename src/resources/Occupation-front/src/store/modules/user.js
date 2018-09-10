import { login, logout, getInfo, reset_password } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { registerByAdmin, registerByEmployer, registerByEmployee } from '../../api/register'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    userType: ''
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_USERTYPE: (state, userType) => {
      state.userType = userType
    }
  },

  actions: {
    //
    Login({ commit }, userInfo) {
      const email = userInfo.email
      return new Promise((resolve, reject) => {
        login(email, userInfo.password).then(response => {
          const token = response.message
          setToken(token)
          commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //
    ResetPassword({ commit }, userInfo) {
      const email = userInfo.email
      return new Promise((resolve, reject) => {
        reset_password(email, userInfo.password).then(response => {
          // const token = response.message
          // setToken(token)
          // commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //
    RegisterByAdmin({ commit }, adminInfo) {
      return new Promise((resolve, reject) => {
        registerByAdmin(adminInfo.email, adminInfo.password).then(response => {
          const token = response.message
          setToken(token)
          commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //
    RegisterByEmployer({ commit }, employerInfo) {
      return new Promise((resolve, reject) => {
        registerByEmployer(employerInfo.email, employerInfo.name, employerInfo.password, employerInfo.phone, employerInfo.address, employerInfo.domain, employerInfo.scale, employerInfo.description).then(response => {
          // const token = response.message
          // setToken(token)
          // commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //
    RegisterByEmployee({ commit }, employeeInfo) {
      return new Promise((resolve, reject) => {
        console.log(employeeInfo)
        registerByEmployee(employeeInfo.email, employeeInfo.name, employeeInfo.password, employeeInfo.phone, employeeInfo.birthday, employeeInfo.gender, employeeInfo.address, employeeInfo.expect_salary, employeeInfo.expect_city, employeeInfo.edu_background, employeeInfo.work_experience, employeeInfo.description).then(response => {
          // const token = response.message
          // setToken(token)
          // commit('SET_TOKEN', token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          const data = response.data
          if (data.userType === 0 || data.userType === 1 || data.userType === 2) { //
            commit('SET_USERTYPE', data.userType)
          } else {
            reject('getInfo: userType must be 0 or 1 or 2 !')
          }
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif')
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    //
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_USERTYPE', '')
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    //
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
