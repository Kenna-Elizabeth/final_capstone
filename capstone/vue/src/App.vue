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
h1 { color: white;
background-image: linear-gradient(#05BCD9,#9DDAE6);
    padding: 8px;
    width: 100vw;
    text-align: center;
}


#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
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