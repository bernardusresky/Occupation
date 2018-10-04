import request from '@/utils/request'

export function getEmployeeList() {
  return request({
    url: '/employee/list',
    method: 'get'
  })
}
export function saveEmployee() {
  return request({
    url: '/employee/save',
    method: 'post'
  })
}
export function deleteEmployee() {
  return request({
    url: '/employee/delete',
    method: 'post'
  })
}
export function addEmployee() {
  return request({
    url: '/employee/add',
    method: 'post'
  })
}
export function getEmployerList() {
  return request({
    url: '/employer/list',
    method: 'get'
  })
}
export function saveEmployer() {
  return request({
    url: '/employer/save',
    method: 'post'
  })
}
export function deleteEmployer() {
  return request({
    url: '/employer/delete',
    method: 'post'
  })
}
export function addEmployer() {
  return request({
    url: '/employer/add',
    method: 'post'
  })
}

export function getNotification() {
  return request({
    url: '/admin/notification',
    method: 'get'
  })
}
