<template>
  <main class="prize-add-form">
    <login-display/>
    <div class="box">
     <h1>Prizes</h1>
    </div>
      <div>
      <button @click="toggleForm()">
        {{ showAddForm ? 'Cancel' : 'Add Prize' }}
      </button>
    </div>
    <add-prize-form v-if="showAddForm" 
    :editPrize="editPrize"
    @create-prize="$store.dispatch('retrievePrizes')" 
    />
    <section id="prize-display">
      <div v-for="prize in $store.state.prizes" v-bind:key="prize.id" class="prize-panel">
        <div class="prize-name">
          {{ prize.prizeName }}
        </div>
        <div class="prize-description">
          {{ prize.description }}
        </div>
        <div class="prize-milestone">
          <section id="label">Minutes to read:</section> {{ prize.milestone}}
        </div>
        <div class="prize-user-group">{{ prizeEligibility(prize) }} Prize</div>
        <div class="prize-maximum">
          <section id="label">Max Prizes:</section>{{ prize.maxPrizes == 0? "Unlimited" : prize.maxPrizes }}
        </div>
        <div class="prize-start-date">
          <section id="label">Start:</section> 
            {{ timeStampDate(prize.startDate) }}
        </div>
        <div class="prize-end-date">
          <section id="label">End:</section>  
          {{ timeStampDate(prize.endDate) }}
        </div>
        <div class="prize-completed">
         <section id="label">Completed?:</section> {{ prize.completed }}
        </div>
        <div>  
          <section id="label">Progress</section>
        </div>

        <div class="progress-bar">
          <div class="progress-color" :style="{width: progressPercent(prize.progressMinutes,prize.milestone)+'%'}">{{ progressPercent(prize.progressMinutes,prize.milestone) }}%</div> 
        </div>
        <div class="edit-button">  
          <button @click="openUpdateForm(prize)" :disabled="showAddForm">Edit Prize</button>
          <button v-on:click="deletePrize(prize.id)">Delete Prize</button> 
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import PrizesService from '../services/PrizesService';
import AddPrizeForm from './AddPrizeForm.vue';
import LoginDisplay from './LoginDisplay.vue';

export default {
  data() {
    return {
      showAddForm: false,
      editPrize: {}
    };
  },
  name: "prizes-form",
  methods: {
    toggleForm() {
      this.showAddForm = !this.showAddForm
      if (!this.showAddForm) {
        this.editPrize = {};
      }
    },
    prizeEligibility( prize ) {
      let str = "";
      if (prize.forChildren) {
        str += "Child ";
      }
      if (prize.forChildren && prize.forParents) {
        str += "and ";
      }
      if (prize.forParents) {
        str += "Parent ";
      }
      return str;
    },
    timeStampDate( timestamp ) {
      if(timestamp != undefined){
      const parts = timestamp.split(/[T .]/);
      return parts[0];
      }
    },
    progressPercent(minutes,total) {
      return Math.min(Math.floor((minutes/total)*(100)),100);
    },
    openUpdateForm(prize) {
      if (!this.showAddForm) {
        this.editPrize = prize;
        this.editPrize.startDate = this.timeStampDate(this.editPrize.startDate);
        this.editPrize.endDate = this.timeStampDate(this.editPrize.endDate);
        this.showAddForm = true;
      }
    },
    deletePrize(id){
      if (confirm('Are you sure you want to delete this prize?')) {
        PrizesService.deletePrize(id)
        .then(response => {
          if(response.status === 200) {
            this.$store.commit("DELETE_PRIZE", id);
          }
        });
      }
    },
    
  },
  components: {
    AddPrizeForm,
    LoginDisplay
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

#prize-display {
  margin-top: .5em;
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  margin-bottom: 3em;
}

.prize-panel {
  margin: 0.4em;
  border: 2px solid black;
  border-radius: 8px;
  background-color: #eef2f3;
  padding: .4em;
  width: 15em;
  height: auto; 
}

.prize-panel:hover {
  box-shadow: 3px 4px #9DDAE6;
}

.prize-name{
  font-size: 1.5em;
  font-weight: bold;
  text-align: center;
  border-bottom: 2px solid black;
  margin: .5em;
  color: rgb(20, 20, 119);
  padding-bottom: .3em;
}

.prize-description {
  font-size: 1em;
  text-align: center;
  font-weight: bold;
  margin-bottom: .8em;
  background-color: white;
  padding: .3em;
  border: 2px solid rgb(11, 168, 241);
  border-radius: 8px;
}

#label {
  font-size: 0.9em;
  font-weight: bold;
  text-align: center;
  padding-right: .3em;
}
.prize-user-group{
  display:flex;
  padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.prize-maximum{
  display:flex;
  padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.prize-milestone {
  display:flex;
  padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.prize-start-date {
  display: flex;
  padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.prize-end-date {
   display: flex;
   padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.prize-completed {
  display: flex;
  padding: .3em;
  background-color: white;
  border: 1px solid grey;
  margin-bottom: .3em;
}

.progress-bar {
  width: 100%;
  background-color: darkgray;
  border: 1px solid grey;
  border-radius: 8px;
}

.progress-color {
  width: 0%; 
  background-color: rgb(67, 67, 231);
  color: white;
  border-radius: 8px;
}

.edit-button {
  margin: 1em;
  display: flex;
  justify-content: center;
}

</style>