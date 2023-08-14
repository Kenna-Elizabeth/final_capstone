import axios from 'axios';

export default {

  getBooks() {
    return axios.get('/books')
  },
  
  submitBook(book) {
    return axios.post('/books', book)
  },

  getBookById(id) {
    return axios.get(`/books/${id}`)
  },

  getRecommendedBook() {
    return axios.get('/books/recommended')
  },

  setBookCompletion(bookId, completed, userId) {
    return axios.patch(`/books/${bookId}?completed=${completed}&userId=${userId}`)
  }
}