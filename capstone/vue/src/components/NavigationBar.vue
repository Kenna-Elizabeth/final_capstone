<template>
  <header>
  <img src="../assets/valley-libraries-logo.png" />
  <nav>
    <ul class="topNavContainer">
      <li v-if="parentLoggedIn">
       <router-link v-bind:to="{ name: 'books' }">Books</router-link>
      </li>

      <!-- <li v-if="parentLoggedIn"> | </li>  -->

      <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'family' }">Family</router-link>
      </li>

      <!-- <li v-if="parentLoggedIn"> | </li>  -->
       <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'activity' }">Activites</router-link>
      </li>

      <!-- <li v-if="parentLoggedIn"> | </li>  -->

      <li v-if="parentLoggedIn">
        <router-link v-bind:to="{ name: 'book' }">My&nbsp;Books</router-link>
      </li>

      <!-- <li v-if="$store.state.token != ''"> | </li> -->

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
  display: flex;
  flex: 1;
  align-items: center;
  flex-direction: row;
}

.topNavContainer {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.topNavContainer > li {
  flex: 1;
}

nav {
  display: flex;
  flex-direction: row;
  flex: 1;
  justify-content: flex-end;
  
}

ul {
  list-style-type: none;
  /* display: flex;
  justify-content: flex-end; */
}

li {

  padding: 0.2em;
  font-weight: bold;
  border: 2px solid  rgb(231, 229, 233);
  border-radius: 10px 10px;
  margin: 0.2em;
  
}

li a {
  color: black;
  text-decoration: none;

}

li a:hover {
  color: #05BCD9;
  
}


@media only screen and (max-width: 768px){
  header {
  display: flex;
  align-content: center;
  justify-content: center;
  }
}
@media only screen and (max-width: 768px){
  nav {
  display: none;
  }
}

</style>