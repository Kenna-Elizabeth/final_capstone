<template>
  <form id="add-book-form" autocomplete="off" @submit.prevent="submitBook">
    <div class="alert-msg" role="alert" v-if="addBookErrors">
      {{ addBookErrorMsg }}
    </div>
    
    <div class="form-input-group">
      <label for="title">Book Title</label>
      <input type="text" id="title" v-model="book.title" :disabled="disableForm"/>
    </div>
    <div class="form-input-group">
      <label for="author">Author</label>
      <input type="text" id="author" v-model="book.author" :disabled="disableForm"/>
      </div>
    <div class="form-input-group">
      <label for="isbn">ISBN</label>
      <input type="text" id="isbn" v-model="book.isbn" :disabled="disableForm"/>
      </div>
    <button type="submit" :disabled="disableForm">Submit Book</button>
  </form>
</template>

<script>
import booksService from "../services/BooksService";

export default {
  data() {
    return {
      book: {
        title: "",
        author: "",
        isbn: "",
      },
      disableForm: false,
      addBookErrors: false,
      addBookErrorMsg: "",
    };
  },
  methods: {
    submitBook() {
      if (this.book.title === "") {
        this.addBookErrors = true;
        this.addBookErrorMsg = "Please enter book title.";
      } else {
        this.addBookErrorMsg = "";
        this.addBookErrors = false;
        this.disableForm = true;
        booksService.submitBook(this.book).then((response) => {
          if (response.status == 201) {
            //Clear form for next entry
            this.book = {
              title: "",
              author: "",
              isbn: "",
            };
            this.disableForm = false;
            this.$emit('create-book');
          }
        });
      }
    },
  },
};
</script>

<style scoped>
#add-book-form {
  margin-top: 0.5em;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-items: flex-end;
  font-weight: bold;
}
.form-input-group {
  margin: 0.3em;
  display: flex;
  align-items: center;
  margin-bottom: 1em;
  border: black 2px;

}
label {
  margin-right: 0.5em;
}

button {
  margin-top: 0.5em;
  margin-bottom: 0.5em;
  padding: 0.3em;
  padding-left: 0.6em;
  padding-right: 0.6em;
  border: 2px solid black;
  border-radius: 8px;
  background-color: #31b4c9;
  font-weight: bold;
  color: white;
}
button:hover {
  background-color: #1590a3;
}
</style>