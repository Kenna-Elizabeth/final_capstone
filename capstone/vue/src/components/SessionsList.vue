<template>
  <section class="session-display">
    <ul>
      <div v-for="session in sessions" :key="session.id">
        <li class="session-entry">
          <div class="first-row">
            📅{{ timestampFormat(session.startDateTime) }}
            ⏱️{{ session.minutes }} Minutes
          </div>
          <div class="second-row">
            📖{{ $store.getters.getBookById(session.bookId).title }}:
            {{ session.format }}
          </div>
        </li>
      </div>
    </ul>
  </section>
</template>

<script>
export default {
  name: "sessionsList",
  props: ["sessions"],
  methods: {
    timestampFormat(dateTime) {
      const parts = dateTime.split(/[T .]/);
      return parts[0] + " " + parts[1];
    },
  },
};
</script>

<style scoped>
.session-entry {
  display: flex;
  flex-wrap: wrap;
  gap: .4em 4em;
}

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