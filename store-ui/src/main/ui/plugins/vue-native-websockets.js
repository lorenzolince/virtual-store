import Vue from 'vue'
import VueNativeSock from 'vue-native-websocket'
//import store from './store/notifications'
import store from '@/store/notifications'

Vue.use(VueNativeSock, process.env.sockItinerary)

/* Vue.use(VueNativeSock, 'wss://vm-miradev-ge01.canal.acp:6143/notification')

Vue.use(VueNativeSock, 'wss://vm-miradev-ge01.canal.acp:6143/Itinerary', {
  store: store
})
Vue.use(VueNativeSock, 'wss://vm-miradev-ge01.canal.acp:6143/notification', {
  store: store
})
 */
