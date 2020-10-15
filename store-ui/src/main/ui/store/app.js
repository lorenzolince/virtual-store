import Vuex from "vuex";

export const state = () => ({});

export const mutations = {
  setBubbles(state, activeBubble) {
    state.bubbles = activeBubble;
  }
};

export const getters = {
  getDefaultAccessView: state => {
    return state.accessView ? state.accessView[0] : null;
  }
};

export const actions = {
  addBubble(vuexContext, bubble) {
    vuexContext.commit("addBubble", bubble);
  }
};

export const menu = [
  {
    name: "home",
    url: "/"
  },
  {
    name: "login",
    url: "/login"
  },
  {
    name: "articles",
    url: "/Articles"
  }
];
