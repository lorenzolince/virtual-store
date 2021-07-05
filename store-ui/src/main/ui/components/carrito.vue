<template>
  <v-dialog v-model="dialog" width="500">
    <template v-slot:activator="{ on }">
      <v-badge color="green" :content="totalCantidad ? totalCantidad : '0'">
        <v-btn id="idCarrito" x-large color="primary" fab dark v-on="on">
          <v-icon>mdi-cart-outline</v-icon>
        </v-btn>
      </v-badge>
    </template>
    <template v-slot:default="dialog">
      <v-toolbar color="primary" dark>
        <v-btn icon dark @click="dialog.value = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-card-text>
          <div class="text-h4 pa-12">Carrito de compra</div>
        </v-card-text>
      </v-toolbar>
      <v-card class="mx-auto" outlined>
        <v-list>
          <v-list-item v-for="item in items" :key="item.id">
            <v-list-item-avatar tile size="80">
              <img :src="item.url" />
            </v-list-item-avatar>
            <v-list-item-title>{{ item.name }}</v-list-item-title>
            <v-list-item-title>x {{ item.qty }}</v-list-item-title>
            <v-list-item-title
              >$ {{ item.qty * item.precio }}</v-list-item-title
            >
          </v-list-item>
          <v-list-item-title class="text-h5 mb-1">
            total: $ {{ total }}
          </v-list-item-title>
        </v-list>
        <v-card-actions>
          <v-btn class="mx-2" dark color="orange" @click="guardar">
            guardar
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
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
    totalCantidad() {
      return _.sumBy(this.items, function(it) {
        return it.qty
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
        this.$router.push('/register')
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
