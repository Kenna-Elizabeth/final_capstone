import axios from 'axios';

export default {

  getSessions() {
    return axios.get('/sessions')
  },

  getSessionsByBookId(id) {
    return axios.get(`/sessions/${id}`)
  }
}