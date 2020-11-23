<template>
  <div id="app">
    <div id="nav">
      <nav class="navbar nav-transparent" role="navigation" aria-label="main navigation">
        <div class="navbar-start">
          <a class="navbar-item">
            <router-link to="/" class="navbar-font">Home</router-link>
          </a>
        </div> 
        <a class="navbar-item navbar-center">
            <router-link to="/preferences" id="nav-pref" class="navbar-font navbar-middle">Preferences</router-link>
          </a>
        <a class="navbar-item navbar-center">
            <router-link to="/favorites" id="nav-fav" class="navbar-font navbar-middle">Favorites</router-link>
        </a>
        <div class="navbar-end">
          <a class="navbar-item">
            <p type="submit" class="navbar-font" @click.prevent="logoff">Logout</p>
          </a>
        </div>
      </nav>
    </div>
    <router-view/>

  </div>
</template>

<script>
import Auth from './auth'
export default {
  name: 'home',

methods: {
    logoff() {
      Auth.logout();
      console.log("logged out");
      this.$router.push('/login');
    }
}
};
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@500&family=Enriqueta:wght@500&display=swap');
/* @import url(https://fonts.googleapis.com/css2?family=Enriqueta:wght@500&display=swap);
@import url(https://fonts.googleapis.com/css?family=Exo:100,200,400);
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css); */

:root {
  --theme-color: #7cfc00;
  --theme-font: "Abhaya Libre", serif;

}

.nav-transparent {
  background: white;
  opacity: 40%;
  color: var(--theme-color);
  position: fixed;
}

.navbar-font {
  color:black;
  font-weight: bold;
  font-size: 20px;
  font-family: var(--theme-font);
}


</style>

