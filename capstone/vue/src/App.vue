<template>
  <div id="app">
  <navigation-bar/>
    <router-view />
  </div>
</template>
<script>
  import NavigationBar from "./components/NavigationBar.vue";

  export default {
    components: {
      NavigationBar
    }
  }

</script>


<style>
.box { 
    color: white;
    background-image: linear-gradient(#05BCD9,#9DDAE6);
    height: 5em;
    width: 100vw;
    text-align: center;
}


#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
}

main {
  display: flex;
  flex-direction: column;
  align-items: center;
}



/* @media(max-width: 1024px){
     {
       
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