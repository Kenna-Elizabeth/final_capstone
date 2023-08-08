<template>
  <div id="nav">
    <li><img src="../assets/valley-libraries-logo.png" /></li>
    <ul>
      <li><router-link v-bind:to="{ name: 'books' }">Books</router-link> | </li>
      <li><router-link v-bind:to="{ name: 'family' }">Family</router-link></li>
      <li v-if="$store.state.token != ''"> | </li>
      <li>
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''">Logout</router-link
        >
      </li>
    </ul>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#nav {
  list-style-type: none;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: space-between;
}

ul {
  list-style-type: none;
  display: flex;
  justify-content: space-between;
}

li {
  display: flex;
  justify-content: space-between;
}
</style>