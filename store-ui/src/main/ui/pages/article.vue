<!-- pages/pet/new.vue -->

<template>
  <div v-if="islogin" class="container">
    <br />
    <h1 class="title has-text-centered">{{ info.name.toUpperCase() }}</h1>
    <div class="columns is-multiline">
      <div class="column is-half">
        <form @submit.prevent="uploadArticle">
          <div class="field">
            <label class="label">Name</label>
            <div class="control">
              <input v-model="info.name" class="input" type="text" />
            </div>
          </div>
          <div class="field">
            <label class="label">Url</label>
            <div class="control">
              <input v-model="info.url" class="input" type="text" />
            </div>
          </div>
          <div class="field">
            <label class="label">Video</label>
            <div class="control">
              <input v-model="info.video" class="input" type="text" />
            </div>
          </div>
         <div class="field">
            <label class="label">Description</label>
            <div class="control">
              <textarea v-model="info.description" class="textarea"></textarea>
            </div>
          </div>
            <div class="field">
            <label class="label">cantidad</label>
            <div class="control">
              <input v-model="info.cantidad" class="input" type="text" />
            </div>
          </div>
          <div class="field">
            <label class="label">Precio</label>
            <div class="control">
              <input v-model="info.precio" class="input" type="text" />
            </div>
          </div>
          <div class="field">
            <label class="label">Precio real</label>
            <div class="control">
              <input v-model="info.precioReal" class="input" type="text" />
            </div>
          </div>
          <div class="field">
            <div class="control">
              <v-select
                v-model="info.categoria"
                :items="allLinks.filter(c => c.text != 'all')"
                label="Categoria"
              ></v-select>
            </div>
          </div>

          <div class="file">
            <label class="file-label">
              <input
                class="file-input"
                type="file"
                name="resume"
                @change="onFileChange"
              />
              <span class="file-cta">
                <span class="file-icon">
                  <i class="fas fa-upload"></i>
                </span>
                <span class="file-label">Upload a articles image…</span>
              </span>
            </label>
          </div>
          <br />

          <div class="field is-grouped">
            <div class="control">
              <button class="button is-link">Submit</button>
            </div>
          </div>
        </form>
      </div>

      <div class="column is-half">
        <figure v-if="preview" class="image container is-256x256">
          <img
            style="
              border-radius: 10px;
              box-shadow: 0 1rem 1rem rgba(0, 0, 0, 0.7);
            "
            :src="preview"
            alt
          />
        </figure>
        <figure v-else class="image container is-256x256">
          <img
            style="
              border-radius: 10px;
              box-shadow: 0 1rem 1rem rgba(0, 0, 0, 0.7);
            "
            src="/store/img/150.png"
          />
        </figure>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters } from "vuex";
export default {
  data() {
    return {
      info: {
        name: "",
        description: "",
        precio: null,
        precioReal: null,
        categoria: null,
        cantidad: null,
        url: "",
        video: null,
        file: "",
      },
      preview: "",
    };
  },
  head() {
    return {
      title: "Articles",
    };
  },
  computed: {
    ...mapState({
      allLinks: (state) => state.app.allLinks,
      islogin: (state) => state.app.islogin,
    }),
  },
  methods: {
    onFileChange(e) {
      let files = e.target.files || e.dataTransfer.files;
      if (!files.length) {
        return;
      }
      this.info.file = files[0];
      this.info.url = process.env.urlServer + "images/" + this.info.file.name;
      this.createImage(files[0]);
    },
    createImage(file) {
      let reader = new FileReader();
      let vm = this;
      reader.onload = (e) => {
        vm.preview = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    async uploadArticle() {
      let formData = new FormData();

      for (let data in this.info) {
        let result = this.info[data];
        if (result != null) {
          console.log(result);
          formData.append(data, result);
        }
      }

      try {
        var response = await this.$axios.post("articles/save/file", formData);
        this.$router.push("/");
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>
