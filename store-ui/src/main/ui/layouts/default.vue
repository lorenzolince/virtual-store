<!-- ./layouts/default.vue -->

<template>
  <v-app dark>
    <v-navigation-drawer
      v-model="drawer"
      :mini-variant="miniVariant"
      :clipped="clipped"
      width="350"
      fixed
      app
    >
      <v-tabs v-model="menuTab" background-color="dark" dark grow>
        <v-tab key="menu">Menu</v-tab>
      </v-tabs>
      <v-tabs-items v-model="menuTab">
        <v-tab-item key="menu">
          <v-list v-for="category in allLinks" :key="category.id" >
            <v-list-item :[category.target]="category.url">
              <v-list-item-action>
                <v-icon>{{ category.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title @click="me(category.text)">{{ category.text }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-tab-item>
      </v-tabs-items>
    </v-navigation-drawer>
    <v-app-bar :clipped-left="clipped" fixed app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title class="d-none d-sm-flex">
        <nav class="customNav">
          <ul>
            <li></li>
            <li v-for="link in links" :key="link.name">
              <nuxt-link :to="link.url" >{{ link.name }}</nuxt-link>
            </li>
          </ul>
        </nav>
      </v-toolbar-title>
      <v-spacer />
      <Login />
    </v-app-bar>
    <v-main>
      <v-container fill-height fluid>
        <nuxt />
      </v-container>
    </v-main>
    <footer class="footer home-footer has-background-black">
      <v-row justify="end">
        <v-col class="text-right pr-2" cols="1">
          <a href="https://www.linkedin.com/in/lorenzo-lince" target="_blank"
            >&copy;Llince</a
          >
        </v-col>
      </v-row>
    </footer>
    <!-- end footer -->
  </v-app>
</template>
<script>
import { mapState, mapGetters } from 'vuex'
import Login from '~/components/Login.vue'
export default {
  components: { Login },
  data() {
    return {
      drawer: false,
      miniVariant: false,
      clipped: false,
      menuTab:false
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
    async me(text) {
      try {
        console.log("###############  MENU ############## "+text)
        this.$store.dispatch('app/setTypeArticle', text)
        this.$root.$emit("TypeArticle")
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

<style lang="scss">
//border radio for elements
$border-radio: 8px;
//Esri bubble custom style
.esri-view {
  .esri-ui {
    /*styling the scroll bar*/
    /* width */
    ::-webkit-scrollbar {
      width: 5px;
    }
    /* Track */
    ::-webkit-scrollbar-track {
      background: #f1f1f1;
    }
    /* Handle */
    ::-webkit-scrollbar-thumb {
      background: #888;
    }
    /* Handle on hover */
    ::-webkit-scrollbar-thumb:hover {
      background: #555;
    }
    /*END styling the scroll bar*/
    .esri-ui-inner-container.esri-ui-manual-container {
      .esri-popup,
      .esri-popup--shadow {
        border-radius: $border-radio;
        -webkit-border-radius: $border-radio;
        -moz-border-radius: $border-radio;
      }

      .esri-ui-inner-container.esri-ui-manual-container:hover,
      .esri-popup:hover {
        cursor: pointer;
      }
      .esri-popup__header,
      .esri-popup__footer {
        // background-color: gainsboro;
        border-radius: $border-radio;
        -webkit-border-radius: $border-radio;
        -moz-border-radius: $border-radio;
      }
      .esri-popup__header-title {
        padding: 0px;
        font-weight: bolder;
      }
      .esri-popup__icon {
        padding: 0px;
      }
      /*add for header size
      Applys for close button
      */
      .esri-popup__button {
        padding-top: 0px;
        padding-bottom: 0px;
        padding-left: 5px;
        padding-right: 0px;
      }
      .esri-popup__pagination-next,
      .esri-popup__feature-menu-button,
      .esri-popup__pagination-previous {
        padding: 0px;
      }

      /* Avoids the round borders in the pointer */
      .esri-popup__pointer-direction {
        border-radius: 0px;
        -webkit-border-radius: 0px;
        -moz-border-radius: 0px;
        background-color: #444444;
      }

      .esri-popup {
        .esri-feature__content-element {
          padding: 0px;
        }
        .esri-popup__content {
          margin-top: 0px;
          margin-right: 5px;
          margin-bottom: 11px;
          margin-left: 5px;
          background-color: white;
        }
      }

      .esri-feature__field-header,
      .esri-feature__field-data {
        padding: 0.2em 0em;
        /*
    original
    padding: 0.5em 0.7em;
    */
      }

      .esri-popup__main-container {
        max-width: 280px;
        border-radius: $border-radio;
        -webkit-border-radius: $border-radio;
        -moz-border-radius: $border-radio;
      }
    }
  }
}

//Esri map Style

.esri-ui-inner-container.esri-ui-corner-container {
  .esri-ui-corner {
    .esri-component {
      box-shadow: none;
    }
    .v-input.v-text-field.v-select {
      border-radius: $border-radio;
      width: 56%;
    }
  }

  .esri-widget {
    &--button,
    &.esri-widget,
    &.esri-widget {
      border-radius: $border-radio;
      &.esri-zoom {
        border-radius: $border-radio;
      }
    }
    .esri-expand__container--expanded {
      .esri-expand__content {
        background-color: white;
        border-radius: $border-radio;
      }
    }
    .esri-layer-list__item.esri-layer-list__item--invisible-at-scale {
      border-radius: $border-radio;
    }
  }
  .esri-ui-bottom-right {
    flex-flow: column;
    .esri-component.esri-widget {
      &.esri-zoom {
        border-radius: $border-radio $border-radio 0px 0px;
      }
      &.esri-home {
        border-radius: 0px 0px $border-radio $border-radio;
      }
    }
  }
}

.theme--dark.v-navigation-drawer {
  background-color: #1e1e1e;
}
#app {
  font-family: 'Humanist';
}
</style>
