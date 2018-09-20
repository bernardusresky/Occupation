import request from '@/utils/request'

export function getRecommendJobList() {
  return request({
    url: '/job/list',
    method: 'get'
  })
}
