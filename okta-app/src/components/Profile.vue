<template>
  <div><h1>
    <button style="color:rgb(255,155,155)">
      buscar stock ballance
    </button>
    </h1></div>
</template>

<script>
export default {
  name: 'Profile',
  data () {
    return {
      claims: []
    }
  },
  async created () {
    this.claims = await Object.entries(await this.$auth.getUser()).map(entry => ({ claim: entry[0], value: entry[1] }))
  }
}
</script>