<template>
  <footer>
    <nav v-if="$store.state.token != ''">
      <ul>
        <li><router-link v-bind:to="{ name: 'bookRecommended' }">
          📖</router-link></li>
        
        <li><router-link v-bind:to="{ name: 'books' }">
          📚</router-link></li>

        <li><router-link v-bind:to="{ name: 'activity' }">
          📋</router-link></li>
          
        <li v-if="parentLoggedIn"><router-link v-bind:to="{ name: 'family' }">
          👨‍👩‍👧‍👦</router-link></li>

        <li><router-link v-bind:to="{ name: 'prizes' }">
          🏆</router-link></li>

        <li><router-link v-bind:to="{ name: 'logout' }">
          🔚</router-link>
        </li>
      </ul>
    </nav>
  </footer>
</template>

<script>
export default {
  computed: {
    parentLoggedIn() {
      return (
        this.$store.state.user.authorities[0].name == "ROLE_PARENT"
      );
    },
  },
};
</script>

<style scoped>

footer{
  display: none;
}
@media only screen and (max-width: 768px) {
  footer {
    list-style-type: none;
    display: flex;
    align-items: center;
    flex-direction: row;
    background-image: linear-gradient(var(--dark-blue),var(--light-blue));
    position: fixed;
    bottom: 0em;
    margin-top: 0.5em;
    left: 0em;
    right: 0em;
  }

  ul {
    line-height: 2em;
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
    padding-left: 0.8em;
    padding-right: 0.8em;
  }
  a:link {
    font-size: 1.5em;
    text-decoration: none;
  }

  a:visited {
    font-size: 1.5em;
    text-decoration: none;
  }

  a:hover {
    font-size: 1.5em;
    text-decoration: none;
  }

  a:active {
    font-size: 1.5em;
    text-decoration: none;
  }
}
</style>