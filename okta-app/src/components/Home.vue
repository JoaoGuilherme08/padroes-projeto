<template>
  <section>
    <div id="home" class="mx-auto">
      <div
        style="
          margin-left: auto;
          margin-right: 10px;
          top: 20px;
          padding-top: 100px;
          padding-bottom: 111px;
        "
      >
        <div
          class="mx-5 lg:w-6/12 bg-gray-800 shadow-2xl rounded-lg overflow-hidden w-1/2 mx-auto"
          style="margin-bottom: 50px"
        >
          <div id="header-buttons" class="py-3 px-4 flex">
            <div class="rounded-full w-3 h-3 bg-red-500 mr-2"></div>
            <div class="rounded-full w-3 h-3 bg-yellow-500 mr-2"></div>
            <div class="rounded-full w-3 h-3 bg-green-500"></div>
          </div>
          <div id="code-area" class="py-4 px-4 mt-1 text-white text-xl">
            <div class="mb-2">
              <span class="text-yellow-300">if </span>
              <span class="text-blue-400">(logged </span>
              <span class="text-green-400">=</span>
              <span class="text-blue-400"> true</span>
              <span class="text-blue-400">)</span>
              <span> {</span>
            </div>

            <div class="mb-2">
              <span class="text-yellow-300">const </span>
              <span class="text-blue-400">user </span>
              <span class="text-green-400">= </span>
              <span>{{ this.claims.email }}</span>
            </div>
            <div class="mb-2">
              <span class="text-green-300">System</span>
              <span>.</span>
              <span class="text-blue-400">out</span>
              <span>.</span>
              <span class="text-yellow-300">println </span>
              <span>(</span>
              <span>"Hello " + </span>
              <span class="text-blue-400">user</span>
              <span>);</span>
            </div>
            <div class="mb-2">
              <span class="text-green-300">System</span>
              <span>.</span>
              <span class="text-blue-400">out</span>
              <span>.</span>
              <span class="text-yellow-300">println </span>
              <span>(</span>
              <span>"Bem vindo ao Trade Actions"</span>
              <span>);</span>
            </div>
            <div class="mb-2">
              <span>} </span>
            </div>
          </div>
        </div>
        <div class="w-1/2 mx-auto">
          <div
            class="w-full shadow-2xl subpixel-antialiased rounded h-64 bg-black border-black mx-auto"
          >
            <div
              class="flex items-center h-6 rounded-t bg-gray-100 border-b border-gray-500 text-center text-black"
              id="headerTerminal"
            >
              <div
                class="flex ml-2 items-center text-center border-red-900 bg-red-500 shadow-inner rounded-full w-3 h-3"
                id="closebtn"
              ></div>
              <div
                class="ml-2 border-yellow-900 bg-yellow-500 shadow-inner rounded-full w-3 h-3"
                id="minbtn"
              ></div>
              <div
                class="ml-2 border-green-900 bg-green-500 shadow-inner rounded-full w-3 h-3"
                id="maxbtn"
              ></div>
              <div class="mx-auto pr-16" id="terminaltitle">
                <p class="text-center text-xl">Terminal</p>
              </div>
            </div>
            <div
              class="pl-1 pt-1 h-auto text-green-200 font-mono text-xs bg-black"
              id="console"
            >
              <p class="pb-1 text-xl">
                Last login: Wed Sep 25 09:11:04 on ttys002
              </p>
              <p class="pb-1 text-xl">Laraben: Hello {{ this.claims.email }}</p>
              <p class="pb-1 text-xl">Laraben: Bem vindo ao Trade Actions</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div>
      <section class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-12">
        <div class="text-center pb-12">
          <h1
            class="font-bold text-3xl md:text-4xl lg:text-5xl font-heading text-gray-900"
          >
            Criador
          </h1>
        </div>
        <div
          class="w-full bg-white rounded-lg p-12 flex flex-col justify-center items-center"
        >
          <div class="mb-8">
            <img
              class="object-center object-cover rounded-full h-36 w-36"
              src="https://media-exp1.licdn.com/dms/image/C4E03AQF610ObQbaKEg/profile-displayphoto-shrink_800_800/0/1641404227167?e=1649289600&v=beta&t=aTzSO1ItLZEHMEXqKsSsY7YmwAeKPxUfeGUACMZUJpI"
              alt="photo"
            />
          </div>
          <div class="text-center">
            <p class="text-xl text-gray-700 font-bold mb-2">João Cobo</p>
            <p class="text-base text-gray-400 font-normal">
              Software Developer
            </p>
          </div>
        </div>
      </section>
    </div>
  </section>
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
            `http://172.17.0.1:8083/usuarios?email=${this.claims.email}`,
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

<style>
#home {
  background-image: url("https://images.unsplash.com/photo-1638913662529-1d2f1eb5b526?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80");
  background-size: cover;
  background-position: left;
  width: 100%;
  height: 70%;
}
</style>
