import request from '@/utils/request'
import store from '../store'
export function getMyJobList() {
  return request({
    url: '/employer/job/list',
    method: 'get'
  })
}

export function getProfile() {
  return request({
    url: '/user',
    method: 'get'
  })
}

export function modifyProfile(detail) {
  return request({
    url: '/employer',
    method: 'put',
    data: detail
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

export function getApplicants() {
  return request({
    url: '/employer/applicants',
    method: 'get'
  })
}
