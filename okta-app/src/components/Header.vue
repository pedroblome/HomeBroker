<template>
    <div id="header">
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
          <button
            style="position:absolute; top: 2px;left:1150px"
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
  
   data(){
     return{
      authenticated: false,
      user:[],
      dollarBalance: "",
      infoLogin: [],
      name: "",
      email: ""
     }
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
    async getInfoLogin(){
      await Object.entries(await this.$auth.getUser()).map(entry => ({ claim: entry[0], value: entry[1] }))
      

    },
    async logout() {
      await this.$auth.signOut();
    },
    async getInfoUser() {
      this.claims = await this.$auth.getUser();
      //iduser = 1 pq n fiz o metodo de verificar qual usuario esta logado no momento. tirar esta duvida
      //o mais rapido possivel.
      const response = await axios.get("http://localhost:8088/users/1", {
        headers: { Authorization: "Bearer " + this.$auth.getAccessToken() },
      });
      console.log(this.claims.name)
      console.log(this.claims.email)

      this.name = this.claims.name
      this.email = this.claims.email

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
