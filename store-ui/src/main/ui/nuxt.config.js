import colors from 'vuetify/es5/util/colors'
export default {
    mode: 'spa',
  router: {
    base: '/mj', 
  }, 

  publicRuntimeConfig: {
    urlServer: process.env.SERVER_HOST,
    axios: {
      baseURL: process.env.SERVER_HOST+'/api'
    }  
  },

  head: {
    title: 'MJ markets.pty',
   meta: [ 
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content: process.env.npm_package_description || ''
      }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/mj/logo.jpg' },
      {
        rel: 'stylesheet',
        href: 'https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css'
      },
      { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&display=swap' }
    ]
  },

 
  css: [],

  plugins: [
    '@/plugins/vue-notifications',
    { src: '~/plugins/vuex-persist', ssr: false }
  ],
  loading: { color: '#3B8070' },

  
  buildModules: [

    '@nuxtjs/eslint-module',
    '@nuxtjs/vuetify'
  ],

  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/pwa',
  ],

  
  vuetify: {
    customVariables: ['~/assets/variables.scss'],
    theme: {
      dark: false,
      themes: {
        dark: {
          primary: colors.blue.darken2,
          accent: colors.grey.darken3,
          secondary: colors.amber.darken3,
          info: colors.teal.lighten1,
          warning: colors.amber.base,
          error: colors.deepOrange.accent4,
          success: colors.green.accent3
        }
      }
    }
  },
 
  build: {
    extend(config, ctx) {
    
      if (ctx.isDev && ctx.isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/,
          options: {
            fix: true
          }
        })
      }
    }
  }
}

