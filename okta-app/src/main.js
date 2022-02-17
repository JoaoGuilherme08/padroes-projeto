import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import OktaVue from "@okta/okta-vue";
import { oktaAuth } from "./okta";
import "./assets/tailwind.css";
import VueApexCharts from "vue3-apexcharts";

createApp(App)
  .use(router)
  .use(VueApexCharts)
  .use(OktaVue, {
    oktaAuth,
    onAuthRequired: () => {
      router.push("/login");
    },
    onAuthResume: () => {
      router.push("/login");
    },
  })
  .mount("#app");
