<template>
  <main class="activity">
    <div class="box">
     <h1>Activities</h1>
    </div>
    <ul>
    <section id ="activity-display"> 
        <div v-for="activity in  $store.state.activity" :key="activity.id"> 
            <li>{{ $store.getters.getBookById(activity.bookId).title }}: {{ activity.minutes }} Minutes
            </li>
        </div>
    </section>
    </ul>
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

<style scoped>
.activity-display {
  display: flex;
  flex-wrap: wrap;
}
ul {
  display: inline-block; 
   text-align: left;
  list-style-type: none;
  padding-inline-start: 0;
}

li {
  padding: 0.5em;
  margin: .5em;
  border: 1px solid black;
  background-color: #b9e9ff;
  border-radius: 0.3em;
}
</style>