<template>
<div id="nav">
      <img src="../assets/valley-libraries-logo.png">
      <router-link v-bind:to="{ name: 'books' }">Books</router-link> | 
      <router-link v-bind:to="{ name: 'family' }">Family</router-link>
      <span v-if="$store.state.token != ''"> | </span>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
    
    </div>
  
</template>

<script>
export default {

}
</script>

<style>

</style>