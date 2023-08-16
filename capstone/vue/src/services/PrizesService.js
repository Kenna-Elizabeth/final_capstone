import axios from 'axios';

export default {

  getPrizes() {
    return axios.get('/prizes')
  },
  submitPrize(prize) {
    return axios.post('/prizes', prize)
  },

  updatePrize(prize) {
    return axios.put('/prizes', prize)
  },

  deletePrize(prizeId) {
    return axios.delete(`/prizes/${prizeId}`)
  }

}