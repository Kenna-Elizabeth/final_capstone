<template>
  <main class="activity">
    <div class="box">
     <h1>Activities</h1>
    </div>
    <section id ="activity-display"> 
        <div v-for="activity in  $store.state.activity" :key="activity.id"> 
            {{ getBookById(activity.bookId) }}
           
        </div>
    </section>
  </main>
</template>

<script>
export default {
  name: "activity",

  created() {
    this.$store.dispatch('retrieveActivity');
    this.$store.dispatch('retrieveBooks');
  },


  methods: {
    getBookById(){
      return this.$store.state.books[0];
      // .find(book => {
      //   return book.id == bookId;
      // });
    }
  }
};
</script>
