<template>
  <div class="mapsSheet">
    <div v-show="!islogin">
      <span id="userId" style="font-weight: bold" hidden></span> &nbsp;&nbsp;
      <a id="sign-In" class="action" hidden>Sign In</a>
      <v-dialog v-model="dialog" width="500">
        <template v-slot:activator="{ on }">
          <v-btn
            v-show="!islogin"
            id="idCLogin"
            x-large
            color="primary"
            fab
            dark
            v-on="on"
          >
            <v-icon>mdi-login</v-icon>
          </v-btn>
        </template>
        <template v-slot:default="dialog">
          <v-card>
            <v-toolbar color="primary" dark>
              <v-btn icon dark @click="dialog.value = false">
                <v-icon>mdi-close</v-icon>
              </v-btn>
              <v-card-text>
                <div class="text-h4 pa-12">Login</div>
              </v-card-text>
            </v-toolbar>
            <v-card-text>
              <br />
              <v-form ref="form" v-model="valid" lazy-validation>
                <v-container>
                  <v-row>
                    <v-col cols="12" md="6">
                      <v-text-field
                        v-model="email"
                        :rules="emailRules"
                        label="E-mail"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6">
                      <v-text-field
                        v-model="password"
                        :rules="passwordRules"
                        type="password"
                        label="password"
                        required
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6">
                      <v-btn
                        :disabled="!valid"
                        color="success"
                        class="mr-4"
                        @click="login"
                      >
                        Sign In
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-container>
              </v-form>
            </v-card-text>
          </v-card>
        </template>
      </v-dialog>
    </div>
    <div v-show="islogin">
      <span id="userId" style="font-weight: bold" hidden></span> &nbsp;&nbsp;
      <a id="sign-out" class="action" hidden>Sign Out</a>
      <v-progress-circular
        v-show="!isShowLoading"
        :size="50"
        indeterminate
        color="primary"
      ></v-progress-circular>
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn
            v-show="isShowLoading"
            id="idSignOut"
            x-large
            color="primary"
            fab
            dark
            v-on="on"
          >
            <h1>{{ user.Avatar }}</h1>
          </v-btn>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item @click="signOutDropdown">
              <a id="sign-out2" class="action"
                ><v-icon>mdi-close</v-icon> Sign Out</a
              ></v-list-item
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
export default {
  data() {
    return {
      valid: true,
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid'
      ],
      password: '',
      passwordRules: [v => !!v || 'Password is required'],
      error: null
    }
  },
  computed: {
    ...mapGetters({
      user: 'app/getUser'
    }),
    ...mapState({
      islogin: state => state.app.islogin,
      isShowLoading: state => state.app.isShowLoading
    })
  },
  methods: {
    async signOutDropdown() {
      console.log('################ LOGIN ##################')
      this.showLoading()
      var response = await this.$axios.$get('auth/logout')
      this.$store.dispatch('app/setDefaultState')
      this.vAvatar = ''
      this.$root.$emit('removeLogica')
      this.$store.dispatch('app/setToken', null)
      this.$router.push('/')
    },
    showLoading() {
      console.log('loading ')
      this.$store.dispatch('app/setShowLoading', false)
    },
    async login() {
      try {
        this.$refs.form.validate()
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
          this.dialog = false
        }
      } catch (e) {
        console.log(e)
        this.error = 'e.response.data.message'
      }
    }
  }
}
</script>

<style scoped>
.action {
  color: black;
}
</style>
