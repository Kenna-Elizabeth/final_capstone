<template>
  <section class="session-display">
    <ul>
      <div v-for="session in sessions" :key="session.id">
        <li>
          {{ $store.getters.getBookById(session.bookId).title }}:
          {{ session.minutes }} Minutes
        </li>
      </div>
    </ul>
  </section>
</template>

<script>
export default {
  name: "sessionsList",
  props: ["sessions"],
};
</script>

<style scoped>
ul {
  display: inline-block;
  text-align: left;
  list-style-type: none;
  padding-inline-start: 0;
}

li {
  padding: 0.5em;
  margin: 0.5em;
  border: 1px solid black;
  background-color: #b9e9ff;
  border-radius: 0.3em;
}
</style>