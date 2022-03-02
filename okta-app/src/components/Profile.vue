<template>
  <div
    class="py-5 align-middle inline-block min-w-full sm:px-6 lg:px-8"
    style="background-color: #2eacb8"
  >
    <div
      class="
        shadow
        overflow-hidden
        border-b border-red-10
        sm:rounded-lg
        bg-gray-300
      "
    >
      <input
        type="text"
        blue-200
        border
        width="100px"
        placeholder="Search Stock"
        @keyup="searchStock()"
        v-model="textSearch"
        class="bg-blue-200 hover:bg-green-200"
      />
      <div>
        <table id="balanceStock" class="w-full table-auto" style="widht: 200px">
          <caption
            style="text-align: center; font-size: 29px; background-color: gray"
          >
            User Stock Balance
          </caption>
          <thead class="bg-gray-600 py-5">
            <tr>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Id
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Stock
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Volume
              </th>
            </tr>
          </thead>
        </table>
      </div>
      <div
        id="div-table"
        class="w-full overflow-y-auto bg-gray-300"
        style="height: 120px"
      >
        <table id="balanceStock" class="w-full table-auto">
          <thead class="bg-gray-300 py-5">
            <tr v-for="stock in filteredStocks" :key="stock.stock_id">
              <td
                style="font-size: 17px"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.id_stock }}
              </td>
              <td
                style="font-size: 17px; position:relative; left: 25px"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.stock_symbol }}
                <span style="font-size: 12px; color: gray">{{
                  stock.stock_name
                }}</span>
              </td>
              <td
                style="font-size: 17px; position:relative; left: -155px"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.volume }} un.
              </td>
            </tr>
          </thead>
        </table>
      </div>
    </div>

    <div
      class="
        shadow
        overflow-hidden
        border-b border-red-10
        sm:rounded-lg
        bg-gray-300
      "
    >
      <input
        type="text"
        blue-200
        border
        width="100px"
        placeholder="Search Order"
        @keyup="searchOrders"
        v-model="textSearchOrder"
        class="bg-blue-200 hover:bg-green-200"
      />
      <div>
        <table id="balanceStock" class="w-full table-auto" style="widht: 200px">
          <caption
            style="text-align: center; font-size: 29px; background-color: gray"
          >
            Users Orders Open
          </caption>
          <thead 
          class="bg-gray-600 py-4">
            <tr>
              <th
                style="width: 13px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Id
              </th>
              <th
                style="width: 43px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Stock
              </th>
              <th
                style="width: 43px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Created on
              </th>
              <th
                style="width: 43px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Volume
              </th>
              <th
                style="width: 40px; position: relative; left:-60px"

                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Price
              </th>
              <th
                style="width: 40px;  position:relative; left:-50px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Updated on
              </th>
              <th
                style="width: 43px;  position:relative; left:-65px"
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-grey-500
                  uppercase
                  tracking-wider
                "
              >
                Remaing Volume
              </th>
            </tr>
          </thead>
        </table>
      </div>
      <div class="w-full overflow-y-auto" style="height: 200px">
        <table id="userOrders" class="w-full table-auto">
          <thead class="bg-gray-300 py-5">
            <tr v-for="stock in filteredOrders" :key="stock.stock_id">
              <td
                style="font-size: 17px"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.id_stock }}
              </td>
              <td
                style="font-size: 17px"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.stock_symbol }}
                <span style="font-size: 12px; color: gray">{{
                  stock.stock_name
                }}</span>
              </td>
              <td
                style="font-size:12px; position: relative; left:-48px"
                class="
                  px-6
                  py-3
                  text-right text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.created_on }}
              </td>
              <td
                style="font-size: 17px; relative;position:relative; left:-68px"
                class="
                  px-6
                  py-3
                  text-right text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.volume }} un.
              </td>
              <td
                style="font-size: 17px;  position:relative; left:-53px"
                class="
                  px-6
                  py-3
                  text-right text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                U${{ stock.price.toFixed(2) }}
              </td>
              <td
                style="font-size: 12px;  position:relative; left:-60px"
                class="
                  px-6
                  py-3
                  text-right text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.updated_on }} un.
              </td>
              <td
                style="font-size: 17px;  position:relative; left:-60px"
                class="
                  px-6
                  py-3
                  text-right text-xs
                  font-large
                  text-black-700
                  tracking-wider
                "
              >
                {{ stock.remaing_volume }} un.
              </td>
              <td>
                <button
                  @click="closeOrder(stock.id)"
                  style="background-color: red"
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
                  Cancel
                </button>
              </td>
            </tr>
          </thead>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      stocks: [],
      filteredStocks: [],
      user: [],
      userOrder: [],
      filteredOrders: [],
      order_id: "",
    };
  },

  created() {
    this.getToken();
    this.getStockBallance();
    this.getInfoUser();
    this.getUserOrder();
  },
  methods: {
    async getToken() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        console.log("Bearer " + accessToken);
      }
    },
    async getStockBallance() {
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get(
        "http://localhost:8088/user_stock_balance/user/1",
        {
          headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
        }
      );
      console.log(response.data);
      this.stocks = response.data;
      this.filteredStocks = response.data;
    },
    async getInfoUser() {
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get("http://localhost:8088/users/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.user = response.data;
    },
    async getUserOrder() {
      const response = await axios.get(
        "http://localhost:8088/users_order/byUser/1",
        {
          headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
        }
      );
      console.log(response.data);
      this.userOrder = response.data;
      this.filteredOrders = response.data;
      console.log("Bearer " + this.$auth.getAccessToken());
    },

    async closeOrder(id) {
      console.log(id);
      const url = "http://localhost:8088/users_order/closeOrder/" + id;

      await fetch(url, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.$auth.getAccessToken(),
        },
      })
        .then((response) => {
          console.log(response);
          window.alert("Order Deleter with sucess");
          console.log("ta chamando a função ao menos");
          console.log(response.data);
        })
        .catch(() => {
          console.log("error");
        });
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
    searchOrders() {
      this.filteredOrders = this.userOrder.filter(
        (userOrder) =>
          userOrder.stock_name
            .toLowerCase()
            .includes(this.textSearchOrder.toLowerCase()) ||
          userOrder.stock_symbol
            .toLowerCase()
            .includes(this.textSearchOrder.toLowerCase())
      );
    },
  },
};
</script>
