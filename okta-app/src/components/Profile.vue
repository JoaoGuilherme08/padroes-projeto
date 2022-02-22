<template>
  <div>
    <div
      class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative"
      style="width: 534px; margin-left: auto; margin-right: 10px; top: 20px"
      role="alert"
      v-show="ocultaMsg"
    >
      <span class="block sm:inline">{{ mensagemErro }}</span>
    </div>
    <div
      class="relative"
      style="width: 534px; margin-left: auto; margin-right: 10px; top: 20px"
    >
      <div
        class="bg-green-100 border text-green-700 px-4 py-3 items-center text-base w-full inline-flex"
        role="alert"
        v-show="ocultaMsgOk"
      >
        <svg
          aria-hidden="true"
          focusable="false"
          data-prefix="fas"
          data-icon="check-circle"
          class="w-4 h-4 mr-2 fill-current"
          role="img"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 512 512"
        >
          <path
            fill="currentColor"
            d="M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z"
          ></path>
        </svg>
        {{ mensagemOk }}
      </div>
    </div>
    <div>
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
          <div
            class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8"
          >
            <div
              class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
            >
              <table class="min-w-full divide-y divide-gray-200">
                <thead class="bg-gray-50">
                  <tr style="background-color: #ffe227">
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
                      <div class="text-xsfont-medium text-gray-900">
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
          <div
            class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8"
          >
            <div
              class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
            >
              <table class="min-w-full divide-y divide-gray-200">
                <caption>
                  Ordens disponíveis para venda
                </caption>
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
                    <th></th>
                  </tr>
                </thead>
                <tbody
                  class="bg-white divide-y divide-gray-200"
                  v-for="(acao, i) in acoes"
                  :key="i"
                >
                  <tr v-if="acao.volume > 0">
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
                    <td>
                      <button
                        class="bg-blue-500 hover:bg-blue-700 text-white font-bold text-sm py-1 px-2 rounded-full"
                        @click="modalHandler(true, acao)"
                      >
                        VENDER
                      </button>
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
      <div
        class="flex flex-col"
        style="
          width: 1200px;
          margin-top: 50px;
          margin-left: auto;
          margin-right: auto;
        "
        v-show="ocultaOrder"
      >
        <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-3">
          <div
            class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8"
          >
            <div
              class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
            >
              <table class="min-w-full divide-y divide-gray-200">
                <caption>
                  Ordens de compra e venda
                </caption>
                <thead class="bg-gray-50">
                  <tr style="background-color: #ff7059">
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
                      Nome Ação
                    </th>
                    <th
                      scope="col"
                      class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                    >
                      Quantidade
                    </th>
                    <th
                      scope="col"
                      class="px-6 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                    >
                      Valor
                    </th>
                    <th
                      scope="col"
                      class="px-1 py-3 text-left text-xs font-medium text-gray-700 uppercase tracking-wider"
                    >
                      Tipo da Order
                    </th>
                  </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                  <tr v-for="(order, i) in Orders" :key="i">
                    <td class="px-6 py-4 whitespace-nowrap">
                      <div class="flex items-center">
                        <div class="ml-4">
                          <div class="text-sm font-medium text-gray-900">
                            {{ order.simbol }}
                          </div>
                        </div>
                      </div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap">
                      <div class="text-sm text-gray-900">
                        {{ order.name }}
                      </div>
                    </td>
                    <td class="px-12 py-4 whitespace-nowrap">
                      <div class="text-sm text-gray-500">
                        {{ order.quantidade }}
                      </div>
                    </td>
                    <td class="px-6 py-4 whitespace-nowrap">
                      <div class="text-sm text-gray-500">
                        R$ {{ order.preco }}
                      </div>
                    </td>
                    <td class="px-1 py-4 whitespace-nowrap">
                      <div class="text-sm text-gray-500">
                        {{ order.type }}
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
        class="py-12 transition duration-150 ease-in-out z-10 absolute top-0 right-0 bottom-0 left-0"
        id="modal"
        v-show="showModal"
      >
        <div role="alert" class="container mx-auto w-11/12 md:w-2/3 max-w-lg">
          <div
            class="relative py-8 px-5 md:px-10 bg-white shadow-md rounded border border-gray-400"
          >
            <div class="w-full flex justify-start text-gray-600 mb-3">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="icon icon-tabler icon-tabler-wallet"
                width="52"
                height="52"
                viewBox="0 0 24 24"
                stroke-width="1"
                stroke="currentColor"
                fill="none"
                stroke-linecap="round"
                stroke-linejoin="round"
              >
                <path stroke="none" d="M0 0h24v24H0z" />
                <path
                  d="M17 8v-3a1 1 0 0 0 -1 -1h-10a2 2 0 0 0 0 4h12a1 1 0 0 1 1 1v3m0 4v3a1 1 0 0 1 -1 1h-12a2 2 0 0 1 -2 -2v-12"
                />
                <path d="M20 12v4h-4a2 2 0 0 1 0 -4h4" />
              </svg>
            </div>
            <div class="parent">
              <vue-element-loading
                :active="show"
                spinner="bar-fade-scale"
                color="#4643FF"
                text="Aguarde..."
              />
              <h1
                class="text-gray-800 font-lg font-bold tracking-normal leading-tight mb-4"
              >
                Por quanto deseja vender esta ação?
              </h1>
              <label
                for="quantidade"
                class="text-gray-800 text-sm font-bold leading-tight tracking-normal"
                >Quantidade</label
              >
              <input
                id="quantidade"
                class="mb-5 mt-2 text-gray-600 focus:outline-none focus:border focus:border-indigo-700 font-normal w-full h-10 flex items-center pl-3 text-sm border-gray-300 rounded border"
                @click="tirarQuantidade()"
                placeholder="Digite a Quantidade"
              />
              <label
                for="valor"
                class="text-gray-800 text-sm font-bold leading-tight tracking-normal"
                >Valor</label
              >
              <input
                id="valor"
                class="mb-5 mt-2 text-gray-600 focus:outline-none focus:border focus:border-indigo-700 font-normal w-full h-10 flex items-center pl-3 text-sm border-gray-300 rounded border"
                @keyup="formatarMoeda()"
                @click="tirarValor()"
                placeholder="Digite o Valor"
              />
              <div class="flex items-center justify-start w-full">
                <button
                  class="focus:outline-none transition duration-150 ease-in-out hover:bg-indigo-600 bg-indigo-700 rounded text-white px-8 py-2 text-sm"
                  @click="[(this.show = true), venderAcao()]"
                >
                  Vender
                </button>
                <button
                  class="focus:outline-none ml-3 bg-gray-100 transition duration-150 text-gray-600 ease-in-out hover:border-gray-400 hover:bg-gray-300 border rounded px-8 py-2 text-sm"
                  @click="modalHandler(false)"
                >
                  Cancelar
                </button>
              </div>
            </div>

            <div
              class="cursor-pointer absolute top-0 right-0 mt-4 mr-5 text-gray-400 hover:text-gray-600 transition duration-150 ease-in-out"
              @click="modalHandler()"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                aria-label="Close"
                class="icon icon-tabler icon-tabler-x"
                width="20"
                height="20"
                viewBox="0 0 24 24"
                stroke-width="2.5"
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
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import VueElementLoading from "vue-element-loading";

