import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import familyService from '../services/FamilyService'
import booksService from '../services/BooksService'
import activityService from '../services/ActivityService'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    familyUsers: [],
    books: [],
    activity: []

  },
  getters: {
    getBookById: (state) => (id) => {
      return state.books.find(book => book.id == id );
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.familyUsers = {};
      axios.defaults.headers.common = {};
    },
    SET_FAMILY_USERS(state, users) {
      state.familyUsers = users;
    },
    SET_BOOKS(state,books){
      state.books = books; 
    },
    SET_ACTIVITY(state,activity){
      state.activity = activity; 
    }
  },
  actions: {
    retrieveFamilyUsers(context) {
      familyService.getFamilyUsers().then( response => {
        if (response.status == 200) {
          context.commit("SET_FAMILY_USERS", response.data);
        }
      }).catch(error => {
        if (error.response) {
          this.errorMsg = "Could not load family members.";
        }
      });
    },
    retrieveBooks(context){
      booksService.getBooks().then( response => {
        if (response.status == 200){
          context.commit("SET_BOOKS", response.data);
        }
      }).catch(error => {
        if (error.response){
          this.errorMsg = "Could not load books.";
        }
      });
    },
    retrieveActivity(context){
      activityService.getActivity().then( response => {
        if(response.status == 200) {
          context.commit("SET_ACTIVITY", response.data); 
        }
      }).catch(error => {
        if(error.response){
          this.errorMsg = "Could not load activity."; 
        }
      });
    }
  }
})
