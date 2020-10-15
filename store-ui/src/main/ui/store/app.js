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

export const infos = [
  {
    _id: "1",
    name: "Video play",
    url: "https://i.ytimg.com/vi/tgbNymZ7vqY/maxresdefault.jpg",
    video: "https://www.youtube.com/embed/tgbNymZ7vqY",
    description: "Mascotas"
  },
  {
    _id: "2",
    name: "test",
    url: "http://localhost:8080/store/images/176.png",
    video: null,
    description: "test"
  },
  {
    _id: "3",
    name: "test",
    url: "http://localhost:8080/store/images/d7.png",
    video: null,
    description: "test"
  },

  {
    _id: "4",
    name: "test",
    url: "http://localhost:8080/store/images/mujer.png",
    video: null,
    description: "test"
  }
];

export const menu = [
  {
    name: "home",
    url: "/"
  },
  {
    name: "login",
    url: "/login"
  }
];
