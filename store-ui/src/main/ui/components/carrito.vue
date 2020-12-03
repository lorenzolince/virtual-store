<template>
  <div class="todo">
    <h1>Carrito de compra</h1>
    <div v-for="item in items" :key="item.id" class="car_pro" >
      <div class="imgsd"><img src="/store/img/producto.jpg" /></div>
      <div class="cuerpo">
        <div class="nomc">{{ item.nombre }}</div>
        <br />
        <div class="cant">x {{ item.qty }}</div>
      </div>
      <div class="precio">Bs {{ item.qty * item.precio }}</div>
    </div>
   <div class="total">Bs {{ total }}</div>
  </div>
</template>
<script>
import logica from "~/static/logica";
import _ from "lodash";
export default {
  data() {
    return {
      items: logica.data.cart,
    };
  },
  computed: {
    total() {
      return _.sumBy(this.items, function (it) {
        return it.precio * it.qty;
      });
    },
  },
};
</script>
<style>
img {
  width: 100%;
}
.imgsd {
  width: 15%;
}
.car_pro {
  display: flex;
  justify-content: space-between;
  padding: 0.8em;
  background-color: #fff;
  box-shadow: 0 0 10px gray;
  margin-top: 0.4em;
}
.todo h1 {
  text-align: center;
  background-color: #2c23aa;
  padding: 0.5em;
  margin: 0;
  color: white;
}
.total {
  border: 1px solid black;
  padding: 0.5em;
}
</style>