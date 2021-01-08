import Vuex from 'vuex'
import axios from 'axios'

export const state = () => ({
  islogin: false,
  role: [],
  typeArticle: 'all',
  token:null,
  user: {
    username: null,
    nombre: null,
    celular: null,
    direccion: null,
    Avatar:null
  },
  allLinks: [
    {
      id: 1,
      text: 'all',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    },
    {
      id: 2,
      text: 'hogar',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    },
    {
      id: 3,
      text: 'belleza',
      icon: 'mdi-view-dashboard',
      url: '/',
      target: 'to'
    },
    {
      id: 4,
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
  setDefaultState(state) {
    state.islogin= false
    state.role= []
    state.user= {
        username: null,
        nombre: null,
        celular: null,
        direccion: null,
        Avatar:null
      }
      state.allLinks= [
        {
          id: 1,
          text: 'all',
          icon: 'mdi-view-dashboard',
          url: '/',
          target: 'to'
        },
        {
          id: 2,
          text: 'hogar',
          icon: 'mdi-view-dashboard',
          url: '/',
          target: 'to'
        },
        {
          id: 3,
          text: 'belleza',
          icon: 'mdi-view-dashboard',
          url: '/',
          target: 'to'
        },
        {
          id: 4,
          text: 'salud',
          icon: 'mdi-view-dashboard',
          url: '/',
          target: 'to'
        }
      ],
      state.menu= [
        {
          name: 'home',
          url: '/'
        },
        {
          name: 'login',
          url: '/login'
        }
      ]
  },
  setLinkMenu(state) {
    state.menu=[
      {
        name: 'home',
        url: '/'
      }
    ]
  },
  setTypeArticle(state,typeArticle) {
    state.typeArticle=typeArticle
  },
  setLinkMenuDefault(state) {
    state.menu=[
      {
        name: 'home',
        url: '/'
      },
      {
        name: 'articles',
        url: '/articles'
      }
      ,
      {
        name: 'ventas',
        url: '/ventas'
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
    user.Avatar=user.nombre.substr(0, 2).toUpperCase()
    state.user = user
  }, setToken(state, token) {
    state.token = token
  }
  
}

export const getters = {
  getDefaultExternalLink: state => {
    return state.allLinks ? state.allLinks[0] : null
  },
  getUser: state => {
    return state.user
  },
  getTypeArticle: state => {
    return state.typeArticle
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
  setToken(vuexContext, token) {
    vuexContext.commit('setToken', token)
  },
  setLinkMenuDefault(vuexContext) {
    vuexContext.commit('setLinkMenuDefault')
  },
  setDefaultState(vuexContext) {
    vuexContext.commit('setDefaultState')
  },
  setTypeArticle(vuexContext,typeArticle) {
    vuexContext.commit('setTypeArticle',typeArticle)
  }
}
