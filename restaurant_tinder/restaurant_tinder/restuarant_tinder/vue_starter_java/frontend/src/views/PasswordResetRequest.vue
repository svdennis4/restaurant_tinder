<template>
<body>
        <div id="password-request" class="text-center">
            <div class="password-page">
    
                <form class="form-email" @submit.prevent="register">
                    <h1 class="page-title">Forgot your password?</h1>
                    <h2 class="page-subtitle">Enter your email below to receive a reset code to your inbox.</h2>
                    <br>
                    <br>
                    <div class="field">
                    <input 
                    type="text"
                    id="username"
                    class="form-control username"
                    placeholder="Email"
                    required
                    autofocus
                    />
                    <br>
                    <br>
                    <button id="sub-button" class="button" type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div> 


</body>
</template>




<script>
export default {
  name: 'password-reset-request',
  components: {},
  data() {
    return {
        invalidRequest: false,
    };
  },
  methods: {
    sendResetRequest() {
      fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/password-reset-request`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        //body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidRequest = true;
          }
        })
        .then()
        .catch((err) => console.error(err));
    },
  },
};
</script>




<style>
@import url('https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@500&family=Enriqueta:wght@500&display=swap');
/* @import url(https://fonts.googleapis.com/css2?family=Enriqueta:wght@500&display=swap);
@import url(https://fonts.googleapis.com/css?family=Exo:100,200,400);
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css); */
/* @import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap'); */

:root {
  --theme-color: #7cfc00;
  --theme-font: 'Abhaya Libre', serif;
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
  border-color: var(--theme-color)
}
.password-page {
  width: 600px;
  padding: 10% 0 0;
  margin: auto;
}

.form-email {
  position: relative;
  z-index: 1;
  background: transparent;
  max-width: 600px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
}

.form-email input {
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
.header h1 span {
  color: var(--theme-color) !important;
  z-index: 200;
}

::-webkit-input-placeholder {
  color: white;
}

.page-title {
  color: white;
  font-family: var(--theme-font);
  font-size: 30px;
  font-weight: bold;
  padding-bottom: 5px;
}
.page-subtitle {
  color: white;
  font-family: var(--theme-font);
  font-size: 18px;
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
  color: var(--theme-color);
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
  outline-color: var(--theme-color)
}

</style>