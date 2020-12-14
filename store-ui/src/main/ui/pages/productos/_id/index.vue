<template>
  <div class="container">
    <br />
    <section class="hero is-medium is-dark is-bold">
      <div class="container">
        <h1 class="title">Productos</h1>
      </div>
    </section>
    <v-data-table
      :headers="headers"
      :items="productos"
      item-key="id"
      class="elevation-1"
      :search="search"
      :custom-filter="filterOnlyCapsText"
    >
      <template v-slot:top>
        <v-text-field
          v-model="search"
          label="Search (UPPER CASE ONLY)"
          class="mx-4"
        ></v-text-field>
      </template>
    </v-data-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      search: "",
      cantidad: "",
      productos: [],
    };
  },
  computed: {
    headers() {
      return [
        { text: "id", value: "id" },
        {
          text: "producto",
          align: "start",
          sortable: false,
          value: "producto",
        },
        { text: "cantidad", value: "cantidad" },
        { text: "precio_unitario", value: "precio_unitario" },
        { text: "precio_por_cantidad", value: "precio_por_cantidad" },
        { text: "precio_real", value: "precio_real" },
        { text: "ganancia_unitaria", value: "ganancia_unitaria" },
        { text: "ganancia_por_cantidad", value: "ganancia_por_cantidad" },
      ];
    },
  },
  async mounted() {
    try {
      var response = await this.$axios.$get(
        "ventas/get/productos/" + this.$route.params.id
      );
      console.log(response);

      this.productos = response;
    } catch (e) {
      console.error("SOMETHING WENT WRONG :" + e);
    }
  },
  methods: {
    filterOnlyCapsText(value, search, item) {
      return (
        value != null &&
        search != null &&
        typeof value === "string" &&
        value.toString().toLocaleUpperCase().indexOf(search) !== -1
      );
    },
  },
};
</script>