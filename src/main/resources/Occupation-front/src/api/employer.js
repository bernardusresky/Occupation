import request from '@/utils/request'

export function getMyJobList() {
  return request({
    url: '/employer/job/list',
    method: 'get'
  })
}

export function getProfile() {
  return request({
    url: '/employer/profile',
    method: 'get'
  })
}

export function modifyProfile() {
  return request({
    url: '/employer/profile',
    method: 'post'
  })
}

export function getNotification() {
  return request({
    url: '/employer/notification',
    method: 'get'
  })
}

export function getApplicants() {
  return request({
    url: '/employer/applicants',
    method: 'get'
  })
}
