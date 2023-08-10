<template>
  <footer>
  <nav>
    <ul>
      <li><router-link v-bind:to="{ name: 'family' }">👨‍👩‍👧‍👦</router-link></li>
      <li><router-link v-bind:to="{ name: 'books' }">📚</router-link></li>
      <li><router-link v-bind:to="{ name: 'activity' }">📋</router-link></li>
      <li><router-link v-bind:to="{ name: 'book' }">📖</router-link></li>
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
  position: fixed;
  bottom: 0em;
  margin-top: 0.5em;
  left: 0em;
  right: 0em; 
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
  background-color: rgb(184, 237, 250);
  border-radius: 1em;
  padding-left: 0.5em;
  padding-right: 0.5em;
}
 a:link {
      text-decoration: none;
}

a:visited {
      text-decoration: none;
}

a:hover {
      text-decoration: none;
     
}

a:active {
      text-decoration: none;
}

</style>