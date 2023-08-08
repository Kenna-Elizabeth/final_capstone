<template>
  <div id="register" class="text-center">
    <header>
      <h1>Create Account</h1>
    </header>
    <register-form :role="'Parent'" :familyId="0" :chooseRole="false" @create-user="onCreateUser()" />
    <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
  </div>
</template>

<script>
import RegisterForm from '../components/RegisterForm.vue';


export default {
  components: { 
    RegisterForm,
    },
  name: 'register',
  methods: {
    onCreateUser() {
      this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
    }
  }
};
</script>

<style scoped>

</style>
