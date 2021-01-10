<template>
  <div class="todo">
    <h1>Carrito de compra</h1>
    <div v-for="item in items" :key="item.id" class="car_pro">
      <div class="imgsd"><img :src="item.url" /></div>
      <div class="cuerpo">
        <div class="nomc">{{ item.name }}</div>
        <br />
        <div class="cant">x {{ item.qty }}</div>
      </div>
      <div class="precio">$ {{ item.qty * item.precio }}</div>
    </div>

    <div class="total">$ {{ total }}</div>
    <button class="button is-dark" @click="guardar">guardar</button>
  </div>
</template>
<script>
import logica from '~/static/logica'
import _ from 'lodash'
import { mapGetters } from 'vuex'
import Vue from 'vue'
export default {
  data() {
    return {
      items: logica.data.cart,
      SaveSucces: {
        group: 'notifGroup',
        type: 'success',
        title: 'Compra de articulos',
        text: 'Su compra se guardo con exito'
      },
      SaveWar: {
        group: 'notifGroup',
        type: 'warning',
        title: 'Compra de articulos',
        text: 'Debe iniciar session para poder guardar su compra'
      }
    }
  },
  computed: {
    total() {
      return _.sumBy(this.items, function(it) {
        return it.precio * it.qty
      })
    },
    ...mapGetters({
      user: 'app/getUser'
    })
  },
  mounted() {
    this.$root.$on('removeLogica', () => {
      logica.remove()
      this.items = logica.data.cart
    })
  },
  methods: {
    async guardar() {
      if (this.user.username == null) {
        Vue.notify(this.SaveWar)
        this.$router.push('/login')
      } else if (this.total > 0) {
        const venta = {
          celular: this.user.celular,
          direccion: this.user.direccion,
          nombreComprador: this.user.nombre,
          productos: [],
          status: 'PROCESO',
          total: this.total
        }
        const productos = []
        this.items.forEach(item => {
          productos.push({
            cantidad: item.qty,
            idArticles: item.id
          })
        })
        venta.productos = productos
        console.log(venta)
        console.log(this.total)
        var response = await this.$axios.$post('ventas/save', venta)
        console.log(response)
        logica.remove()
        this.items = logica.data.cart
        Vue.notify(this.SaveSucces)
      } else {
        Vue.notify({
          group: 'notifGroup',
          type: 'warning',
          title: 'Compra de articulos',
          text: 'Debe seleccionar almenos un articulo'
        })
      }
    }
  }
}
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
