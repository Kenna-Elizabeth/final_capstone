import axios from 'axios';

export default {

  getFamilyUsers() {
    return axios.get('/user/family/users')
  }

}
