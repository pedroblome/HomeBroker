<template>
    <div id="header">
    <nav>
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
          <a v-if="authenticated">
           U${{dollarBalance}}
          </a>     
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
  
   data(){
     return{
      authenticated: false,
      user:[],
      dollarBalance: ""
     }
   },
  async created() {
    await this.isAuthenticated();
    this.getInfoUser();
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
    async getInfoUser() {
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get("http://localhost:8088/users/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(response.data)
      this.user = response.data
      this.dollarBalance=this.user.dollar_balance
    

    }
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