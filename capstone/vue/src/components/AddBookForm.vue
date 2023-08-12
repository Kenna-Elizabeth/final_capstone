<template>
  <form id="add-book-form" autocomplete="off" @submit.prevent="submitBook">
    <div class="alert-msg" role="alert" v-if="addBookErrors">
      {{ addBookErrorMsg }}
    </div>
    <div class="form-input-group">
      <label for="title">Book Title</label>
      <input type="title" id="title" v-model="title" :disabled="disableForm" />
    </div>
    <div class="form-input-group">
      <label for="author">Author</label>
      <input
        type="author"
        id="author"
        v-model="author"
        :disabled="disableForm"
      />
    </div>
    <div class="form-input-group">
      <label for="isbn">ISBN</label>
      <input type="isbn" id="isbn" v-model="isbn" :disabled="disableForm" />
    </div>
    <button type="submit" :disabled="disableForm">Submit Book</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      title: "",
      author: "",
      isbn: "",
      disableForm: false,
      addBookErrors: false,
      registrationErrorMsg: "",
    };
  },
  methods: {
    submitBook() {
      console.log("Hello");
    },
  },
};
</script>

<style>
</style>