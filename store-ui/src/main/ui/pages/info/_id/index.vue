<!-- ./pages/info/_id/index.vue -->

<template>
  <div class="container">
    <div class="card">
      <header class="card-header">
        <nuxt-link to="/" class="navbar-item is-half">
          <img
            src="https://assets.dryicons.com/uploads/icon/svg/12647/610ab76e-1af7-4da0-a938-5261e80a436f.svg"
            width="25"
            height="25"
          />
        </nuxt-link>

        <p class="card-header-title is-centered">{{ info.name }}</p>
      </header>
      <div class="card-content has-background-dark">
        <div v-show="info.video == null" class="card-content">
          <figure class="image is-3by2">
            <img :src="`${info.url}`" />
          </figure>
        </div>
      </div>
      <iframe
        v-show="info.video != null"
        width="100%"
        height="560px"
        :src="`${info.video}`"
        allowfullscreen
        style="border: none"
      ></iframe>

      <br />
      <h4 class="title is-5 is-marginless">
        <p class="has-text-centered">
          <strong>{{ info.description }}</strong>
        </p>
        <br />
      </h4>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      info: [],
    };
  },
  async mounted() {
    try {
      var response = await this.$axios.$get("articles/get?id="+this.$route.params.id
      );
      this.info = response;
    } catch (e) {
      console.error("SOMETHING WENT WRONG :" + e);
    }
  },
};
</script>
