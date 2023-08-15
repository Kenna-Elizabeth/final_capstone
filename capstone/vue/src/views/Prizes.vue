<template>
  <div class="prizes">
     <prizes-form/>
  </div>
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
   created() {
    this.$store.dispatch('retrievePrizes');
  },
};
</script>

<style scoped>

</style>