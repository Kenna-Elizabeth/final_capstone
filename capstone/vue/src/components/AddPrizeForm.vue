<template>
  <form id="add-prize-form" autocomplete="off" @submit.prevent="submitPrize">
    <div class="alert-msg" role="alert" v-if="addPrizeErrors">
      {{ addPrizeErrorMsg }}
    </div>
    
    <div class="form-input-group">
      <label for="name">Prize Name</label>
      <input type="text" id="name" v-model="prize.prizeName" :disabled="disableForm"/>
    </div>
    <div class="form-input-group">
      <label for="description">Description</label>
      <input type="text" id="description" v-model="prize.description" :disabled="disableForm"/>
      </div>
    <div class="form-input-group">
      <label for="forChildren">For Children</label>
      <input type="checkbox" id="for-children" v-model="prize.forChildren" :disabled="disableForm"/>
      </div>
      <div class="form-input-group">
      <label for="forParents">For Parents</label>
      <input type="checkbox" id="for-parents" v-model="prize.forParents" :disabled="disableForm"/>
      </div>
       <div class="form-input-group">
      <label for="milestone">Milestone</label>
      <input type="number" id="milestone" v-model="prize.milestone" :disabled="disableForm"/>
      </div>
       <div class="form-input-group">
      <label for="startDate">Start Date</label>
      <input type="date" id="start-date" v-model="prize.startDate" :disabled="disableForm"/>
      </div>
       <div class="form-input-group">
      <label for="endDate">End Date</label>
      <input type="date" id="end-date" v-model="prize.endDate" :disabled="disableForm"/>
      </div>
    <button type="submit" :disabled="disableForm">Submit Prize</button>
  </form>
</template>

<script>
import prizesService from "../services/PrizesService";

export default {
  data() {
    return {
      prize: {
        
      },
      disableForm: false,
      addPrizeErrors: false,
      addPrizeErrorMsg: "",
    };
  },
  methods: {
    submitPrize() {
      if (this.prize.prizeName === "") {
        this.addPrizeErrors = true;
        this.addPrizeErrorMsg = "Please enter prize name.";
      } else {
        this.addPrizeErrorMsg = "";
        this.addPrizeErrors = false;
        this.disableForm = true;
        prizesService.submitPrize(this.prize).then((response) => {
          if (response.status == 201) {
            //Clear form for next entry
            this.prize = {
            
            };
            this.disableForm = false;
            this.$emit('create-prize');
          }
        });
      }
    },
  },
};
</script>

<style scoped>
#add-prize-form {
  margin-top: 0.5em;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-items: flex-end;
  font-weight: bold;
}
.form-input-group {
  margin: 0.3em;
  display: flex;
  align-items: center;
  margin-bottom: 1em;
  border: black 2px;

}
label {
  margin-right: 0.5em;
}
</style>