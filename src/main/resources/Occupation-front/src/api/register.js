import request from '@/utils/request'

export function registerByAdmin(email, password) {
  return request({
    url: '/user',
    method: 'post',
    data: {
      email,
      password,
      userType: 0
    }
  })
}

export function registerByEmployer(email, name, password, phone, address, domain, scale, description) {
  return request({
    url: '/employer',
    method: 'post',
    data: {
      email,
      name,
      password,
      phone,
      address,
      domain,
      scale,
      description,
      userType: 1
    }
  })
}

export function registerByEmployee(email, name, password, phone, birthday, gender, address, expect_salary, expect_city, edu_background, work_experience, description) {
  return request({
    url: '/employee',
    method: 'post',
    data: {
      email,
      name,
      password,
      phone,
      birthday,
      gender: (gender === 'male') ? 1 : 2,
      address,
      expectSalary: expect_salary,
      expectCity: expect_city,
      eduBackground: edu_background,
      workExperience: work_experience,
      description,
      userType: 2
    }
  })
}
