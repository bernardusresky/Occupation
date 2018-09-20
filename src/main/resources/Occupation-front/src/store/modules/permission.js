import { asyncRouterMap, constantRouterMap } from '@/router'

/**
 *
 * @param userType
 * @param route
 */
function hasPermission(userType, route) {
  if (route.meta && route.meta.userType) {
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
        let accessedRouters
        if (userType === 0 || userType === 1 || userType === 2) {
          accessedRouters = asyncRouterMap
        } else {
          accessedRouters = filterAsyncRouter(asyncRouterMap, userType)
        }
        console.log(accessedRouters)
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
