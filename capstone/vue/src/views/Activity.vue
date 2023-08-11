<template>
  <main class="activity">
    <div class="box">
     <h1>Activities</h1>
    </div>
    <section id ="activity-display"> 
        <div v-for="activity in  $store.state.activity" :key="activity.id"> 
            {{ $store.getters.getBookById(activity.bookId).title }}: {{ activity.minutes }} Minutes
        </div>
    </section>
  </main>
</template>

<script>
export default {
  name: "activity",

  created() {
    this.$store.dispatch('retrieveActivity');
    this.$store.dispatch('retrieveBooks');
  }
};
</script>
