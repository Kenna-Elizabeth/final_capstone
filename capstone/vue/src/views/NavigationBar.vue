<template>
  <div id="nav">
    <img src="../assets/valley-libraries-logo.png" />
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
img{
  height: auto;
  display: block;
}

img:hover {
  box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
}

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
  justify-content: space-evenly;
}
</style>