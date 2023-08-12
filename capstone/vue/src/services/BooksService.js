import axios from 'axios';

export default {

  getBooks() {
    return axios.get('/books')
  },
  submitBook(book) {
    return axios.post('/books', book)
  }

}