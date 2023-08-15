<template>
  <div class="login-display">
    <span class="welcome-message">
      Welcome, {{ $store.state.user.username }}
    </span>
    <span class="switch-view-message" v-if="$store.state.user.authorities[0].name == 'ROLE_PARENT'">
      - View Information Of: 
      <select id="switch-view" @change.prevent="">
        <option v-for="user in $store.state.familyUsers" :key="user.username" :value="user.username">
          {{ user.username }}
        </option>
      </select>
    </span>
  </div>
</template>

<script>
export default {
  name: 'login-display'
}
</script>

<style scoped>

</style>