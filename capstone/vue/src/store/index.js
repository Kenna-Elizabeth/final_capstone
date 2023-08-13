import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import familyService from '../services/FamilyService'
import booksService from '../services/BooksService'
import sessionsService from '../services/SessionsService'

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
    sessions: []

  },
  getters: {
    getBookById: (state) => (id) => {
      const book = state.books.find(book => book.id == id );
      if (book == undefined) {
        return { id: 0, familyId: 0, title: '', author: '', isbn: '', coverUrl: '', note: '', 
          completed: false, recommended: false, lastRead: null };
      }
      return book;
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
      state.familyUsers = [];
      state.books = [];
      state.sessions = [];
      axios.defaults.headers.common = {};
    },
    SET_FAMILY_USERS(state, users) {
      state.familyUsers = users;
    },
    SET_BOOKS(state, books){
      state.books = books; 
    },
    SET_SESSIONS(state, sessions){
      state.sessions = sessions; 
    },
    /*--- completed button ---*/
    SET_COMPLETED_STATUS(state, payload) {
      payload.books.completed = payload.value;
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
    retrieveSessions(context){
      sessionsService.getSessions().then( response => {
        if(response.status == 200) {
          context.commit("SET_SESSIONS", response.data); 
        }
      }).catch(error => {
        if(error.response){
          this.errorMsg = "Could not load sessions."; 
        }
      });
    }
  }
})
