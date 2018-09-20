const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  userType: state => state.user.userType,
  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters,
  register_email: state => state.register.register_email,
  register_pwd: state => state.register.register_pwd
}
export default getters
