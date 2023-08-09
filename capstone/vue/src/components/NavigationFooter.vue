<template>
  <footer>
  <nav>
    <ul>
      <li><router-link v-bind:to="{ name: 'books' }">📚</router-link></li>
      <li><router-link v-bind:to="{ name: 'family' }">👨‍👩‍👧‍👦</router-link></li>
      <li v-if="$store.state.token != ''">
        <router-link
        v-bind:to="{ name: 'logout' }">🔚</router-link></li>
    </ul>
  </nav>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>


footer {
  list-style-type: none;
  display: flex;
  align-items: center;
  flex-direction: row;
  background-image: linear-gradient(#05BCD9,#9DDAE6);
}

ul {
  list-style-type: none;
  display: flex;
  justify-content: space-evenly;
}

nav {
  flex-grow: 1;
}

li {
  
  padding-left: 2em;
  padding-right: 2em;
}
</style>