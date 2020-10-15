export default {
    mode: 'spa',
    generate: {
    dir: 'dist/static'
  },

  router: {
    base: '/store', 
  }, 

    env: {
    urlServer: 'http://192.168.0.185:8080/store/'
  },

  head: {
    titleTemplate: '%s store',
    title: 'store',
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
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      {
        rel: 'stylesheet',
        href: 'https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css'
      },
      { rel: 'stylesheet', href: 'https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&display=swap' }
    ]
  },

 
  css: [],

  loading: { color: '#3B8070' },

  
  buildModules: [

    '@nuxtjs/eslint-module',
    '@nuxtjs/vuetify'
  ],

  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/pwa'
  ],

  axios: {},
 
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

