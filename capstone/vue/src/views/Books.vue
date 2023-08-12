<template>
  <main class="books">
    <div class="box">
      <h1>Books</h1>
    </div>
      <section id="book-display">
        <div class="book-panel" v-for="book in $store.state.books" :key="book.id">
            <p>{{ book.title }}</p> 
            <div id="book-image">
              <img :src="book.coverUrl" />
            </div>
            <div id="author">
             <p>Author: {{ book.author }}</p> 
            </div>
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
  
}

.book-panel:hover{
  transform: scale(1.05);
  box-shadow: 5px 7px #c9c9c9;
}

#book-display  p {
  font-weight: bold;
}

img {
  height: 20em;
}

#author {
  font-size: 1em;
}


</style>