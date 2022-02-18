<template>
  <div>
    <br />
    <tr style="font-size: 30px; position: center">
      Table of User Stock Balance
    </tr>
    <tr>
      User dollar balance:
      {{
        user.dollar_balance
      }}
    </tr>
    <div>
      <input type="text" />

      <table style="width: 500px; background-color: pink">
        <thead>
          <tr>
            <td
              scope="col"
              class="
                px-0
                py-3.5
                text-midle text-xs
                font-medium
                text-gray-800
                uppercase
                tracking-wider
              "
            >
              Stock ID
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Stock name
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Stock Symbol
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Volume
            </td>
          </tr>
          <tr v-for="stock in filteredStocks" :key="stock.stock_id">
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ stock.id_stock }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ stock.stock_name }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ stock.stock_symbol }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ stock.volume }}
            </th>
          </tr>
        </thead>
      </table>
      <br />

      <table style="width: 500px; background-color: green">
        <thead>
          <tr>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Stock name
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Stock Symbol
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Order ID:
            </td>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              Created on
            </th>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Volume Order
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Price
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Remaing Volume
            </td>
            <td
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-grey-800
                uppercase
                tracking-wider
              "
            >
              Uptaded On
            </td>
          </tr>
          <tr v-for="order in userOrder" :key="order.id">
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.stock_name }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.stock_symbol }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.id }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.created_on }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.volume }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              U${{ order.price }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.remaing_volume }}
            </th>
            <th
              scope="col"
              class="
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700
                uppercase
                tracking-wider
              "
            >
              {{ order.updated_on }}
            </th>
            <button
              @click="closeOrder(order.id)"
              style="color: red; background-color: blue"
            >
              close order
            </button>
          </tr>
        </thead>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      stocks: [],
      filteredStocks: [],
      user: [],
      userOrder: [],
      order_id: ""
    }
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
      const response = await axios.get("http://localhost:8088/user_stock_balance/user/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.stocks = response.data
      this.filteredStocks = response.data
    },
     async getInfoUser() {
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get("http://localhost:8088/users/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.user = response.data
    },
    async getUserOrder(){
     const response = await axios.get("http://localhost:8088/users_order/byUser/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.userOrder = response.data
    },
     
 async closeOrder(order_id){ 
      console.log(order_id)
      const url = "http://localhost:8088/users_order/closeOrder/" + order_id
      
       await fetch(url, {
        method: "PUT",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.$auth.getAccessToken(),
        },
      }).then((response) => {
      console.log(response)
      window.alert("Order Deleter with sucess")
      console.log("ta chamando a função ao menos")
      console.log(response.data);
      }
      ).catch(()=>{
        console.log("error")

      })
    }
  
                                                                          
  }

};
</script>