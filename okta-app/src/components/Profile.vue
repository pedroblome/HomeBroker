<template>
  <div class="flex flex-col background-color:red" >
    <div class="py-10 align-middle inline-block min-w-full sm:px-6 lg:px-8">
      <div>
        <div
        >
          <input  
          blue-200
          placeholder="Search Stock"
          @keyup="searchStock()"
          v-model="textSearch"
          class="bg-blue-200 hover:bg-green-200 order-collapse border border-slate-400 text-midle  "
        />
  
    <table
    style="      
    border-radius: 15px;
    background: #DAD9D3;
    padding: 20px;
    width: 200px;
    height: 150px;"
    id="stockBalance"  class="min-w-full divide-y divide-gray-200 border-collapse border border-slate-400  ">  
     <caption style="text-align:center; font-size:29px; background-color: #8ACDF1">Stock balance of User</caption>
        <thead>
          <tr style="background-color:#BFC9F0	"> 
            <th class="border border-slate-300  ...">Id</th>
            <th class="border border-slate-300 ...">Name</th>
            <th class="border border-slate-300 ...">Symbol</th>
            <th class="border border-slate-300 ...">Volume</th>
          </tr>
        </thead>
        <tbody> 
        <tr v-for="stock in filteredStocks" :key="stock.stock_id">
                  <th
              scope="col"
              class="
                border border-slate-300 
                px-6
                py-3
                text-midle text-xs
                font-medium
                text-black-700                
                tracking-wider
              "
            >
              {{ stock.id_stock }}
            </th>
            <th
              scope="col"
              class="
                border border-slate-300 
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                tracking-wider
              "
            >
              {{ stock.stock_name }}
            </th>
            <th
              scope="col"
              class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider"
            >
              {{ stock.stock_symbol }}
            </th>
            <th
              scope="col"
              class="
                border border-slate-300 
                px-6
                py-3
                text-left text-xs
                font-medium
                text-black-700
                tracking-wider
              "
            >
              {{ stock.volume }}
            </th>
          </tr>
        </tbody>
      </table>
    

     
        </div>
        
      </div>
        <div>
          <br>&nbsp;
          <br>&nbsp;
      
             <input  
          blue-200
          placeholder="Search Orders"
          @keyup="searchOrders()"
          v-model="textSearchOrder"
          class="bg-blue-200 hover:bg-green-200 order-collapse border border-slate-400 text-midle  "
        />
  
        </div>
        <table class=" table-auto 	table table-dark table-responsive " width = '1200'>
       
          <caption style="text-align:center; font-size:29px; background-color: #D1FA81">Order history of User </caption>

          <thead >
          <tr style=" background-color:#BFC9F0	"> 
            <th style="width:74px" class="border border-slate-300  ...">Stock</th>
            <th style="width:74px" class="border border-slate-300  ...">Created on</th>
            <th style="width:74px" class="border border-slate-300  ...">Updated on</th>
            <th style="width:74px" class="border border-slate-300  ...">Volume</th>
            <th style="width:74px" class="border border-slate-300  ...">Type Order</th>
            <th style="width:74px" class="border border-slate-300  ...">Price</th>
            <th style="width:74px" class="border border-slate-300  ...">Remaing Volume</th>
            <th style="width:74px" class="border border-slate-300  ...">Action</th>
          </tr>
          </thead>
          <tbody>
            <tr v-for="order in filteredOrders" :key="order.stock_name">
            <th style="width:74px"          class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">
               <spa n>
                {{order.stock_name}}
              </spa>
              <span class="ms-3 text-uppercase text-muted">
                {{order.stock_symbol}}
              </span>
            </th>
            <th style="width:74px"          class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">{{order.created_on}}</th>
            <th style="width:74px"          class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">{{order.updated_on}}</th>
            <th style="width:74px"          class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">{{order.volume}} un</th>
            <th v-if="order.type==1 " style="width:74px"  class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">Buy</th>
            <th v-if="order.type==0 " style="width:74px"  class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">Sell</th>
            <th style="width:74px"  class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider">U${{order.price}}</th>
            <th style="width:74px"  class="
              border border-slate-00 x-6 py-3  text-left text-xs font-medium text-black-700 tracking-wider ">{{order.remaing_volume}}un </th>
            <th style="width:20px">
               <button
               @click="closeOrder(order.id)"
              class="
                bg-red-300
                hover:bg-red-600
                text-white
                font-medium
                py-1
                px-2
                rounded-full
              "
            >
                Cancel
              </button>
            </th>
            </tr>
             
          </tbody>
           
       
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
      filteredOrders: [],
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
      this.filteredOrders = response.data
    },
     
 async closeOrder(id){ 
      console.log(id)
      const url = "http://localhost:8088/users_order/closeOrder/" + id
      
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
    },
    searchStock() {
      this.filteredStocks = this.stocks.filter(
        (stock) =>
          stock.stock_name.toLowerCase().includes(this.textSearch.toLowerCase()) ||
          stock.stock_symbol.toLowerCase().includes(this.textSearch.toLowerCase())
      );
    },
    searchOrders() {
      this.filteredOrders = this.userOrder.filter(
        (userOrder) =>
          userOrder.stock_name.toLowerCase().includes(this.textSearchOrder.toLowerCase()) ||
          userOrder.stock_symbol.toLowerCase().includes(this.textSearchOrder.toLowerCase())
      );
    },
  
                                                                          
  }

};
</script>
