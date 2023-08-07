<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<!-- added styles tag --> 
<style>
h1 {
  background-image: linear-gradient(#05BCD9,#9DDAE6);
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
}



/* @media(max-width: 1024px){
     {
        grid-template-columns: 1fr 1fr;
        grid-template-areas:
        /* ESTABLISH GRID AREAS
        */
        /* /* column-gap: 35px;
    }
}
/* @media (max-width: 450px) {
        {
           grid-template-columns: 1fr;
           grid-template-areas:  
           /* GRID AREAS TO BE ESTABLISHED 
       }
} */
</style>