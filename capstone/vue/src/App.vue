<template>
  <div id="app">
  <navigation-bar/>
    <router-view />
    <navigation-footer/>
  </div>
</template>
<script>
  import NavigationBar from "./components/NavigationBar.vue";
  import NavigationFooter from './components/NavigationFooter.vue';

  export default {
    components: {
      NavigationBar,
      NavigationFooter

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

/*@media(min-width: 1025px){

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