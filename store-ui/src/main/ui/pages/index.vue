<!-- ./pages/index.vue -->

<template>
  <!-- begin header -->
  <div class="infos">
    <section class="hero is-medium is-dark is-bold">
      <div class="container">
        <h1 class="title">Tienda Virtual</h1>
        <h2 class="subtitle">
          Bienvenidos a la tienda virtual😃
        </h2>
      </div>
    </section>
    <section class="main-content">
      <div class="container">
        <h1 class="title has-text-centered">
          Seleccione la imagen para ver el detalle
        </h1>
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
              <footer class="card-footer">
                <nuxt-link :to="`/info/${info.id}`" class="card-footer-item">
                  <button class="button is-dark">
                    leer mas
                  </button>
                </nuxt-link>
              </footer>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
    async asyncData(context) {
    try {
      return await fetch(process.env.urlServer+"api/articles/get/all")
        .then(res => res.json())
        .then(data => {
          return { infos: data };
        });
    } catch (e) {
      console.error("SOMETHING WENT WRONG :" + e);
    }
  },
  data() {
    return {
      infos: []
    };
  }
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
</style>
