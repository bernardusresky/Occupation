import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '../store'
import { getToken } from '@/utils/auth'

//
const service = axios.create({
  headers: {
    // 'Content-Type': 'application/x-www-form-urlencoded'
    // 'Access-Control-Allow-Origin': 'http://localhost:8080'
  },
  withCredentials: true,
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 50000
})

//
service.interceptors.request.use(config => {
  if (store.getters.token) {
    config.headers['X-Token'] = getToken()
  }
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

//
service.interceptors.response.use(
  response => {
    /**
    *
    */
    const res = response.data
    if (res.code !== 1) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })

      //
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        MessageBox.confirm('you logout', 'make sure logout', {
          confirmButtonText: 're login',
          cancelButtonText: 'cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    console.log('err' + error)// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
