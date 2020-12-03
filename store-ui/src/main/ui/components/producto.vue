<!-- ./pages/index.vue -->

<template>
  <div class="columns is-multiline">
    <div v-for="info in infos" :key="info.id" class="column is-4">
      <div class="card">
        <header class="card-header">
          <p class="card-header-title is-centered">{{ info.name }}</p>
        </header>
        <div class="card-content">
          <figure class="image is-3by2">
            <img :src="`${info.url}`" />
          </figure>
        </div>
        <footer >
           <div class="producto">
               
            <nuxt-link :to="`/info/${info.id}`" class="card-footer-item">
              <button class="button is-dark">leer mas</button>
            </nuxt-link>
             
            <button class="btn">Agregar Al carrito</button>
            <div class="botones">
              <button class="btns" @click="inc">+</button>
              <button class="btns" @click="dec">-</button>
            </div>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      infos: [],
    };
  },
  async mounted() {
    try {
      var response = await this.$axios.$get("articles/get/all");
      console.log(response);

      this.infos = response;
    } catch (e) {
      console.error("SOMETHING WENT WRONG :" + e);
    }
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
