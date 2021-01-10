<template>
  <div class="infos">
    <notifications group="compraSave" position="top right">
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
    <section class="hero is-medium is-dark is-bold">
      <div class="container">
        <h1 class="title">Tienda Virtual</h1>
        <h2 class="subtitle">Bienvenidos a la tienda virtual😃</h2>
      </div>
    </section>
    <section class="main-content">
      <div class="container">
        <h1 class="title has-text-centered">
          Seleccione la imagen para ver el detalle
        </h1>
        <div class="global">
          <div class="prod">
            <producto />
          </div>
          <div class="carrito">
            <carrito-compra></carrito-compra>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import producto from '~/components/producto.vue'
import CarritoCompra from '~/components/carrito.vue'
export default {
  components: { producto, CarritoCompra },
  data() {
    return {}
  }
}
</script>
<style>
.global {
  display: flex;
  justify-content: space-between;
}
.prod {
  width: 80%;
  justify-content: space-between;
  flex-wrap: wrap;
}
.carrito {
  width: 18%;
  background-color: #eeeeee;
}
</style>
