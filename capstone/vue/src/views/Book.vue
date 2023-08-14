<template>
  <main class="book">
    <div class="box">
      <h1>{{ book.title }}</h1>
    </div>
    <p>By {{ book.author }}</p>
    <img :src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" alt="Book Cover Art" />

    <div v-if="book.completed" class="completed-text">✔️ Completed!</div>

    <div class="button-container">
      <button
        class="mark-complete"
        v-on:click.prevent="setCompleted(true)"
        v-if="!book.completed"
      >
        Mark Complete
      </button>
      <button
        class="mark-incomplete"
        v-on:click.prevent="setCompleted(false)"
        v-if="book.completed"
      >
        Mark Incomplete
      </button>
    </div>

    <p>Record a Reading Session:</p>
    <form id="session-form" @submit.prevent="submitSession()">
      <div class="alert-msg" role="alert" v-if="formErrors">
        {{ formErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="minutes">Minutes</label>
        <input
          type="number"
          id="minutes"
          v-model="newSession.minutes"
          min="1"
          step="1"
          required
        />
        <label for="format">Format</label>
        <select id="format" v-model="newSession.format">
          <option value="Paper">Paper</option>
          <option value="Digital">Digital</option>
          <option value="Audiobook">Audiobook</option>
          <option value="Read-aloud (Reader)">Read-aloud (Reader)</option>
          <option value="Read-aloud (Listener)">Read-aloud (Listener)</option>
          <option value="Other">Other</option>
        </select>
      </div>
      <div class="form-input-group">
        <label for="startDateTime">Date (Optional)</label>
        <input
          type="datetime-local"
          id="startDateTime"
          v-model="newSession.startDateTime"
        />
        <label for="note">Note</label>
        <input
          type="text"
          id="note"
          size="40"
          placeholder="Enter an optional note here"
          v-model="newSession.note"
        />
      </div>
      <div class="form-input-group">
        <input type="submit" value="Submit" />
      </div>
    </form>

    <sessions-list :sessions="bookSessions" />

    <router-link :to="{ name: 'books' }">
      <p>Back to Book Collection</p>
    </router-link>
  </main>
</template>

<script>
import booksService from "../services/BooksService";
import sessionsService from "../services/SessionsService";
import sessionsList from "../components/SessionsList.vue";

export default {
  components: {
    sessionsList,
  },
  name: "book",
  data() {
    return {
      book: {},
      bookSessions: [],
      newSession: {
        userId: this.$store.state.user.id,
        bookId: undefined,
        minutes: 30,
        format: "Paper",
        startDateTime: undefined,
        note: "",
      },
      formErrors: false,
      formErrorMsg: "There were problems submitting this session.",
    };
  },
  created() {
    if (this.$route.params.id == undefined) {
      booksService
        .getRecommendedBook()
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
            this.newSession.bookId = this.book.id;
            this.getSessionsForBook();
          } else if (response.status == 204) {
            this.$router.push("/books");
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push("/books");
          }
        });
    } else {
      booksService
        .getBookById(this.$route.params.id)
        .then((response) => {
          if (response.status == 200) {
            this.book = response.data;
            this.newSession.bookId = this.book.id;
            this.getSessionsForBook();
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load recommended book.";
            this.$router.push("/books");
          }
        });
    }
  },
  methods: {
    getSessionsForBook() {
      sessionsService
        .getSessionsByBookId(this.book.id)
        .then((response) => {
          if (response.status == 200) {
            this.bookSessions = response.data;
            if (this.bookSessions.length > 0) {
              this.newSession.format = this.bookSessions[0].format;
            }
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Could not load book reading sessions.";
          }
        });
    },
    submitSession() {
      if (
        this.newSession.userId == undefined ||
        this.newSession.bookId == undefined
      ) {
        this.formErrors = true;
        this.formErrorMsg =
          "Error submitting reading session for user or book.";
      } else {
        if (this.newSession.startDateTime == "") {
          this.newSession.startDateTime = undefined;
        }
        sessionsService
          .addSession(this.newSession)
          .then((response) => {
            if (response.status == 201) {
              this.getSessionsForBook();
            }
          })
          .catch((error) => {
            if (error.response) {
              this.formErrors = true;
              this.formErrorMsg = "Error submitting reading session.";
            }
          });
      }
    },
    setCompleted(completion) {
      booksService
        .setBookCompletion(this.book.id, completion, this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            booksService
              .getBookById(this.book.id)
              .then((response) => {
                if (response.status == 200) {
                  this.book = response.data;
                }
              })
              .catch((error) => {
                if (error.response) {
                  this.errorMsg = "Error reloading book data.";
                }
              });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg = "Error setting book completion.";
          }
        });
    },
  },
};
</script>