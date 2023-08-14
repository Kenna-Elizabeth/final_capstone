<template>
  <main class="activity">
    <div class="box">
      <h1>Activity</h1>
    </div>
    <h2>Books Completed: {{ completedCount }}</h2>
    <h2>Total Reading Minutes: {{ minutesReading }}</h2>
    <sessions-list :sessions="$store.state.sessions" />
  </main>
</template>

<script>
import SessionsList from "../components/SessionsList.vue";

export default {
  components: { SessionsList },
  name: "activity",
  computed: {
    completedCount() {
      return this.$store.state.books.reduce((completions, book) => {
        return book.completed ? completions + 1 : completions;
      }, 0);
    },
    minutesReading() {
      return this.$store.state.sessions.reduce((total, session) => {
        return total + session.minutes;
      }, 0);
    },
  },

  created() {
    this.$store.dispatch("retrieveBooks");
    this.$store.dispatch("retrieveSessions");
  },
};
</script>

<style scoped>
</style>