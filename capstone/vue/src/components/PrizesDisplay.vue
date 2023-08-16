<template>
  <main class="prize-add-form">
    <div class="box">
     <h1>Prizes</h1>
    </div>
      <div>
      <button @click="showAddForm = !showAddForm">
        {{ showAddForm ? 'Cancel' : 'Add Prize' }}
      </button>
    </div>
    <add-prize-form v-if="showAddForm" 
    @create-prize="$store.dispatch('retrievePrizes')" 
    />
    <section id="prize-display">
      <div v-for="prize in $store.state.prizes" v-bind:key="prize.id" class="prize-panel">
        <div class="prize-name"> {{ prize.prizeName }} </div>
        <div class="prize-description"> {{ prize.description }}</div>
        <div class="prize-milestone">{{ prize.milestone}} </div>
        <div v-if="prize.forParents" class="prize-user-group">Parent Prize</div>
        <div v-if="prize.forChildren" class="prize-user-group">Child Prize</div>
        <div class="prize-maximum">{{ prize.maxPrizes }}</div>
        <div class="prize-start-date">{{ prize.startDate }}</div>
        <div class="prize-end-date">{{ prize.endDate }}</div>
        <div class="prize-completed">{{ prize.completed }}</div>
      </div>
    </section>
  </main>
</template>

<script>
import AddPrizeForm from './AddPrizeForm.vue';

export default {
     data() {
    return {
      showAddForm: false,
    };
  },
    name: "prizes-form",

    components: {
      AddPrizeForm
    }
  
}
</script>

<style scoped>
button {
  margin-top: 1em;
  margin-bottom: 1em;
  padding: 0.6em;
  padding-left: 1.5em;
  padding-right: 1.5em;
  border: 2px solid black;
  border-radius: 8px;
  background-color:#31b4c9;
  font-weight: 800;
  color: white;
}
button:hover {
  background-color: #1590a3;
}


</style>