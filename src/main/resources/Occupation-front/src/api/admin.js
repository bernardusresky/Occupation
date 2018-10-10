import request from '@/utils/request'
import store from '../store'
export function getEmployeeList() {
  return request({
    url: '/user/employee_list',
    method: 'get'
  })
}
export function saveEmployee(detail) {
  return request({
    url: '/employee',
    method: 'put',
    data: detail
  })
}
export function deleteEmployee(email) {
  return request({
    url: '/employee/' + email,
    method: 'delete'
  })
}
export function getEmployerList() {
  return request({
    url: '/user/employer_list',
    method: 'get'
  })
}
export function saveEmployer(detail) {
  return request({
    url: '/employer',
    method: 'put',
    data: detail
  })
}
export function deleteEmployer(email) {
  return request({
    url: '/employer/' + email,
    method: 'delete'
  })
}
export function getNotification() {
  return request({
    url: '/notification/list',
    method: 'get'
  })
}
export function newNotification(newNotification) {
  return request({
    url: '/notification',
    method: 'post',
    data: {
      noteId: 0,
      content: newNotification.content,
      targetEmail: newNotification.to,
      crtEmail: store.getters.token,
      createTime: ''
    }
  })
}
export function banEmployee(detail) {
  return request({
    url: '/employee',
    method: 'put',
    data: detail
  })
}
export function banEmployer(detail) {
  return request({
    url: '/employer',
    method: 'put',
    data: detail
  })
}

