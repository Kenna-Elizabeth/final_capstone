<template>
  <main class="activity">
    <div class="box">
     <h1>Activity</h1>
    </div>
    <ul>
    <section id ="session-display"> 
        <div v-for="session in  $store.state.sessions" :key="session.id"> 
            <li>
              {{ $store.getters.getBookById(session.bookId).title }}: {{ session.minutes }} Minutes
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
    this.$store.dispatch('retrieveBooks');
    this.$store.dispatch('retrieveSessions');
  }
};
</script>

<style scoped>
.session-display {
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