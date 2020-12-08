import VuexPersistence from 'vuex-persist'

/*
This plugins can use globaly or independing by modules you can rewrite in modules
if you will make changes please check documentation or call for help xD
https://github.com/championswimmer/vuex-persist <---- check documentations
*/
export default ({ store }) => {
  new VuexPersistence({
    modules: ['user']
  }).plugin(store)
}
