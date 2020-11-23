<template>
  <div class="infos">
    <section class="main-content">
      <div class="container">
        <div class="card">
          <div class="card-content">
            <div class="column is-4 is-offset-4">
              <h2 class="title has-text-centered">Welcome back!</h2>
              <Notification v-if="error" :message="error" />
              <form method="post" @submit.prevent="login">
                <div class="field">
                  <label class="label">Email</label>
                  <div class="control">
                    <input
                      v-model="email"
                      type="email"
                      class="input"
                      name="email"
                      required
                    />
                  </div>
                </div>
                <div class="field">
                  <label class="label">Password</label>
                  <div class="control">
                    <input
                      v-model="password"
                      type="password"
                      class="input"
                      name="password"
                      required
                    />
                  </div>
                </div>
                <div class="control">
                  <button type="submit" class="button is-dark is-fullwidth">
                    Login
                  </button>
                </div>
              </form>
              <div class="has-text-centered" style="margin-top: 20px">
                <p>
                  Don't have an account?
                  <nuxt-link to="/register">Register</nuxt-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      error: null,
    };
  },

  methods: {
    async login() {
      try {
        var response = await this.$axios.post("auth/login", {
          username: this.email,
          password: this.password,
        });
        console.log(response);
        this.$axios.setHeader("Authorization", response.data.token);
        this.$axios.setToken(response.data.token);
        this.$router.push("/");
        this.$store.dispatch("app/setLinkMenu", {
          name: "articles",
          url: "/Articles",
        });
         this.$store.dispatch("app/setLogin", true);
      } catch (e) {
        console.log(e);
        this.error = "e.response.data.message";
      }
    },
  },
};
</script>