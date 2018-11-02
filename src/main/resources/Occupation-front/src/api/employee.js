import request from '@/utils/request'
import store from '../store'
export function getRecommendJobList() {
  return request({
    url: '/job/list',
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
    url: '/employee',
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
export function report(email) {
  return request({
    url: '/user/report/' + email,
    method: 'put'
  })
}
export function getInteractionList() {
  return request({
    url: '/interaction/list',
    method: 'get'
  })
}

export function accept(interId, isAccept) {
  return request({
    url: '/interaction/accept/' + interId + '/' + isAccept,
    method: 'put'
  })
}

export function apply(id, ere) {
  return request({
    url: '/interaction',
    method: 'post',
    data: {
      interId: 0,
      jobId: id,
      createTime: '',
      employeeEmail: store.getters.token,
      employerEmail: ere,
      checkStatus: -1
    }
  })
}
