<template>
  <div class="infos">
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
              <producto/>
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
import producto from "~/components/producto.vue";
import CarritoCompra from "~/components/carrito.vue";
export default {
  components: { producto, CarritoCompra },
  data() {
    return {
      infos: [],
      productos: [
         { id:1, nombre:'primer producto', precio: 45, img:'producto.jpg' },
         { id:2, nombre:'segundo producto', precio: 20, img:'producto.jpg' },
         { id:3, nombre:'tercer producto', precio: 15, img:'producto.jpg' },
         { id:4, nombre:'cuarto producto', precio: 25, img:'producto.jpg' },
         { id:5, nombre:'quinto producto', precio: 20, img:'producto.jpg' },
         { id:6, nombre:'sexto producto', precio: 30, img:'producto.jpg' }
      ],
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
