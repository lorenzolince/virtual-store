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
    </v-navigation-drawer>
    <v-app-bar fixed app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
      <v-toolbar-title class="d-none d-sm-flex">
        <nav class="customNav">
          <ul>
            <li></li>
            <li v-for="link in links" :key="link.name">
              <nuxt-link :to="link.url">{{ link.name }}</nuxt-link>
            </li>
          </ul>
        </nav>
      </v-toolbar-title>
      <v-spacer />
    </v-app-bar>
    <v-content>
      <v-container fill-height fluid>
        <nuxt />
        <notifications group="itineraries" position="top right" />
      </v-container>
    </v-content>
    <footer class="footer home-footer has-background-black">
      <v-row justify="end">
        <v-col class="text-right pr-2" cols="1">
          <a href="https://www.linkedin.com/in/lorenzo-lince" target="_blank"
            >&copy; Lorenzo Lince</a
          >
          <button @click="me" class="button is-dark">me</button>
        </v-col>
      </v-row>
    </footer>
    <!-- end footer -->
  </v-app>
</template>
<script>
import { menu } from "~/store/app";
export default {
  data() {
    return {
      links: menu,
      drawer: false,
      miniVariant: false,
      clipped: false,
    };
  },
  methods: {
    async me() {
      try {
        var response = await this.$axios.$get("user/me");
        console.log(response);
      } catch (e) {
        console.log(e);
      }
    },
  },
};
</script>
<style>
.main-content {
  margin: 20px 0;
}
body {
  margin: 0;
  padding: 0;
  font-size: 15px;
  font-family: "Lucida Grande", "Helvetica Nueue", Arial, sans-serif;
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
