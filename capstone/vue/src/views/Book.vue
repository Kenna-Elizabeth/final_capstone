<template>
  <main class="book">
    <div class="box">
      <h1>{{ book.title }}</h1>
    </div>
    <p>By {{ book.author }}</p>
    <img :src="book.coverUrl" alt="Book Cover Art" />
    <p>Reading session form goes here!</p>
    <sessions-list :sessions="bookSessions" />
    <router-link :to="{ name: 'books' }">
      <p>Back to Book Collection</p>
    </router-link>
  </main>
</template>

<script>
import booksService from "../services/BooksService";
import sessionsService from "../services/SessionsService";
import sessionsList from "../components/SessionsList.vue";

export default {
  components: {
    sessionsList
  },
  name: "book",
  data() {
    return {
      book: {},
      bookSessions: [],
    };
  },
  created() {
    if (this.$route.params.id == undefined) {
      booksService
        .getRecommendedBook()
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
            this.getSessionsForBook();
          } else if (response.status == 204) {
            this.$router.push("/books");
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push("/books");
          }
        });
    } else {
      booksService
        .getBookById(this.$route.params.id)
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
            this.getSessionsForBook();
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push("/books");
          }
        });
    }
  },
  methods: {
    getSessionsForBook() {
      sessionsService
        .getSessionsByBookId(this.book.id)
        .then((response) => {
          if (response.status == 200) {
            this.bookSessions = response.data;
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load book reading sessions.";
          }
        });
    },
  },
};
</script>