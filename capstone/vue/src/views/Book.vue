<template>
  <main class="book">
    <div class="box">
      <h1>{{ book.title }}</h1>
    </div>
    <p>By {{ book.author }}</p>
    <img :src="book.coverUrl" alt="Book Cover Art">
    <p>Reading session form goes here!</p>
    <p>Past sessions go here!</p>
    <router-link :to="{ name: 'books' }">
      <p>Back to Book Collection</p>
    </router-link>
  </main>
</template>

<script>
import booksService from "../services/BooksService";

export default {
  name: "book",
  data() {
    return {
      book: {},
    };
  },
  created() {
    if (this.$route.params.id == undefined) {
      booksService
        .getRecommendedBook()
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
          } else if (response.status == 204) {
            this.$router.push('/books');
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push('/books');
          }
        });
    } else {
      booksService
        .getBookById(this.$route.params.id)
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push('/books');
          }
        });
    }
  },
};
</script>