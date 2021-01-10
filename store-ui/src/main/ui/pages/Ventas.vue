<template>
  <div class="container">
    <br />
    <section class="hero is-medium is-dark is-bold">
      <div class="container">
        <h1 class="title">Ventas</h1>
      </div>
    </section>

    <v-data-table
      :headers="headers"
      :items="ventas"
      item-key="id"
      class="elevation-1"
      :search="search"
      :custom-filter="filterOnlyCapsText"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col class="d-flex" cols="12" sm="6">
                      <v-select
                        v-model="estado"
                        :items="itemsEstado"
                        label="Estado"
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="save"> Save </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
        <v-text-field
          v-model="search"
          label="Search (UPPER CASE ONLY)"
          class="mx-4"
        ></v-text-field>
      </template>
      <template v-slot:[`item.actions`]="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
        <nuxt-link :to="`/productos/${item.id}`">
          <v-icon small> mdi-arrow-right </v-icon>
        </nuxt-link>
      </template>
    </v-data-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      search: '',
      total_ganancia: '',
      formTitle: 'Actualizar estado de la Venta',
      dialog: false,
      ventas: [],
      itemsEstado: [],
      estado: null,
      idVenta: null
    }
  },
  computed: {
    headers() {
      return [
        { text: 'id', value: 'id' },
        {
          text: 'comprador',
          align: 'start',
          sortable: false,
          value: 'comprador'
        },

        { text: 'celular', value: 'celular' },
        { text: 'estado', value: 'estado' },
        { text: 'direccion', value: 'direccion' },
        { text: 'fecha', value: 'fecha' },
        { text: 'total_venta', value: 'total_venta' },
        {
          text: 'total_ganancia',
          value: 'total_ganancia',
          filter: value => {
            if (!this.total_ganancia) return true

            return value < parseInt(this.total_ganancia)
          }
        },
        { text: 'Actions', value: 'actions', sortable: false }
      ]
    }
  },
  async mounted() {
    try {
      var response = await this.$axios.$get('ventas/get/ventas')
      this.ventas = response
      response = await this.$axios.$get('ventas/get/estados')
      this.itemsEstado = response
      console.log(response)
    } catch (e) {
      console.error('SOMETHING WENT WRONG :' + e)
    }
  },
  methods: {
    filterOnlyCapsText(value, search, item) {
      return (
        value != null &&
        search != null &&
        typeof value === 'string' &&
        value
          .toString()
          .toLocaleUpperCase()
          .indexOf(search) !== -1
      )
    },
    editItem(item) {
      console.log(item.id)
      this.dialog = true
      this.estado = item.estado
      this.idVenta = item.id
    },
    close() {
      this.dialog = false
    },
    async save() {
      var response = await this.$axios.$post(
        'ventas/update/Estado/' + this.idVenta + '/' + this.estado
      )
      response = await this.$axios.$get('ventas/get/ventas')
      console.log(response)
      this.ventas = response
      this.close()
    }
  }
}
</script>
