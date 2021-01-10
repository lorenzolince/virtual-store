<template>
  <div class="infos">
    <section class="main-content">
      <div class="container">
        <div class="card">
          <div class="card-content">
            <div class="column is-4 is-offset-4">
              <h2 class="title has-text-centered">Welcome MJ markets.pty</h2>
              <!-- <Notification v-if="error" :message="error" />-->
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
import { mapState, mapGetters } from 'vuex'
export default {
  data() {
    return {
      email: '',
      password: '',
      error: null
    }
  },
  computed: {
    ...mapState({
      islogin: state => state.app.islogin
    })
  },

  methods: {
    async login() {
      try {
        if (!this.islogin) {
          var response = await this.$axios.post('auth/login', {
            username: this.email,
            password: this.password
          })
          console.log(response)
          this.$axios.setHeader('Authorization', response.data.token)
          this.$axios.setToken(response.data.token)
          this.$store.dispatch('app/setToken', response.data.token)
          const result = response.data.roles.filter(
            role => role === 'ROLE_ADMIN'
          ).length
          if (result > 0) {
            this.$store.dispatch('app/setLinkMenuDefault')
          } else {
            this.$store.dispatch('app/setLinkMenu')
          }
          this.$store.dispatch('app/setLogin', true)
          this.$store.dispatch('app/setShowLoading', true)
          this.$store.dispatch('app/setRole', response.data.roles)
          this.$store.dispatch('app/setUser', response.data.user)
          this.$router.push('/')
        }
      } catch (e) {
        console.log(e)
        this.error = 'e.response.data.message'
      }
    }
  }
}
</script>
