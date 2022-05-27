<template>
    
    <div id="header">
          <div v-if="openModal" id="defaultModal" aria-hidden="true" class=" flex  overflow-y-auto overflow-x-hidden fixed right-0 left-0 top-4 z-50 justify-center items-center h-modal md:h-full md:inset-0">
                      <div class="  relative px-4 w-full max-w-2xl h-full md:h-auto">
                        <!-- Modal content -->
                        <div class=" bg-blue-200 relative bg-white rounded-lg shadow dark:bg-gray-700">
                            <!-- Modal header -->
                            <div class="flex justify-between items-start p-5 rounded-t border-b dark:border-gray-600">
                                <h3 class="text-xl font-semibold text-gray-900 lg:text-2xl dark:text-white">
                                   Do Deposit U$ 
                                </h3>
                                <button @click="openModal=false" type="button" class="text-gray-300 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="defaultModal">
                                    <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>  
                                </button>
                            </div>
                            <!-- Modal body -->
                            <div class="p-6 space-y-6">
                             <thead>
                                <tr>
                                  Amount: U$ 
                                <input class="border " v-model="amount" placeholder=" amount" style="width:82px; background-color:#BFDBFE; border-width:2;  " type="number" step="1" min="1"> <label for=""></label>
                                     
                                </tr>
                             </thead>
                            </div>
                            <!-- Modal footer -->
                            <div class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200 dark:border-gray-600">
                                <button @click="deposit" type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Confirm Deposit</button>
                          
                                <button @click="openModal=false"  type="button" class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:ring-gray-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600">Cancel</button>
                            </div>
                        </div>
                    </div>
                </div>
    <nav class="bg-gray-800" style="color:white; height:40px; rounded" >
          <div>
        <tr>
          <router-link to="/">
            <button 
              class="
                bg-blue-400
                hover:bg-blue-600
                text-white
                font-medium
                py-1.5
                px-4
                rounded-full
              "
            >
              Home
            </button>
          </router-link>
          <router-link to="/login" v-if="!authenticated">
            <button 

               class="
                bg-blue-400
                text-white
                font-medium
                py-1.5
                px-3
                rounded-full
              "
            >
              login
            </button>
          </router-link>
          <router-link to="/profile" v-if="authenticated">
            <button
             style="position:relative; left:24px"
              class="
                bg-blue-400
                hover:bg-blue-600
                text-white
                font-medium
                py-1.5
                px-3
                rounded-full
              "
            >
              Profile
            </button>
          </router-link>
          <a v-if="authenticated" v-on:click="logout()">
            <button
              style="position:absolute; top:2px; left:1190px"
              class="
                bg-blue-400
                hover:bg-blue-600
                text-white
                font-medium
                py-1.5
                px-3
                rounded-full
              "
            >
              logout
            </button>
          </a>
          <button v-if="authenticated"
            @click="openDepositWindow"
            style="position:absolute; top: 2px;left:850px"
            class="
              bg-green-400
              hover:bg-green-600
              text-white
              font-medium
              py-1.5
              px-3
              rounded-full
            "
          >DEPOSIT</button>

          <span v-if="authenticated" style="position: absolute;
            top: 10px; left: 555px; ">
            Bem vindo: {{name}}
          </span>

          <span v-if="authenticated" style="position: absolute;
           top: 10px; left: 965px; ">
           Ballance: U$ {{dollarBalance}}
         </span>
          
    <th>
      <td>
        
      </td>
    </th>
        </tr>

      </div>
    </nav>
    <div id="content">
      <router-view />
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "Header",

  data() {
    return {
      openModal: false,
      authenticated: false,
      user: [],
      dollarBalance: "",
      infoLogin: [],
      name: "",
      email: "",
    };
  },
  async created() {
    this.getInfoLogin();
    await this.isAuthenticated();
    this.getInfoUser();
    this.claims = await this.$auth.getUser();

    // this.claims = await Object.entries(await this.$auth.getUser()).map(entry => ({ claim: entry[0], value: entry[1] }))
  },
  watch: {
    // Everytime the route changes, check for auth status
    $route: "isAuthenticated",
  },
  methods: {
    async isAuthenticated() {
      this.authenticated = await this.$auth.isAuthenticated();
    },
    async getInfoLogin() {
      await Object.entries(await this.$auth.getUser()).map((entry) => ({
        claim: entry[0],
        value: entry[1],
      }));
    },
    async logout() {
      console.log("chamando o logout");

      await this.$auth.signOut();
    },
    async getInfoUser() {
      this.claims = await this.$auth.getUser();
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get("http://localhost:8088/users/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(this.claims.name);
      console.log(this.claims.email);

      this.name = this.claims.name;
      this.email = this.claims.email;

      this.user = response.data;
      this.dollarBalance = this.user.dollar_balance;
    },
    async openDepositWindow() {
      this.openModal = !this.openModal;
    },
    async deposit() {
      const body = {
        id: 1,
        amount: this.amount,
      };
      try {
        var now = new Date();

        const response = await axios.put(
          "http://localhost:8088/users/deposit",
          body,
          {
            headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
          }
        );
        window.alert("Depoist done with success! \n\n" + now);
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
  },
};
</script>

<style>
#Header {
  width: 1200;
  color: rgb(61, 13, 13);
  padding: 10px;
}
a {
  color: blue;
  text-decoration: underline;
  cursor: pointer;
}
</style>
