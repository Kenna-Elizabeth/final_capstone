import axios from 'axios';

export default {

  getFamilyUsers() {
    return axios.get('/books')
  }

}