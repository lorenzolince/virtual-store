<template>
  <div class="container">
    <br />
    <section class="hero is-medium is-dark is-bold">
      <div class="container">
        <h1 class="title">Articles</h1>
      </div>
    </section>

    <v-data-table
      :headers="headers"
      :items="articles"
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
                <span class="headline">{{ formTitle }} {{ article.name }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="4" sm="6" md="4">
                      <v-text-field
                        v-model="article.name"
                        label="name"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4" sm="6" md="4">
                      <v-text-field
                        v-model="article.description"
                        label="description"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4" sm="6" md="4">
                      <v-text-field
                        v-model="article.precio"
                        label="precio"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="4" sm="6" md="4">
                      <v-text-field
                        v-model="article.precioReal"
                        label="precio_real"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="4" sm="6" md="4">
                      <v-select
                        v-model="article.categoria"
                        :items="categorias"
                        label="categoria"
                      ></v-select>
                    </v-col>
                    <v-col cols="4" sm="6" md="4">
                      <v-text-field
                        v-model="article.cantidad"
                        label="cantidad"
                      ></v-text-field>
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
        >
        </v-text-field>
        <nuxt-link :to="`/article/`"
          ><v-icon>mdi-plus-circle </v-icon>
        </nuxt-link>
      </template>
      <template v-slot:[`item.actions`]="{ item }">
        <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
        <v-icon small class="mr-2" @click="deleteArticle(item.id)"
          >mdi-delete
        </v-icon>
      </template>
    </v-data-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      search: '',
      cantidad: '',
      formTitle: 'Actualizar ',
      articles: [],
      categorias: ['hogar', 'belleza', 'salud'],
      article: {},
      dialog: false
    }
  },
  computed: {
    headers() {
      return [
        { text: 'id', value: 'id' },
        {
          name: 'name',
          align: 'start',
          sortable: false,
          value: 'comprador'
        },

        { text: 'description', value: 'description' },
        { text: 'precio', value: 'precio' },
        { text: 'precio_real', value: 'precioReal' },
        { text: 'categoria', value: 'categoria' },
        {
          text: 'cantidad',
          value: 'cantidad',
          filter: value => {
            if (!this.cantidad) return true

            return value < parseInt(this.cantidad)
          }
        },
        { text: 'Actions', value: 'actions', sortable: false }
      ]
    }
  },
  async mounted() {
    try {
      var response = await this.$axios.$get('articles/get/all')
      console.log(response)
      this.articles = response
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
    close() {
      this.dialog = false
    },
    editItem(item) {
      console.log(item)
      this.article = item
      this.dialog = true
    },
    async save() {
      console.log(this.article)
      var response = await this.$axios.$post('articles/save', this.article)
      console.log(response)
      this.close()
      this.refresh()
    },
    async deleteArticle(id) {
      console.log(id)
      var response = await this.$axios.$post('articles/delete?id=' + id)
      console.log(response)
      this.refresh()
    },
    async refresh() {
      var response = await this.$axios.$get('articles/get/all')
      console.log(response)
      this.articles = response
    }
  }
}
</script>
