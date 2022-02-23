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
        <h1 id="h1Title" class="mx-auto">Plataforma de Trading</h1>

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
      </div>
    </div>
    <div id="creatorArea">
      <section class="max-w-6xl mx-auto px-4 sm:px-6 lg:px-4 py-12">
        <div class="text-center pb-12">
          <h1
            class="font-bold text-3xl md:text-4xl lg:text-5xl font-heading text-gray-900"
            id="h1Creator"
          >
            Criador
          </h1>
        </div>
        <div
          class="w-full bg-white rounded-lg p-12 flex flex-col justify-center items-center"
          id="divCreator"
        >
          <div class="mb-8">
            <img
              class="object-center object-cover rounded-full h-36 w-36"
              src="https://media-exp1.licdn.com/dms/image/C4E03AQF610ObQbaKEg/profile-displayphoto-shrink_800_800/0/1641404227167?e=1649289600&v=beta&t=aTzSO1ItLZEHMEXqKsSsY7YmwAeKPxUfeGUACMZUJpI"
              alt="photo"
            />
          </div>
          <div class="text-center">
            <p id="nameCreator" class="text-xl text-gray-700 font-bold mb-2">
              João Cobo
            </p>
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
      }
    },
  },
};
</script>

<style>
#home {
  background-image: url("../../images/backgroudhome.jpg");
  background-size: cover;
  background-position: left;
  width: 100%;
  height: 70%;
}
#bgTitle {
  filter: blur(8px);
  -webkit-filter: blur(8px);
  height: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
#h1Title {
  color: white;
  width: 100vw;
  height: 30vh;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-size: 100px;
  font-family: -apple-system, BlinkMacSystemFont, avenir next, avenir,
    helvetica neue, helvetica, Ubuntu, roboto, noto, segoe ui, arial, sans-serif;
  line-height: 1.3;
  margin-bottom: 50px;
}

#graficoDiv {
  padding-bottom: 50px;
}
</style>
