import axios from 'axios';

export default {

  getBooks() {
    return axios.get('/books')
  },

  getBookById(id) {
    return axios.get(`/books/${id}`)
  },

  getRecommendedBook() {
    return axios.get('/books/recommended')
  }
}