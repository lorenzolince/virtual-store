<template>
  <div class="infos">
    <section class="main-content">
      <div class="container">
        <div class="card">
          <div class="card-content">
            <div class="column is-4 is-offset-4">
              <h2 class="title has-text-centered">Register!</h2>

              <form method="post" @submit.prevent="register">
                <div class="field">
                  <label class="label">Username</label>
                  <div class="control">
                    <input
                      v-model="username"
                      type="text"
                      class="input"
                      name="username"
                      required
                    />
                  </div>
                </div>
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
                  <label class="label">Cell Phone</label>
                  <div class="control">
                    <input
                      v-model="celular"
                      type="text"
                      class="input"
                      name="celular"
                      required
                    />
                  </div>
                </div>
                <div class="field">
                  <label class="label">Address</label>
                  <div class="control">
                    <input
                      v-model="direccion"
                      type="text"
                      class="input"
                      name="celular"
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
                    Register
                  </button>
                </div>
              </form>

              <div class="has-text-centered" style="margin-top: 20px">
                Already got an account? <nuxt-link to="/login">Login</nuxt-link>
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
      username: '',
      email: '',
      password: '',
      celular: '',
      direccion: ''
    }
  },
  methods: {
    async register() {
      const UserRegister = {
        nombre: this.username,
        email: this.email,
        password: this.password,
        celular: this.celular,
        direccion: this.direccion
      }
      console.log(UserRegister)
      var response = await this.$axios.$post('register', UserRegister)
      this.$router.push('/login')
    }
  }
}
</script>
