<template>
  <header>
  <img src="../assets/valley-libraries-logo.png" />
  <nav>
    <ul>
      <li><router-link v-bind:to="{ name: 'books' }">Books</router-link></li>

      <li v-if="parentLoggedIn"> | </li> 

      <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'family' }">Family</router-link>
      </li>

      <li v-if="parentLoggedIn"> | </li> 
       <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'activity' }">Activites</router-link>
      </li>

      <li v-if="parentLoggedIn"> | </li> 

      <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'book' }">My&nbsp;Books</router-link>
      </li>

      <li v-if="$store.state.token != ''"> | </li>

      <li v-if="$store.state.token != ''">
        <router-link
        v-bind:to="{ name: 'logout' }"
          >Logout</router-link
        >
      </li>
    </ul>
  </nav>
  </header>
</template>

<script>
export default {
  computed: {
    parentLoggedIn() {
      return this.$store.state.token != '' && this.$store.state.user.authorities[0].name == 'ROLE_PARENT';
    }
  }
};
</script>

<style scoped>
img{
  height: 5em;
  display: block;
  padding: 2em;
}

img:hover {
  box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
}

header {
  list-style-type: none;
  display: flex;
  align-items: center;
  flex-direction: row;
  
}

ul {
  list-style-type: none;
  display: flex;
  justify-content: flex-end;
  
  
}

nav {
  flex-grow: 1;
  margin-right: 20px;
  
  
}

li {
  padding-left: 2em;
  font-weight: bold;
}

li a {
  color: black;
  text-decoration: none;
}

li a:hover {
  color: #05BCD9;
  text-decoration: underline;
}

</style>