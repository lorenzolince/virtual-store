import Vuex from "vuex";

export const state = () => ({
  islogin:false,
  role:[],
  allLinks: [
    {
      id: 1,
      text: "hogar",
      icon: 'mdi-view-dashboard',
      url:"/",
      target:'to'
    },
    {
      id: 2,
      text: "belleza",
      icon: 'mdi-view-dashboard',
      url:"/",
      target:'to'
    },
    {
      id: 3,
      text: "salud",
      icon: 'mdi-view-dashboard',
      url:"/",
      target:'to'
    }
  ],
  menu : [
    {
      name: "home",
      url: "/"
    },
    {
      name: "login",
      url: "/login"
    }
  ]
});

export const mutations = {
  setLinkMenu(state, link) {
    state.menu.push(link);
  },
  setLogin(state, islogin) {
    state.islogin=islogin;
  },
  setRole(state, role) {
    state.role=role;
  }
};

export const getters = {
  getDefaultExternalLink: state => {
    return state.allLinks ? state.allLinks[0] : null
  }
};

export const actions = {
  setLinkMenu(vuexContext, link) {
    vuexContext.commit("setLinkMenu", link);
  },
  setLogin(vuexContext, islogin) {
    vuexContext.commit("setLogin", islogin);
  },
  setRole(vuexContext, role) {
    vuexContext.commit("setLinkMenu", role);
  }
};


