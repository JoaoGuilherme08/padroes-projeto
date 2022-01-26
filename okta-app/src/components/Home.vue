<template>
  <div id="home">
    <h1>Okta Single-Page App Demo</h1>
    <div v-if="!this.$root.authenticated">
      <p>
        How much caffeine has your developer had today?
        <router-link role="button" to="/login">Log in to find out!</router-link>
      </p>
    </div>

    <div v-if="this.$root.authenticated">
      <p>Welcome, {{ claims.name }}!</p>
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
