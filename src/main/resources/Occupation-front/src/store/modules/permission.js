import { asyncRouterMap, constantRouterMap } from '@/router'

/**
 *
 * @param userType
 * @param route
 */
function hasPermission(userType, route) {
  // console.log(userType + '+++++' + route.meta.userType)
  if (route.meta && (route.meta.userType === 1 || route.meta.userType === 0 || route.meta.userType === 2)) {
  // return userType.some(role => route.meta.userType.indexOf(role) >= 0)
    return userType === route.meta.userType
  } else {
    return true
  }
}

/**
 *
 * @param asyncRouterMap
 * @param userType
 */
function filterAsyncRouter(asyncRouterMap, userType) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasPermission(userType, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, userType)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { userType } = data
        const accessedRouters = filterAsyncRouter(asyncRouterMap, userType)
        // if (userType === 0 || userType === 1 || userType === 2) {
        //   accessedRouters = asyncRouterMap
        // } else {
        //   accessedRouters = filterAsyncRouter(asyncRouterMap, userType)
        // }
        // console.log(accessedRouters)
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
