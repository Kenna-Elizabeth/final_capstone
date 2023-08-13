import axios from 'axios';

export default {

  getSessions() {
    return axios.get('/sessions')
  }

}