<!-- ./pages/index.vue -->

<template>
  <div class="columns is-multiline">
    <div v-for="(info, index) in infos" :key="info.id" class="column is-4">
      <div class="card">
        <header class="card-header">
          <p class="card-header-title is-centered">{{ info.name }}</p>
        </header>
        <div class="card-content">
          <figure class="image is-3by2">
            <img :src="`${info.url}`" />
          </figure>
        </div>
        <footer>
          <div class="producto">
            <nuxt-link :to="`/info/${info.id}`" class="card-footer-item">
              <button class="button is-dark">leer mas</button>
            </nuxt-link>

            <button v-if="qtyCart === 0" class="btn" @click="addToCart(index)">
              Agregar Al carrito
            </button>
            <div class="botones">
              <button class="btns" @click="inc(index)">+</button>
              <button class="btns" @click="dec(index)">-</button>
            </div>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import logica from "~/static/logica";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      infos: [],
      shared: logica.data,
    };
  },
  computed: {
    ...mapGetters({
      typeArticle: "app/getTypeArticle",
    }),
    qtyCart() {
      var busqueda = _.find(this.shared.cart, ["id", this.infos.id]);
      if (typeof busqueda == "object") {
        return busqueda.qty;
      } else {
        return 0;
      }
    },
  },
  async mounted() {
    try {
      if (this.typeArticle == "all") {
        var response = await this.$axios.$get("articles/get/all");
        this.infos = response;
      }else {
          var response = await this.$axios.$get(
            "articles/get/byCategory?category=" + this.typeArticle
          );
          this.infos = response;
        }
      this.$root.$on("TypeArticle", async () => {
        if (this.typeArticle == "all") {
          var response = await this.$axios.$get("articles/get/all");
          this.infos = response;
        } else {
          var response = await this.$axios.$get(
            "articles/get/byCategory?category=" + this.typeArticle
          );
          this.infos = response;
        }
      });
    } catch (e) {
      console.error("SOMETHING WENT WRONG :" + e);
    }
  },
  methods: {
    addToCart(index) {
      logica.add(this.infos[index]);
    },
    inc(index) {
      logica.inc(this.infos[index]);
    },
    dec(index) {
      logica.dec(this.infos[index]);
    },
  },
};
</script>
<style>
.infos {
  height: 100%;
  width: 100%;
}
.main-content {
  margin: 20px 0;
}
.btn {
  background-color: #42b992;
  width: 100%;
  border: none;
  color: white;
  padding: 0.5em;
}
.botones {
  display: flex;
}
.btns {
  width: 50%;
}
.producto {
  background: white;
  margin: 0 0.5em;
}
</style>
