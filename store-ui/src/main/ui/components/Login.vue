<template>
  <div class="mapsSheet" v-show="islogin">
    <div>
      <span id="userId" style="font-weight: bold" hidden></span> &nbsp;&nbsp;
      <a id="sign-out" class="action" hidden>Sign Out</a>
      <v-progress-circular
        v-show="!islogin"
        :size="50"
        indeterminate
        color="primary"
      ></v-progress-circular>
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn
            v-show="islogin"
            id="btnUserIcon"
            color="primary"
            class="headline"
            fab
            dark
            v-on="on"
          >
            {{ user.Avatar}}
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
import { mapGetters ,mapState} from "vuex";
export default {
  data() {
    return { };
  },
  computed: {
    ...mapGetters({
      user: "app/getUser",
    }),
       ...mapState({
      islogin: (state) => state.app.islogin,
    }),
  },
  methods: {
    async signOutDropdown() {
      this.showLoading();
      var response = await this.$axios.$get("auth/logout");
      this.$store.dispatch("app/setDefaultState");
      this.vAvatar = "";
      this.$root.$emit("removeLogica");
      this.$store.dispatch('app/setToken', null)
      this.$router.push("/");
    },
    showLoading() {
      console.log("loading ");
      this.isShowLoading = true;
    },
  },
};
</script>

<style scoped>
.action {
  color: black;
}
#btnUserIcon {
  max-width: 50px;
  max-height: 50px;
}
</style>
