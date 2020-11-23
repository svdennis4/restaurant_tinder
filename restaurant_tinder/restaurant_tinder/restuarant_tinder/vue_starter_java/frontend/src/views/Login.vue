<template>

      <div id="register" class="text-center">
          <div id="logo" class="logo">
            <img src="https://i.ibb.co/5j0YdfQ/CapLogo.png" alt="CapstoneLogo" border="0">
          </div>
        <div id="register" class="register-page">
          <form class="form-register" @submit.prevent="login">
            <h1 class="create-acct">Please Sign In</h1>
            <div class="alert alert-danger theme-color theme-font" role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div class="field">
            <label for="username" class="sr-only">Username</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
            </div>
            <div class="field">
            <label for="password" class="sr-only">Password</label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
            </div>
            
            <button id="sub-button" class="button" type="submit">Sign in</button>
            <br>
            <router-link :to="{ name: 'register' }" id="have-account" class="have-act">Need an account?</router-link>
            <!-- <br>
            <router-link :to="{ name: 'password-reset-request' }" id="have-account" class="have-act">Forgot Password?</router-link> -->
          </form>
        </div>
      </div>

</template>

<script>
import auth from '../auth';


export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style>
@import url(https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@500&family=Enriqueta:wght@500&display=swap);
/* @import url(https://fonts.googleapis.com/css2?family=Enriqueta:wght@500&display=swap);
@import url(https://fonts.googleapis.com/css?family=Exo:100,200,400);
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css); */
/* @import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap'); */

:root {
  --theme-color: #7cfc00;
  --theme-font: 'Abhaya Libre', serif;
}

.theme-color{
  color: var(--theme-color);
}
.theme-font{
  font-family: var(--theme-font);
}

html{
  width: 100%;
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow-x: hidden
}

html, body {
  width: 100%;
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow-x: hidden
}

body {
  /* 
  Original Image:
  background-image: url(https://i.ibb.co/1bfc5MR/output-onlinejpgtools.jpg);
  New Image Blurry:        
  background-image: url(https://i.ibb.co/PDCrKN4/output-onlinejpgtools.jpg);
  /* background-image: url(https://i.ibb.co/ZR9Y0s7/img-3.jpg); */
  /* background-image: url(https://i.ibb.co/8rrW0S8/Italian-Background.jpg); */
  /* background-image: url(https://i.ibb.co/ZR9Y0s7/img-3.jpg); */

  /* background-image: url('https://i.ibb.co/zJRkBfL/italian-food-background-1.jpg'); */

  background-image: url('https://i.ibb.co/TDXZm4N/italian-food-background-1-shaded.jpg');
  background-position: center center;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
  margin: 0;
  padding: 0;

}

.text-box {
  border-color: #7cfc00
}


.register-page {
  width: 360px;
  padding: 10% 0 0;
  margin: auto;
  background: transparent;

}

.form-register {
  position: relative;
  z-index: 1;
  background: transparent;
  max-width: 360px;
  margin: 0 auto 100px;
  text-align: center;
}

.form-register input {
  width: 250px;
  height: 30px;
  background: transparent;
  border: 1px solid white;
  border-radius: 2px;
  color: #fff;
  font-family: var(--theme-font);
  font-size: 16px;
  font-weight: 400;
  padding: 4px;
}

.header {
  z-index: 200;
}

#logo {
  text-align: center;
  width: 100%; 
  height: 100%; 
  object-fit: contain;
  display: block;
  max-width: 100%;
  height: auto;
  padding-top: 125px;
}

.header h1 span {
  color: #7cfc00 !important;
  z-index: 200;
}

::-webkit-input-placeholder {
  color: white;
}

.create-acct {
  color: white;
  font-family: var(--theme-font);
  font-size: 25px;
  font-weight: bold;
  padding-bottom: 5px;

}
.sr-only {
  color: white;
  font-family: var(--theme-font);
}

.create-acct-btn{
  font-family: var(--theme-font);
}

.have-act {
  color:#7cfc00;
  }

#sub-button {
  align-content: center;
  font-family: var(--theme-font);

}
#have-account {
  align-content: center;
  font-family: var(--theme-font);

}

input:focus{
  outline-color: #7cfc00
}
  
</style>