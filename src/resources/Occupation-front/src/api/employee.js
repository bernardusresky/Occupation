import request from '@/utils/request'

export function getRecommendJobList() {
  return request({
    url: '/employee/job/list',
    method: 'get'
  })
}
