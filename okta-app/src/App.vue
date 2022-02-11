<template>
  <div>
    <nav class="bg-white shadow">
      <div class="mx-auto container px-6 py-2 xl:py-0">
        <div class="flex items-center justify-between">
          <div class="inset-y-0 left-0 flex items-center xl:hidden">
            <div
              class="inline-flex items-center justify-center p-2 rounded-md text-gray-700 hover:text-gray-100 focus:outline-none transition duration-150 ease-in-out"
            >
              <div class="visible xl:hidden">
                <ul
                  class="p-2 border-r bg-white absolute rounded left-0 right-0 shadow mt-8 md:mt-8 hidden"
                >
                  <li
                    class="flex xl:hidden cursor-pointer text-gray-600 text-sm leading-3 tracking-normal mt-2 py-2 hover:text-indigo-700 focus:text-indigo-700 focus:outline-none"
                  >
                    <a
                      href="/"
                      class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
                    >
                      <span class="mr-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          class="icon icon-tabler icon-tabler-grid"
                          width="20"
                          height="20"
                          viewBox="0 0 24 24"
                          stroke-width="1.5"
                          stroke="currentColor"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        >
                          <path stroke="none" d="M0 0h24v24H0z"></path>
                          <rect x="4" y="4" width="6" height="6" rx="1"></rect>
                          <rect x="14" y="4" width="6" height="6" rx="1"></rect>
                          <rect x="4" y="14" width="6" height="6" rx="1"></rect>
                          <rect
                            x="14"
                            y="14"
                            width="6"
                            height="6"
                            rx="1"
                          ></rect>
                        </svg>
                      </span>
                      Inicio
                    </a>
                  </li>
                  <li
                    class="xl:hidden flex-col cursor-pointer text-gray-600 text-sm leading-3 tracking-normal py-2 hover:text-indigo-700 focus:text-indigo-700 focus:outline-none flex justify-center"
                    @click="dropdownHandler($event)"
                  >
                    <a
                      href="/Profile"
                      class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
                    >
                      <span class="mr-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                          fill="none"
                          stroke="#000000"
                          stroke-width="2"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        >
                          <path
                            d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"
                          ></path>
                          <circle cx="12" cy="7" r="4"></circle>
                        </svg>
                      </span>
                      Perfil
                    </a>
                  </li>
                  <li
                    class="xl:hidden cursor-pointer text-gray-600 text-sm leading-3 tracking-normal py-2 hover:text-indigo-700 flex items-center focus:text-indigo-700 focus:outline-none"
                  >
                    <a
                      href="/Orders"
                      class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
                    >
                      <span class="mr-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          fill="currentColor"
                          class="bi bi-currency-dollar"
                          viewBox="0 0 16 16"
                        >
                          <path
                            d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718H4zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73l.348.086z"
                          />
                        </svg>
                      </span>
                      Compra de ações
                    </a>
                  </li>
                  <li
                    class="xl:hidden cursor-pointer text-gray-600 text-sm leading-3 tracking-normal py-2 hover:text-indigo-700 flex items-center focus:text-indigo-700 focus:outline-none"
                  >
                    <a
                      v-if="authenticated"
                      v-on:click="logout()"
                      class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
                    >
                      <span class="mr-2">
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="24"
                          height="24"
                          viewBox="0 0 24 24"
                          fill="none"
                          stroke="#000000"
                          stroke-width="2"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        >
                          <path d="M16 17l5-5-5-5M19.8 12H9M10 3H4v18h6" />
                        </svg>
                        <path stroke="none" d="M0 0h24v24H0z"></path>
                        <polyline
                          points="8 16 10 10 16 8 14 14 8 16"
                        ></polyline>
                        <circle cx="12" cy="12" r="9"></circle>
                      </span>
                      Sair
                    </a>
                  </li>
                </ul>
                <svg
                  @click="MenuHandler($event, true)"
                  aria-haspopup="true"
                  aria-label="Main Menu"
                  xmlns="http://www.w3.org/2000/svg"
                  class="show-m-menu icon icon-tabler icon-tabler-menu"
                  width="28"
                  height="28"
                  viewBox="0 0 24 24"
                  stroke-width="1.5"
                  stroke="currentColor"
                  fill="none"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                >
                  <path stroke="none" d="M0 0h24v24H0z"></path>
                  <line x1="4" y1="8" x2="20" y2="8"></line>
                  <line x1="4" y1="16" x2="20" y2="16"></line>
                </svg>
                <div
                  class="hidden close-m-menu text-gray-700"
                  @click="MenuHandler($event, false)"
                >
                  <svg
                    aria-label="Close"
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    fill="none"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  >
                    <path stroke="none" d="M0 0h24v24H0z" />
                    <line x1="18" y1="6" x2="6" y2="18" />
                    <line x1="6" y1="6" x2="18" y2="18" />
                  </svg>
                </div>
              </div>
            </div>
          </div>
          <div
            class="flex w-full sm:w-auto items-center sm:items-stretch justify-end sm:justify-start"
          >
            <div class="flex items-center">
              <img
                height="44"
                width="43"
                src="data:image/png;base64,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"
                alt=""
              />
              <h2
                class="hidden sm:block text-base text-gray-700 font-bold leading-normal pl-3"
              >
                Trade Actions
              </h2>
            </div>
          </div>
          <div class="flex">
            <div class="hidden xl:flex md:mr-6 xl:mr-16">
              <a
                href="/"
                class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
              >
                <span class="mr-2">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    class="icon icon-tabler icon-tabler-grid"
                    width="20"
                    height="20"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    fill="none"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  >
                    <path stroke="none" d="M0 0h24v24H0z"></path>
                    <rect x="4" y="4" width="6" height="6" rx="1"></rect>
                    <rect x="14" y="4" width="6" height="6" rx="1"></rect>
                    <rect x="4" y="14" width="6" height="6" rx="1"></rect>
                    <rect x="14" y="14" width="6" height="6" rx="1"></rect>
                  </svg>
                </span>
                Inicio
              </a>
              <a
                href="/Profile"
                class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
              >
                <span class="mr-2">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="#000000"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  >
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                    <circle cx="12" cy="7" r="4"></circle>
                  </svg>
                </span>
                Perfil
              </a>
              <a
                href="/Orders"
                class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
              >
                <span class="mr-2">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    fill="currentColor"
                    class="bi bi-currency-dollar"
                    viewBox="0 0 16 16"
                  >
                    <path
                      d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718H4zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73l.348.086z"
                    />
                  </svg>
                </span>
                Compra de ações
              </a>
              <a
                v-if="authenticated"
                v-on:click="logout()"
                class="flex px-5 items-center py-6 text-sm leading-5 text-gray-700 hover:bg-gray-100 focus:bg-gray-100 focus:outline-none transition duration-150 ease-in-out"
              >
                <span class="mr-2">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    viewBox="0 0 24 24"
                    fill="none"
                    stroke="#000000"
                    stroke-width="2"
                    stroke-linecap="round"
                    stroke-linejoin="round"
                  >
                    <path d="M16 17l5-5-5-5M19.8 12H9M10 3H4v18h6" />
                  </svg>
                  <path stroke="none" d="M0 0h24v24H0z"></path>
                  <polyline points="8 16 10 10 16 8 14 14 8 16"></polyline>
                  <circle cx="12" cy="12" r="9"></circle>
                </span>
                Sair
              </a>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <div id="content">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      authenticated: false,
    };
  },
  async created() {
    await this.isAuthenticated();
    this.$auth.authStateManager.subscribe(this.isAuthenticated);
  },
  watch: {
    // Everytime the route changes, check for auth status
    $route: "isAuthenticated",
  },
  methods: {
    async isAuthenticated() {
      this.authenticated = await this.$auth.isAuthenticated();
    },
    async logout() {
      await this.$auth.signOut();
    },
    dropdownHandler(event) {
      let single = event.currentTarget.getElementsByTagName("ul")[0];
      single.classList.toggle("hidden");
    },
    MenuHandler(el, val) {
      let MainList =
        el.currentTarget.parentElement.getElementsByTagName("ul")[0];
      let closeIcon =
        el.currentTarget.parentElement.getElementsByClassName(
          "close-m-menu"
        )[0];
      let showIcon =
        el.currentTarget.parentElement.getElementsByClassName("show-m-menu")[0];
      if (val) {
        MainList.classList.remove("hidden");
        el.currentTarget.classList.add("hidden");
        closeIcon.classList.remove("hidden");
      } else {
        showIcon.classList.remove("hidden");
        MainList.classList.add("hidden");
        el.currentTarget.classList.add("hidden");
      }
    },
  },
};
</script>

<style>
a {
  text-decoration: underline;
  cursor: pointer;
}
</style>
