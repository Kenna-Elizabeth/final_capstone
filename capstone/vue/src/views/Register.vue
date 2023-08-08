<template>
  <main id="register" class="text-center">
    <div class="box">
      <h1>Create Account</h1>
    </div>
    <register-form :role="'Parent'" :familyId="0" :chooseRole="false" @create-user="onCreateUser()" />
    <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
  </main>
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
