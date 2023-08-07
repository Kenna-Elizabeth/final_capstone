<template>
  <header>
    <h1>Logout</h1>
  </header>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
