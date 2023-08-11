<template>
  <main class="books">
    <div class="box">
      <h1>Books</h1>
    </div>
      <section id="book-display">
        <div class="book-panel" v-for="book in $store.state.books" :key="book.id">
           <p>{{ book.title }}</p> 
           
            <img :src="book.coverUrl" />
            <p>Author: {{ book.author }}</p>
          
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
  border: 2px solid black;
  border-radius: 5px;
  margin: 0.5em;
  padding: 0.2em; 
  text-align: center;
  background-color: #b1f1fd;
  box-shadow: 5px 8px #c9c9c9;
}
#book-display {
  display: flex; 
  justify-content: space-evenly;
  flex-wrap: wrap; 
  margin: 10px; 

}

#book-display  p {
  font-weight: bold;
}


</style>