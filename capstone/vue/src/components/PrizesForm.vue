<template>
  <main class="prizes-form">
    
     <div>
        <button @click="showAddForm = !showAddForm">
        {{ showAddForm ? 'Cancel' : 'Add Prize' }} 

      </button>
    </div>
    <section id="prize-display">
      <div class="prize-panel">
        <div class="prize-name">Prize Name</div>
        <div class="prize-description">Description</div>
        <div class="prize-milestone">Total Minutes Reading for Prize</div>
        <div class="prize-user-group">Prize User Group</div>
        <div class="prize-maximum">Maximum Number of Prize</div>
        <div class="prize-start-date">Prize Available Start Date</div>
        <div class="prize-end-date">Prize Available End Date</div>
        <div class="prize-completed">Prize Completed</div>
      </div>
    </section>
  </main>
</template>

<script>
export default {
     data() {
    return {
      showAddForm: false,
    };
  },
    name: "prizes-form"
    

}
</script>

<style>

</style>