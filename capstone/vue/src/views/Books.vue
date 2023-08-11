<template>
  <main class="books">
    <div class="box">
      <h1>Books</h1>
    </div>
      <section id="book-display">
        <div class="book-panel" v-for="book in $store.state.books" :key="book.id">
            {{ book.title }}
            <img :src="book.coverUrl" />
        </div>
      </section>
  </main>
</template>

<script>
export default {
  name: "books",

  created() {
    this.$store.dispatch('retrieveBooks');
  }
};
</script>

<style scoped>
.book-panel {
  border: 1px solid black;
}
</style>