<template>
  <div class="mapsSheet" v-show="isShowSing">
    <div>
      <span id="userId" style="font-weight: bold" hidden></span> &nbsp;&nbsp;
      <a id="sign-out" class="action" hidden>Sign Out</a>
      <v-progress-circular
        v-show="isShowLoading"
        :size="50"
        indeterminate
        color="primary"
      ></v-progress-circular>
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn
            v-show="!isShowLoading"
            id="btnUserIcon"
            color="primary"
            class="headline"
            fab
            dark
            v-on="on"
          >
            {{ vAvatar }}
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
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      vAvatar: "",
      isShowLoading: false,
      isShowSing: false,
    };
  },
  mounted() {
    this.$root.$on("user_name", () => {
      var cutName = this.user.nombre.substr(0, 2);
      this.vAvatar = cutName.toUpperCase();
      this.isShowSing = true;
    });
  },
  computed: {
    ...mapGetters({
      user: "app/getUser",
    }),
  },
  methods: {
    async signOutDropdown() {
      this.showLoading();
      var response = await this.$axios.$get("auth/logout");
      this.$store.dispatch("app/setDefaultState");
      this.vAvatar = "";
      this.isShowLoading = false;
      this.isShowSing = false;
      this.$root.$emit("removeLogica");
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
