<template>
  <main id="login">
    <div class="box">
      <h1>Family Reading Tracker</h1>
    </div>
    <form id="login-form" @submit.prevent="login">
      <h2>Please Log In</h2>
      <div class="alert-error" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="alert-success" role="alert" v-if="$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="text-input-block">
        <div class="form-input-group">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="user.password"
            required
          />
        </div>
      </div>
      <button type="submit">Sign in</button>
      <p>
        <router-link :to="{ name: 'register' }"
          >Need an account? Sign up.<br />(Kids, get your parents to set things
          up!)</router-link
        >
      </p>
    </form>
  </main>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            if (response.data.user.authorities[0].name == "ROLE_CHILD") {
              this.$router.push("/books");
            } else if (
              response.data.user.authorities[0].name == "ROLE_PARENT") {
              this.$store.dispatch("retrieveBooks");
              this.$router.push("/family");
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
#login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}
.alert-error {
  color: firebrick;
  margin-bottom: 1em;
}
.alert-success {
  color: navy;
  margin-bottom: 1em;
}
.form-input-group {
  margin-bottom: 1rem;
}
.text-input-block {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
label {
  margin-right: 0.5rem;
}
</style>