<template>
  <main class="books">
    <div class="box">
      <h1>Books</h1>
    </div>
    <div>
      <button @click="showAddForm = !showAddForm">
        {{ showAddForm ? "Cancel" : "Add Book" }}
      </button>
    </div>
    <add-book-form v-if="showAddForm" />
    <section id="book-display">
      <div v-for="book in $store.state.books" :key="book.id">
        <router-link :to="{ name: 'book', params: { id: book.id } }">
          <div class="book-panel" :class="{ completed: book.completed }">
            <div class="book-title">
              {{ book.title }}
            </div>
            <div class="book-image">
              <img :src="book.coverUrl" />
            </div>
            <div class="author">
              {{ book.author }}
            </div>
            <div v-if="book.completed" class="completedText">
              ✔️ Completed!
            </div>
          </div>
        </router-link>
      </div>
    </section>
  </main>
</template>

<script>
import AddBookForm from "../components/AddBookForm.vue";

export default {
  name: "books",
  data() {
    return {
      showAddForm: false,
    };
  },

  components: {
    AddBookForm,
  },

  created() {
    this.$store.dispatch("retrieveBooks");
  },
};
</script>

<style scoped>
#book-display {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

.book-panel {
  border: 2px solid black;
  border-radius: 8px;
  margin: 1em;
  padding: 0.2em;
  text-align: center;
  background-color: #eef2f3;
  box-shadow: 2px 3px #c9c9c9;
  font-weight: bold;
  width: 14em;
  height: auto;
}

.book-panel:hover {
  transform: scale(1.05);
  box-shadow: 5px 7px #c9c9c9;
}

.completed {
  background-color: palegreen;
}

img {
  height: 18em;
}

.book-title {
  margin: 1em;
  height: 1.5em;
  font-size: 1em;
}

.author {
  font-size: 0.75em;
  margin: 0.5em;
}

a {
  text-decoration: none;
  color: black;
}
</style>