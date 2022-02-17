<template>
  <div id="app2">
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
        </tr>
      </div>
    </nav>
    <div id="content">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app",
  data: function () {
    return { authenticated: false };
  },
  async created() {
    await this.isAuthenticated();
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
  },
};
</script>

<style>
nav div a {
  margin-right: 300px;
}
#app {
  width: 1200;
  color: rgb(61, 13, 13);
  margin: 0 auto;
}
a {
  color: blue;
  text-decoration: underline;
  cursor: pointer;
}
</style>