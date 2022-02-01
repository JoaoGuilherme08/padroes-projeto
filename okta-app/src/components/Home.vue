<template>
  <div id="home" style="margin-left: auto; margin-right: 10px; top: 20px">
    <h1>Bem vindo ao Trade Actions</h1>
    <div v-if="!this.$root.authenticated"></div>
    <div v-if="this.$root.authenticated">
      <p>{{ claims.name }}!</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "home",
  data: function () {
    return {
      claims: "",
    };
  },
  created() {
    this.setup();
  },
  methods: {
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        console.log(accessToken);
        try {
          let response = await axios.get(
            `http://localhost:8083/usuarios?email=${this.claims.email}`,
            { headers: { Authorization: "Bearer " + accessToken } }
          );

          console.log(response.data);
        } catch (error) {
          console.log(error);
        }
      }
    },
  },
};
</script>