export default {
  components: {
    VueElementLoading,
  },
  data: () => ({
    people: [],
    acoes: [],
    total: 0,
    acoesRecebidas: [],
    showModal: false,
    infosUser: [],
    show: false,
    isDisabled: false,
    Orders: [],
    mensagemErro: "",
    ocultaMsg: false,
    iduserlogado: "",
    mensagemOk: "",
    ocultaMsgOk: false,
    ocultaOrder: false,
  }),
  created() {
    this.setup();
    this.BuscaCarteira();
    this.BuscaOrders();
    this.connect();
  },
  methods: {
    connect() {
      this.socket = new SockJS("http://172.17.0.1:8085/profile");
      this.stompClient = Stomp.over(this.socket);
      let accessToken = this.$auth.getAccessToken();
      this.stompClient.connect(
        { "X-Authorization": "Bearer " + accessToken },
        (frame) => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/pushorders", (tick) => {
            if (JSON.parse(tick.body).orders == true) {
              if (JSON.parse(tick.body).operation == "INSERT") {
                if (
                  this.infosUser.data.id == JSON.parse(tick.body).record.id_user
                ) {
                  this.Orders.push({
                    id: JSON.parse(tick.body).record.id,
                    idStock: JSON.parse(tick.body).record.id_stock,
                    name: JSON.parse(tick.body).record.stock_name,
                    simbol: JSON.parse(tick.body).record.stock_symbol,
                    preco: JSON.parse(tick.body).record.price.toLocaleString(
                      "pt-BR",
                      {
                        minimumFractionDigits: 2,
                      }
                    ),
                    quantidade: JSON.parse(tick.body).record.volume,
                    type:
                      JSON.parse(tick.body).record.type == 1
                        ? "COMPRA"
                        : "VENDA",
                    status: JSON.parse(tick.body).record.status,
                  });
                }
              } else {
                for (var key in this.Orders) {
                  if (this.Orders[key].id == JSON.parse(tick.body).record.id) {
                    this.Orders[key].id = JSON.parse(tick.body).record.id;
                    this.Orders[key].idStock = JSON.parse(
                      tick.body
                    ).record.id_stock;
                    this.Orders[key].name = JSON.parse(
                      tick.body
                    ).record.stock_name;
                    this.Orders[key].simbol = JSON.parse(
                      tick.body
                    ).record.stock_symbol;
                    this.Orders[key].quantidade = JSON.parse(
                      tick.body
                    ).record.volume;
                    this.Orders[key].preco = JSON.parse(
                      tick.body
                    ).record.price.toLocaleString("pt-BR", {
                      minimumFractionDigits: 2,
                    });
                    this.Orders[key].type =
                      JSON.parse(tick.body).record.type == 1
                        ? "COMPRA"
                        : "VENDA";
                    this.Orders[key].status = JSON.parse(
                      tick.body
                    ).record.status;
                    this.ocultaOrder = true;
                  }
                }
              }
            } else if (JSON.parse(tick.body).users == true) {
              this.people[0].dinheiro =
                "R$ " + JSON.parse(tick.body).record.dollar_balance.toFixed(2);
            } else if (JSON.parse(tick.body).stockbalance == true) {
              if (JSON.parse(tick.body).operation == "INSERT") {
                this.acoes.push({
                  id: JSON.parse(tick.body).record.id_stock,
                  simbol: JSON.parse(tick.body).record.stock_symbol,
                  name: JSON.parse(tick.body).record.stock_name,
                  volume: JSON.parse(tick.body).record.volume,
                });
              } else {
                //MARCACAO
                if (this.iduserlogado == JSON.parse(tick.body).record.id_user) {
                  this.total = 0;
                  for (var key1 in this.acoes) {
                    if (
                      this.acoes[key1].id ==
                      JSON.parse(tick.body).record.id_stock
                    ) {
                      this.acoes[key1].id = JSON.parse(
                        tick.body
                      ).record.id_stock;
                      this.acoes[key1].simbol = JSON.parse(
                        tick.body
                      ).record.stock_symbol;
                      this.acoes[key1].name = JSON.parse(
                        tick.body
                      ).record.stock_name;
                      this.acoes[key1].volume = JSON.parse(
                        tick.body
                      ).record.volume;
                    }

                    this.total += this.acoes[key1].volume;
                  }
                }
              }
            }
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },

    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();

        let response = await axios.get(
          `http://172.17.0.1:8083/usuarios?email=${this.claims.email}`,
          { headers: { Authorization: "Bearer " + accessToken } }
        );

        this.infosUser = response;
        this.iduserlogado = response.data.id;
        this.people.push({
          name: this.claims.name,
          email: this.claims.email,
          pais: this.claims.locale,
          dinheiro: "R$ " + response.data.dollarBalance.toFixed(2),
        });
      }
    },
    async BuscaOrders() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get(
            `http://172.17.0.1:8083/userorder?user=${this.infosUser.data.id}`,
            { headers: { Authorization: "Bearer " + accessToken } }
          );
          for (var key in response.data) {
            if (response.data[key].status != 2) {
              this.ocultaOrder = true;
              this.Orders.push({
                id: response.data[key].id,
                idStock: response.data[key].idStock,
                simbol: response.data[key].stockSymbol,
                preco: response.data[key].price.toLocaleString("pt-BR", {
                  minimumFractionDigits: 2,
                }),
                type: response.data[key].type == 1 ? "COMPRA" : "VENDA",
                quantidade: response.data[key].volume,
                status: response.data[key].status,
                name: response.data[key].stockName,
              });
            }
          }
        } catch (error) {
          this.Orders = `${error}`;
        }
      }
    },
    async BuscaCarteira() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        // console.log(accessToken);
        try {
          let response = await axios.get(
            `http://172.17.0.1:8083/stockbalances?email=${this.claims.email}`,
            { headers: { Authorization: "Bearer " + accessToken } }
          );
          for (var key in response.data) {
            this.acoes.push({
              id: response.data[key][0].idStock,
              simbol: response.data[key][0].stockSymbol,
              name: response.data[key][0].stockName,
              volume: response.data[key][0].volume,
            });
            this.total += parseInt(response.data[key][0].volume, 10);
          }
        } catch (error) {
          this.acoes = `${error}`;
        }
      }
    },
    modalHandler(val, acao) {
      this.acoesRecebidas = acao;
      let modal = document.getElementById("modal");
      let valor = document.getElementById("valor");
      let quantidade = document.getElementById("quantidade");
      if (val) {
        valor.value = (0).toLocaleString("pt-BR", { minimumFractionDigits: 2 });
        quantidade.value = 0;
        this.fadeIn(modal);
      } else {
        this.fadeOut(modal);
      }
    },
    fadeOut(el) {
      el.style.opacity = 1;
      (function fade() {
        if ((el.style.opacity -= 0.1) < 0) {
          el.style.display = "none";
        } else {
          requestAnimationFrame(fade);
        }
      })();
    },
    fadeIn(el, display) {
      el.style.opacity = 0;
      el.style.display = display || "flex";
      (function fade() {
        let val = parseFloat(el.style.opacity);
        if (!((val += 0.2) > 1)) {
          el.style.opacity = val;
          requestAnimationFrame(fade);
        }
      })();
    },
    formatarMoeda() {
      var elemento = document.getElementById("valor");
      var valor = elemento.value;

      valor = valor + "";
      valor = parseInt(valor.replace(/[\D]+/g, ""));
      valor = valor + "";
      valor = valor.replace(/([0-9]{2})$/g, ",$1");

      if (valor.length > 6) {
        valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
      }

      elemento.value = valor;
      if (valor == "NaN") elemento.value = "";
    },
    tirarValor() {
      var valor = document.getElementById("valor");
      if (valor.value == "0,00") {
        valor.value = "";
      }
    },
    tirarQuantidade() {
      var quantidade = document.getElementById("quantidade");
      if (quantidade.value == 0) {
        quantidade.value = "";
      }
    },
    async venderAcao() {
      let valor = document.getElementById("valor");
      let quantidade = document.getElementById("quantidade");

      console.log(valor.value);
      if (quantidade.value == 0 || quantidade.value == "") {
        this.ocultaMsg = true;
        this.alertMensagemErro("Quantidade não pode ser igual a 0");
        this.show = false;
        setTimeout(this.ocultaMensagem, 3000);
      } else if (valor.value == "0,00" || valor.value == "") {
        this.ocultaMsg = true;
        this.alertMensagemErro("Valor não pode ser igual a 0");
        this.show = false;
        setTimeout(this.ocultaMensagem, 3000);
      } else if (quantidade.value <= this.acoesRecebidas.volume) {
        if (this.$root.authenticated) {
          this.claims = await this.$auth.getUser();
          let accessToken = this.$auth.getAccessToken();
          console.log(parseFloat(valor.value));
          try {
            let response = await axios.post(
              `http://172.17.0.1:8083/userorder`,
              {
                idUser: this.infosUser.data.id,
                idStock: this.acoesRecebidas.id,
                stockSymbol: this.acoesRecebidas.simbol,
                stockName: this.acoesRecebidas.name,
                volume: parseFloat(quantidade.value),
                price: parseFloat(valor.value.replace(/,/g, ".")),
                type: 2,
                status: 1,
              },
              {
                headers: {
                  Authorization: "Bearer " + accessToken,
                },
              }
            );
            console.log(response);
            if (response.status == 200) {
              this.ocultaMsgOk = true;
              this.alertMensagemOk(
                "Venda feita com sucesso, verifique as Orders."
              );
              setTimeout(this.ocultaMensagem, 3000);
              let modal = document.getElementById("modal");
              this.fadeOut(modal);
              this.show = false;
            }
          } catch (error) {
            this.show = false;
            console.log(error);
          }
        }
      } else {
        this.ocultaMsg = true;
        this.show = false;
        this.alertMensagemErro(
          "Quantidade vendida não pode ser maior que a quantidade atual."
        );
        setTimeout(this.ocultaMensagem, 3000);
      }
    },
    alertMensagemErro(mensagem) {
      this.mensagemErro = mensagem;
    },
    alertMensagemOk(mensagem) {
      this.mensagemOk = mensagem;
    },
    ocultaMensagem() {
      this.mensagemOk = "";
      this.ocultaMsgOk = false;
      this.mensagemErro = "";
      this.ocultaMsg = false;
    },
  },
};
</script>
