<template>
  <main class="books">
    <div class="box">
      <h1>Books</h1>
    </div>
    <div>
      <button @click="showAddForm = !showAddForm">
        {{ showAddForm ? 'Cancel' : 'Add Book' }}
      </button>
    </div>
    <add-book-form v-if="showAddForm" 
    @create-book="$store.dispatch('retrieveBooks')" 
    />
    <section id="book-display">
      <div v-for="book in $store.state.books" :key="book.id">
        <router-link :to="{ name: 'book', params: { id: book.id } }">
          <div class="book-panel" :class="{ completed: book.completed, 'in-progress': book.lastRead != null }">
            <div class="book-title">
              {{ book.title }}
            </div>
            <div class="book-image">
              <img :src="book.coverUrl" />
            </div>
            <div class="author">
              {{ book.author }}
            </div>
            <!-- testing --> 
            <div class="button-container"> 
              <button class="mark-complete" v-on:click.prevent="setCompleted(true)" v-if=" ! book.completed"> Mark Complete </button>
              <button class="mark-incomplete" v-on:click.prevent="setCompleted(false)" v-if="book.completed">Mark Incomplete</button>
            </div>
            <div v-if="book.completed" class="completed-text">
              ✔️ Completed!
            </div>
            <div v-if="book.lastRead != null" class="in-progress-text">
              Last Read: {{ timestampDate(book.lastRead) }}
            </div>
          </div>
        </router-link>
      </div>
    </section>
  </main>
</template>

<script>
import AddBookForm from '../components/AddBookForm.vue';

export default {
  name: "books",
  data() {
    return {
      showAddForm: false,
    };
  },
  methods: {
    timestampDate( timestamp ) {
      const parts = timestamp.split(/[T .]/);
      return parts[0];
    },
    /* ------- */
    setCompleted(value) {
    this.$store.commit('SET_COMPLETED_STATUS' , { book: this.book, value: value});
    },
  },
 
  components: {
    AddBookForm,
  },

  created() {
    this.$store.dispatch('retrieveBooks');
  },
};
</script>

<style scoped>
#book-display {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
button {
  margin-top: 1em;
  padding: 0.6em;
  padding-left: 1.5em;
  padding-right: 1.5em;
  border: 2px solid black;
  border-radius: 8px;
  background-color: #8be1f5;
  font-weight: 800;
}
button:hover {
  background-color: #44b8ca;
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

.in-progress {
  background-color: paleturquoise;
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