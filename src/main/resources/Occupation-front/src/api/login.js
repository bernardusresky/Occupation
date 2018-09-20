import request from '@/utils/request'

export function login(email, password) {
  return request({
    url: '/user/login',
    method: 'post',
    data: {
      email,
      password
    }
  })
}

export function getInfo() {
  return request({
    url: '/user',
    method: 'get'
    // params: {
    //   email: token.split('&')[0],
    //   userType: token.split('&')[1]
    // }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function reset_password(email, password) {
  return request({
    url: '/user',
    method: 'put',
    data: {
      email,
      password
    }
  })
}
