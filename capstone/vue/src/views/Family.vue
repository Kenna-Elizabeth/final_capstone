<template>
  <div class="family">
    <header>
    <h1>Family Management Page</h1>
    </header>
    <h3>Family Members</h3>
    <ul>
      <li v-for="user in this.$store.state.familyUsers" :key="user.username">
        {{ user.username }} - {{ user.authorities[0].name == "ROLE_PARENT" ? 'Parent' : 'Child' }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "family",
  created() {
    this.$store.dispatch('retrieveFamilyUsers');
  }
};
</script>

<style scoped>
ul {
  display: inline-block;
  text-align: left;
}

</style>