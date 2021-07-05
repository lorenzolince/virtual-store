<template>
  <div class="infos">
    <v-carousel
      cycle
      height="300"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item
        v-for="(item, i) in items"
        :key="i"
        :src="item.src"
        reverse-transition="fade-transition"
        transition="fade-transition"
      ></v-carousel-item>
    </v-carousel>
    <section class="hero is-medium is-danger is-bold">
      <div class="container">
        <v-spacer />
      </div>
    </section>
    <br />
    <div>
      <producto />
    </div>
  </div>
</template>

<script>
import producto from '~/components/producto.vue'
//import CarritoCompra from '~/components/carrito.vue'
export default {
  components: { producto },
  data() {
    return {
      items: [
        {
          src:
            'https://media.istockphoto.com/photos/online-shopping-concept-picture-id516827980?s=612x612'
        },
        {
          src:
            'https://www.marketingdirecto.com/wp-content/uploads/2018/05/tienda-online-1-300x174.jpg'
        },
        {
          src:
            'https://sofmaker.com/wp-content/uploads/2020/05/tienda-virtual_062017-F-300x300.jpg'
        },
        {
          src:
            'https://www.esan.edu.pe/conexion/actualidad/2020/02/21/1500x844_tiendas_virtuales.jpg'
        }
      ]
    }
  }
}
</script>
<style>
.global {
  display: flex;
  justify-content: space-between;
}
</style>
