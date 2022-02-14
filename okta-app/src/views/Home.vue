<!-- This example requires Tailwind CSS v2.0+ -->
<template>
  <div class="flex flex-col">
    <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
      <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
        <div
          class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg "
          <!-- escrolar a pagina ; overflow-y-auto -->
  
        >
          <table class="min-w-full divide-y divide-gray-120">
            <thead class="bg-gray-50">
              <tr>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Stock ID</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Stock name</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Stock Symbol</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-blue-700 uppercase tracking-wider">AskMin PRICE</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-blue-700 uppercase tracking-wider">AskMax PRICE</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-orange-500 uppercase tracking-wider">BidMin PRICE</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-orange-500 uppercase tracking-wider">BidMax PRICE</th>
              </tr>
                <tr v-for="stock in filteredStocks" :key="stock.stock_id">
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">{{stock.id}} </th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">{{stock.stock_name}}</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">{{stock.stock_symbol}}</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">$ {{stock.ask_min}}</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">$ {{stock.AskMax}}</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">$ {{stock.BidMin}}</th>
                <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">$ {{stock.bid_max}}</th>
                
                
                
              </tr>
              
            </thead>  
           <thead>
       
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
      filteredStocks: []
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
      }
    },
    async getStocks() {
      const response = await axios.get("http://localhost:8089/stocks/", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data);
      this.stocks = response.data
      this.filteredStocks = response.data
    },
     searchStock() {
      this.filteredStocks = this.stocks.filter(
        (stock) =>
          stock.stock_name.toLowerCase().includes(this.textSearch.toLowerCase()) ||
          stock.stock_symbol.toLowerCase().includes(this.textSearch.toLowerCase())
      );
    },
  },
};
</script>