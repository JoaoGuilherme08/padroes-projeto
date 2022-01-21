<template>
  <nav>
    <div
      class="flex flex-col"
      style="
        width: 1200px;
        margin-top: 50px;
        margin-left: auto;
        margin-right: auto;
      "
    >
      <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-3">
        <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
          <div
            class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
          >
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr style="background-color: #ffed57">
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Name
                    <div class="text-xs text-gray-500">Email</div>
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    País
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Status
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Saldo atual
                  </th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="(person, i) in people" :key="i">
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="flex items-center">
                      <div class="ml-4">
                        <div class="text-sm font-medium text-gray-900">
                          {{ person.name }}
                        </div>
                        <div class="text-sm text-gray-500">
                          {{ person.email }}
                        </div>
                      </div>
                    </div>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-900">{{ person.pais }}</div>
                    <div class="text-sm text-gray-500">
                      {{ person.department }}
                    </div>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <span
                      class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800"
                    >
                      Active
                    </span>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-500">
                      {{ person.dinheiro }}
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div
      class="flex flex-col"
      style="
        width: 1200px;
        margin-top: 50px;
        margin-left: auto;
        margin-right: auto;
      "
    >
      <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-3">
        <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
          <div
            class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
          >
            <table class="min-w-full divide-y divide-gray-200">
              <thead class="bg-gray-50">
                <tr style="background-color: #71fb75">
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Simbolo Ação
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Nome da Ação
                  </th>
                  <th
                    scope="col"
                    class="px-1 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Quantidade
                  </th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="(acao, i) in acoes" :key="i">
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="flex items-center">
                      <div class="ml-4">
                        <div class="text-sm font-medium text-gray-900">
                          {{ acao.simbol }}
                        </div>
                      </div>
                    </div>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="text-sm text-gray-900">{{ acao.name }}</div>
                  </td>
                  <td class="px-10 py-4 whitespace-nowrap mx-auto">
                    <div class="text-sm text-gray-500">
                      {{ acao.volume }}
                    </div>
                  </td>
                </tr>
              </tbody>
              <tfoot>
                <tr>
                  <td></td>
                  <td></td>
                  <th
                    scope="col"
                    class="px-4 py-1 text-left text-sm font-medium text-gray-700 uppercase tracking-wider"
                  >
                    Total: {{ total }}
                  </th>
                </tr>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from "axios";
export default {
  data: () => ({
    people: [],
    acoes: [],
    total: 0,
  }),
  created() {
    this.setup();
    this.BuscaCarteira();
  },
  methods: {
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();

        let response = await axios.get(
          `http://localhost:8083/usuarios?email=${this.claims.email}`,
          { headers: { Authorization: "Bearer " + accessToken } }
        );
        this.people.push({
          name: this.claims.name,
          email: this.claims.email,
          pais: this.claims.locale,
          dinheiro: "R$ " + response.data.dollar_balance.toFixed(2),
        });
      }
    },
    async BuscaCarteira() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        // console.log(accessToken);
        try {
          let response = await axios.get(
            `http://localhost:8083/stockbalances?email=${this.claims.email}`,
            { headers: { Authorization: "Bearer " + accessToken } }
          );
          for (var key in response.data) {
            this.acoes.push({
              simbol: response.data[key][0].stock_symbol,
              name: response.data[key][0].stock_name,
              volume: response.data[key][0].volume,
            });
            this.total += parseInt(response.data[key][0].volume, 10);
          }
        } catch (error) {
          this.acoes = `${error}`;
        }
      }
    },
  },
};
</script>
