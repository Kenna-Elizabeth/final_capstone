import axios from 'axios';

export default {

  getPrizes() {
    return axios.get('/prizes')
  },
  submitPrize(prize) {
    return axios.post('/prizes', prize)
  },

  deletePrize(prizeId) {
    return axios.delete(`/prizes/${prizeId}`)
  }

}