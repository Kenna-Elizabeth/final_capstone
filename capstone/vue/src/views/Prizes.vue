<template>
  <main class="prizes">
    <div class="box">
     <h1>Prizes</h1>
    </div>
     <prizes-form/>
  </main>
</template>

<script>
import PrizesForm from '../components/PrizesForm.vue';

export default {
  components: { 
    PrizesForm,
    },

  name: "prizes",
  data() {
    return {
      
    };
  },
};
</script>

<style scoped>
main {
    color: white;
    background-image: linear-gradient(var(--dark-blue),var(--light-blue));
    height: 5em;
    width: 100vw;
    text-align: center;
}

</style>