<template>
  <form id="register-form" @submit.prevent="register">
    <div class="form-input-group">
      New {{ user.role }} User
      <br>Family Id {{ user.familyId }}
    </div>
    <div role="alert" v-if="registrationErrors">
      {{ registrationErrorMsg }}
    </div>
    <div class="form-input-group" v-if="chooseRole">
      <input type="radio" id="child-radio" value="Child" v-model="user.role">
      <label for="child-radio">Child</label>
      <input type="radio" id="parent-radio" value="Parent" v-model="user.role">
      <label for="parent-radio">Parent</label>
    </div>
    <div class="form-input-group">
      <label for="username">Username</label>
      <input type="text" id="username" v-model="user.username" required autofocus />
    </div>
    <div class="form-input-group">
      <label for="password">Password</label>
      <input type="password" id="password" v-model="user.password" required />
    </div>
    <div class="form-input-group">
      <label for="confirmPassword">Confirm Password</label>
      <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
    </div>
    <button type="submit">Create Account</button>
  </form>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register-form',
  props: ['role', 'familyId', 'chooseRole'],
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: this.role,
        familyId: this.familyId
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$emit('create-user');
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};

</script>

<style>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>