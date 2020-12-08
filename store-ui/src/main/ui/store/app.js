import Vuex from 'vuex'

export const state = () => ({
  islogin: false,
  role: [],
  user: {
    username: null,
    nombre: null,
    celular: null,
    direccion: null
  },
  allLinks: [
    {
      id: 1,
      text: 'hogar',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    },
    {
      id: 2,
      text: 'belleza',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    },
    {
      id: 3,
      text: 'salud',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    }
  ],
  menu: [
    {
      name: 'home',
      url: '/'
    },
    {
      name: 'login',
      url: '/login'
    }
  ]
})

export const mutations = {
  setLinkMenu(state) {
    state.menu=[
      {
        name: 'home',
        url: '/'
      }
    ]
  },
  setLinkMenuDefault(state) {
    state.menu=[
      {
        name: 'home',
        url: '/'
      },
      {
        name: 'articles',
        url: '/Articles'
      }
    ]
  },
  setLogin(state, islogin) {
    state.islogin = islogin
  },
  setRole(state, role) {
    state.role = role
  },
  setUser(state, user) {
    state.user = user
  }
}

export const getters = {
  getDefaultExternalLink: state => {
    return state.allLinks ? state.allLinks[0] : null
  },
  getUser: state => {
    return state.user
  }
}

export const actions = {
  setLinkMenu(vuexContext, link) {
    vuexContext.commit('setLinkMenu')
  },
  setLogin(vuexContext, islogin) {
    vuexContext.commit('setLogin', islogin)
  },
  setRole(vuexContext, role) {
    vuexContext.commit('setRole', role)
  },
  setUser(vuexContext, user) {
    vuexContext.commit('setUser', user)
  },
  setLinkMenuDefault(vuexContext) {
    vuexContext.commit('setLinkMenuDefault')
  }
}
