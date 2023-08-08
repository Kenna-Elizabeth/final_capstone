<template>
  <div id="app">
  <navigation-bar/>
    <router-view />
  </div>
</template>
<script>
  import NavigationBar from "./views/NavigationBar.vue";

  export default {
    components: {
      NavigationBar
    }
  }

</script>


<style>
header {
    background-image: linear-gradient(#05BCD9,#9DDAE6);
    display: flex;
    padding: 8px;
    width: 100vw;
    align-items: center;
    justify-content: center;
    
}
header h1 { color: white;
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