<template>
  <main class="family">
    <div class="box">
    <h1>Family Management Page</h1>
    </div>
    <h3>Family Members</h3>
    <ul>
      <li v-for="user in this.$store.state.familyUsers" :key="user.username">
        {{ user.username }} - {{ user.authorities[0].name == "ROLE_PARENT" ? 'Parent' : 'Child' }}
      </li>
    </ul>
    <div>
      <button>Add Family Member</button>
    </div>
    <register-form 
      :role="'Child'" 
      :familyId="this.$store.state.user.familyId == 0 ? this.$store.state.user.id : this.$store.state.user.familyId" 
      :chooseRole="true"
      @create-user="$store.dispatch('retrieveFamilyUsers')" 
    />
  </main>
</template>

<script>
import RegisterForm from '../components/RegisterForm.vue';

export default {
  components: { 
    RegisterForm
  },
  name: "family",
  data() {
    return {
      showAddForm: false,
    };
  },
  created() {
    this.$store.dispatch('retrieveFamilyUsers');
  }
};
</script>

<style scoped>
ul {
  display: inline-block;
  text-align: left;
  list-style-type: none;
  padding-inline-start: 0;
}

</style>