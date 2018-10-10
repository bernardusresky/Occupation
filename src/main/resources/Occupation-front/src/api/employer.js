import request from '@/utils/request'
import store from '../store'
export function getMyJobList() {
  return request({
    url: '/job/list',
    method: 'get'
  })
}

export function modifyJob(job) {
  return request({
    url: '/job',
    method: 'put',
    data: {
      jobId: job.jobId,
      number: job.number,
      salary: job.salary,
      expectWorkExperience: job.expectWorkExperience,
      expectEduBackground: job.expectEduBackground,
      jobRequirement: job.jobRequirement,
      description: job.description,
      createTime: job.createTime,
      status: job.status,
      createEmployerEmail: job.employer.email
    }
  })
}

export function deleteJob(jobId) {
  return request({
    url: '/job/' + jobId,
    method: 'delete'
  })
}

export function newJob(job) {
  return request({
    url: '/job',
    method: 'post',
    data: job
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
    url: '/user/employee_list',
    method: 'get'
  })
}

export function report(email) {
  return request({
    url: '/user/report/' + email,
    method: 'put'
  })
}
