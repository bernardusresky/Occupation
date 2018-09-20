const register = {
  state: {
    register_email: '',
    register_pwd: ''
  },

  mutations: {
    SET_RE: (state, register_email) => {
      state.register_email = register_email
    },
    SET_RP: (state, register_pwd) => {
      state.register_pwd = register_pwd
    }
  },

  actions: {
  }
}

export default register
