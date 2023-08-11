import axios from 'axios';

export default {

  getActivity() {
    return axios.get('/sessions')
  }

}