<!-- ./layouts/default.vue -->

<template>
  <v-app dark>
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      :clipped="clipped"
      width="500"
      fixed
      app
    >
      <v-img
        src="/mj/logo.jpeg"
        aspect-ratio="1"
        max-height="75"
        max-width="200"
        position="center center"
        contain
        class="ma-2"
      ></v-img>
      <div class="autocomplete">
        <v-autocomplete
          v-if="allLinks"
          :key="$route.fullPath"
          v-model="autoCompleteLayout"
          :items="allLinks"
          dense
          filled
          label="Categorias"
          @change="changeArticle(autoCompleteLayout)"
        ></v-autocomplete>
      </div>
      <v-tabs v-model="menuTab" background-color="dark" dark grow>
        <v-tab key="menu">Menu</v-tab>
      </v-tabs>
      <v-tabs-items v-model="menuTab">
        <v-tab-item key="menu">
          <v-list v-for="category in links" :key="category.id">
            <v-list-item :[category.target]="category.url">
              <v-list-item-action>
                <v-icon>{{ category.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>{{ category.text }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-tab-item>
      </v-tabs-items>
    </v-navigation-drawer>
    <v-app-bar
      height="100"
      dark
      shrink-on-scroll
      prominent
      fade-img-on-scroll
      scroll-target="#scrolling-techniques-3"
      app
      color="primary"
      src="/mj/header.jpeg"
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title class="d-flex d-sm-none" v-text="titleXs" />
      <v-toolbar-title class="d-none d-sm-flex" v-text="title" />
      <v-spacer />
      <v-spacer />
      <v-spacer />
      <v-spacer />
      <v-spacer />
      <v-row>
        <v-col cols="2">
          <carrito />
        </v-col>
      </v-row>
      <Login />
    </v-app-bar>
    <v-main>
      <v-container fill-height fluid>
        <notifications group="notifGroup" position="top right">
          <template slot="body" slot-scope="props">
            <v-alert
              dismissible
              close-icon="mdi-delete"
              border="left"
              elevation="2"
              colored-border
              :type="props.item.type"
            >
              <h1>{{ props.item.title }}</h1>
              <h2>{{ props.item.text }}</h2>
            </v-alert>
          </template>
        </notifications>
        <nuxt />
      </v-container>
    </v-main>
    <!--   <footer class="footer home-footer has-background-black">
      <v-row justify="end">
        <v-col class="text-right pr-2" cols="1">
          <a href="https://www.linkedin.com/in/lorenzo-lince" target="_blank"
            >&copy;Llince</a
          >
        </v-col>
      </v-row>
    </footer>
  end footer -->
  </v-app>
</template>
<script>
import { mapState, mapGetters } from 'vuex'
import Login from '~/components/Login.vue'
import carrito from '~/components/carrito.vue'
export default {
  components: { Login, carrito },
  data() {
    return {
      drawer: false,
      miniVariant: false,
      clipped: false,
      menuTab: false,
      autoCompleteLayout: '',
      titleXs: 'MJ',
      title: 'MJ markets.pty'
    }
  },
  computed: {
    ...mapState({
      allLinks: state => state.app.allLinks,
      links: state => state.app.menu,
      token: state => state.app.token
    }),
    ...mapGetters({
      menuCategorys: 'app/getDefaultExternalLink'
    })
  },
  mounted() {
    this.$axios.setHeader('Authorization', this.token)
    this.$axios.setToken(this.token)
  },
  methods: {
    async changeArticle(text) {
      try {
        console.log(text)
        this.$store.dispatch('app/setTypeArticle', text)
        this.$root.$emit('TypeArticle')
      } catch (e) {
        console.log(e)
      }
    }
  }
}
</script>
<style>
/** This its a hack for default container div from ESRI */
.container {
  padding: 0px;
}
.autocomplete {
  margin: auto;
  width: 97%;
  padding: 1px;
}
.main-content {
  margin: 20px 0;
}
body {
  margin: 0;
  padding: 0;
  font-size: 15px;
  font-family: 'Lucida Grande', 'Helvetica Nueue', Arial, sans-serif;
}

.home-footer {
  margin-top: 47.5vh;
  height: 10px;
}

.customNav {
  background-color: #333;
  border: 1px solid #333;
  color: #fff;
  display: block;
  margin: 0;
  overflow: hidden;
}
.customNav ul {
  margin: 0;
  padding: 0;
  list-style: none;
}
.customNav ul li {
  margin: 0;
  display: inline-block;
  list-style-type: none;
  transition: all 0.2s;
}

.customNav > ul > li > a {
  color: #aaa;
  display: block;
  line-height: 2em;
  padding: 0.5em 2em;
  text-decoration: none;
}
</style>
