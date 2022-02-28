<!-- This example requires Tailwind CSS v2.0+ -->
<template>
<div class="py-5 align-middle inline-block min-w-full sm:px-6 lg:px-8" style="background-color:#2EACB8">
        <div
          class="shadow overflow-hidden border-b border-red-10 sm:rounded-lg "
        >
        <!-- parte para abertura de ordens. compra e venda -->
          <div v-if="openModal" id="defaultModal" aria-hidden="true" class=" flex  overflow-y-auto overflow-x-hidden fixed right-0 left-0 top-4 z-50 justify-center items-center h-modal md:h-full md:inset-0">
                      <div class="  relative px-4 w-full max-w-2xl h-full md:h-auto">
                        <!-- Modal content -->
                        <div class=" bg-blue-200 relative bg-white rounded-lg shadow dark:bg-gray-700">
                            <!-- Modal header -->
                            <div class="flex justify-between items-start p-5 rounded-t border-b dark:border-gray-600">
                                <h3 class="text-xl font-semibold text-gray-900 lg:text-2xl dark:text-white">
                                   Open Order
                                </h3>
                                <button @click="openModal=false" type="button" class="text-gray-300 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="defaultModal">
                                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>  
                                </button>
                            </div>
                            <!-- Modal body -->
                            <div class="p-6 space-y-6">
                             <thead>
                                  <tr class="text-gray-900">
                                  Name: {{stock_name}}
                                </tr>
                                <tr class="text-gray-900">
                                  Symbol: {{stock_symbol}}
                                </tr>
                                <tr class="text-gray-900">
                                  id: {{id}}
                                </tr>
                             </thead>
                             <thead>
                                <tr>
                                  Type Order:&nbsp;
                                  
                                    <input type="radio" name = "typerOrder" id="one" value="0" v-model="picked" >
                                    <label for="one">Sell</label>&nbsp;&nbsp;&nbsp;
                                    <input type="radio" name = "typerOrder" id="two" value="1" v-model="picked">
                                    <label for="two">Buy</label>
                                    <br>
                                  Volume Order: 
                                <input v-model="volume" placeholder=" vol" style="width:54px; background-color:#BFDBFE; border-width:2;  " type="number" step="1" min="1" class="                border border-slate-300 
                                "> <label for=""></label>
                                <br>
                                  Price: R$ 
                                <input class="border " v-model="price" placeholder=" price" style="width:74px; background-color:#BFDBFE; border-width:2;  " type="number" step="1" min="1"> <label for=""></label>
                                     
                                </tr>
                             </thead>
                            </div>
                            <!-- Modal footer -->
                            <div class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200 dark:border-gray-600">
                                <button @click="postOrdeStock" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Do Order</button>
                          
                                <button @click="openModal=false"  type="button" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:ring-gray-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
          <!-- parte para deposito de dolar -->
        <div class="bg-gray-300">
        <input
          type="text"
          border
          width="100px"
          placeholder="Search Stock"
          @keyup="searchStock()"
          v-model="textSearch"
          class="bg-gray-300 hover:bg-green-200"
        />
        <div class="bg-gray-500">
          <table id="tableStock" class="w-full table-auto" style="widht: 200px">
            <caption
              style="
                text-align: center;
                font-size: 29px;
                background-color: gray;
              "
            >
              Home Broker
            </caption>
            <thead class="bg--200 py-5">
              <tr class="bg-gray-500">
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium              
                    uppercase
                    tracking-wider
                  "
                >
                  Stock id
                </th>
                <th
                  style="position: relative; left: -5px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  Stock name
                </th>
                <th
                  style="position: relative; left: -5px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  Stock Symbol
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  AskMin PRICE
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  AskMax PRICE
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  BidMin PRICE
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  BidMax PRICE
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    uppercase
                    tracking-wider
                  "
                >
                  Order/ Graphic
                </th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="w-full overflow-y-auto" style="height: 440px">
          <table class="w-full table-auto">
            <thead class="bg-gray-300 py-5">
              <tr
                class="overflow-y-auto"
                v-for="stock in filteredStocks"
                :key="stock.stock_id"
              >
                <td
                  style="position:sticky;"
                  scope="col"
                  class="
                    text-center
                    px-6
                    py-3
                    text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  {{ stock.id }}
                </td>
                <td
                  style="position: sticky; left: 120px; width: 50px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  {{ stock.stock_name }}
                </td>
                <td
                  style="position: sticky; left: 0px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  {{ stock.stock_symbol }}
                </td>
                <span
                  style="position: sticky; left: 350px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  $ {{ stock.ask_min }}
                </span>
                <td
                  style="position: sticky; left: 570px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  $ {{ stock.ask_max }}
                </td>
                <td
                  style="position: sticky; left: 730px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  $ {{ stock.bid_min }}
                </td>
                <td
                  style="position: sticky; left: 882px"
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-large
                    text-black-700
                    uppercase
                    tracking-wider
                  "
                >
                  $ {{ stock.bid_max }}
                </td>
                <th class="flex padding=10px">
                  <button
                    style="position: sticky; left: 1050px;"
                    @click="
                      openOrder(stock.id, stock.stock_name, stock.stock_symbol)
                    "
                    class="
                      block
                      text-white
                      bg-blue-500
                      hover:bg-blue-800
                      rounded-lg
                      text-m
                      p-1.5
                    "
                    type="button"
                  >
                    Order
                  </button>
                  &nbsp; &nbsp;
                  <button
                    style="position: sticky; left: 1115px"
                    class="
                      block
                      text-white
                      bg-green-500
                      hover:bg-green-800
                      rounded-lg
                      text-m
                      p-1.5
                    "
                    type="button"
                  >
                    Graphic
                  </button>
                </th>
              </tr>
            </thead>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "getStocks",
  data() {
    return {
      stocks: [],
      filteredStocks: [],
      textSearch: "",
      openModal: false,
      stock_name: "",
      stock_symbol: "",
      id: "",
      volume: "",
      price: "",
      picked: "",
    };
  },

  created() {
    this.getToken();
    this.getStocks();
  },
  methods: {
    async getToken() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        console.log("Bearer " + accessToken);
        console.log("chegou no bearer token");
      }
    },
    async getStocks() {
      const response = await axios.get("http://localhost:8089/stocks/", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.stocks = response.data;
      this.filteredStocks = response.data;
      console.log("Bearer " + this.$auth.getAccessToken());
    },
    async openOrder(id, nome, symbol) {
      this.openModal = !this.openModal;
      console.log(id, nome, symbol);
      this.stock_name = nome;
      this.stock_symbol = symbol;
      this.id = id;
    },
    async postOrdeStock() {
      const body = {
        id_user: 1,
        id_stock: this.id,
        price: this.price,
        status: 1,
        stock_name: this.stock_name,
        stock_symbol: this.stock_symbol,
        type: this.picked,
        volume: this.volume,
      };
      try {
        var now = new Date();

        const response = await axios.post(
          "http://localhost:8088/users_order",
          body,
          {
            headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
          }
        );
        window.alert("Order added with success! \n\n" + now);
        this.openModal = !this.openModal;
        console.log(response);
        console.log(body);
      } catch (error) {
        window.alert(error.response.data.message + "\n\n" + now);
        this.openModal = !this.openModal;
        console.log(error.response.data.message);
        console.log(body);
      }
    },
    searchStock() {
      this.filteredStocks = this.stocks.filter(
        (stock) =>
          stock.stock_name
            .toLowerCase()
            .includes(this.textSearch.toLowerCase()) ||
          stock.stock_symbol
            .toLowerCase()
            .includes(this.textSearch.toLowerCase())
      );
    },
  },
};
</script>
<style>
body {
}
</style>
