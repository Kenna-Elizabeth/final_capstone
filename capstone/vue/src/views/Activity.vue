<template>
  <main class="activity">
    <div class="box">
     <h1>Activity</h1>
    </div>
    <sessions-list :sessions="$store.state.sessions" />
  </main>
</template>

<script>
import SessionsList from '../components/SessionsList.vue';

export default {
  components: { SessionsList },
  name: "activity",

  created() {
    this.$store.dispatch('retrieveBooks');
    this.$store.dispatch('retrieveSessions');
  }
};
</script>

<style scoped>
</style>