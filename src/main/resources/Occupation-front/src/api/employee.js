import request from '@/utils/request'

export function getRecommendJobList() {
  return request({
    url: '/employee/job/list',
    method: 'get'
  })
}

export function getProfile() {
  return request({
    url: '/employee/profile',
    method: 'get'
  })
}

export function modifyProfile() {
  return request({
    url: '/employee/profile',
    method: 'post'
  })
}

export function getNotification() {
  return request({
    url: '/employee/notification',
    method: 'get'
  })
}
