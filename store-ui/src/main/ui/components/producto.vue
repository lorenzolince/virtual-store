<template>
  <div class="columns is-multiline">
    <div v-for="(info, index) in infos" :key="info.id" class="column is-2">
      <div class="card">
        <header class="card-header" style="background-color:blue">
          <p class="card-header-title is-centered">
            {{ info.name }}
          </p>
        </header>
        <div class="card-content">
          <figure class="image is-3by2">
            <nuxt-link :to="`/info/${info.id}`">
              <img :src="`${info.url}`" />
            </nuxt-link>
          </figure>
        </div>
        <footer>
          <div class="producto">
            <h2 class="subtitle">${{ info.precio }}</h2>
            <button v-if="qtyCart === 0" class="btn" @click="addToCart(index)">
              Añadir al carrito
            </button>
            <v-row>
              <v-col cols="8">
                <v-btn
                  class="mx-2"
                  fab
                  dark
                  small
                  color="indigo"
                  @click="inc(index)"
                >
                  <v-icon dark>
                    mdi-plus
                  </v-icon>
                </v-btn>
              </v-col>
              <v-col cols="4" sm="3">
                <v-btn
                  class="mx-2"
                  fab
                  dark
                  small
                  color="indigo"
                  @click="dec(index)"
                >
                  <v-icon dark>
                    mdi-minus
                  </v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </div>
        </footer>
      </div>
    </div>
  </div>
</template>

<script>
import logica from '~/static/logica'
import { mapGetters } from 'vuex'
import _ from 'lodash'
export default {
  data() {
    return {
      infos: [],
      shared: logica.data
    }
  },
  computed: {
    ...mapGetters({
      typeArticle: 'app/getTypeArticle'
    }),
    qtyCart() {
      var busqueda = _.find(this.shared.cart, ['id', this.infos.id])
      if (typeof busqueda == 'object') {
        return busqueda.qty
      } else {
        return 0
      }
    }
  },
  async mounted() {
    try {
      if (this.typeArticle == 'all') {
        this.infos = await this.$axios.$get('articles/get/all')
      } else {
        this.infos = await this.$axios.$get(
          'articles/get/byCategory?category=' + this.typeArticle
        )
      }
      this.$root.$on('TypeArticle', async () => {
        if (this.typeArticle == 'all') {
          this.infos = await this.$axios.$get('articles/get/all')
        } else {
          this.infos = await this.$axios.$get(
            'articles/get/byCategory?category=' + this.typeArticle
          )
        }
      })
    } catch (e) {
      console.error('SOMETHING WENT WRONG :' + e)
    }
  },
  methods: {
    addToCart(index) {
      logica.add(this.infos[index])
    },
    inc(index) {
      logica.inc(this.infos[index])
    },
    dec(index) {
      logica.dec(this.infos[index])
    }
  }
}
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
  background-color: blue;
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
