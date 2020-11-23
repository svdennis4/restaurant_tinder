<template>
<div>
<div id="logo" class="logo">
            <img src="https://i.ibb.co/5j0YdfQ/CapLogo.png" alt="CapstoneLogo" border="0">
          </div>
          <br>

        <div id="register-alert" class="notification is-success is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.registration">
            <button id="alert-delete" class="delete" @click="deleteAlert"></button>
            <center>Thank you for registering!</center>
        </div>

    <div>
        <h1 class="text-green theme-font">Please Select Your Preferences:</h1><br>
    <form id=checkboxes>
        <!-- Need error statment/method -->
        
        <div v-for="cuisine in cuisineList" v-bind:key="cuisine.id" id="form">
            
            <input  type="checkbox"
                    v-model="currentPreferences"
                    :value="cuisine.id"
                    :id="cuisine.id"
                    
                />
                
            <label>{{cuisine.name}}</label>
            
        </div><br>
        
    </form>
        <div id="sumbit" align="center">
        <button id="sub-button" class="button" type="submit" @click.prevent="setPreferences">Submit</button><br>
            <span class="theme-color theme-font"></span><br>
        </div>
    </div>
</div>
</template>

<script>
import auth from '../auth';

export default {
    data() {
        return{
            cuisineList: [],
            previousPreferences:[],
            currentPreferences:[]
            };
    },

    methods: {
         getPreferences() {
            fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/preferences`, {
                method: "GET",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            }
        })
        .then(response => {
          return response.json();
        })
        .then(response => {
            console.log(response);
          this.previousPreferences = response;
          this.currentPreferences = this.previousPreferences.preferences;
          this.checkPrevPrefBoxes();
          })
        .catch(err => console.error(err));

        },

        setPreferences() {
            fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/preferences`, {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            },
            body: JSON.stringify(this.currentPreferences),
            },
            console.log("Fetch reached.")
            )
            .then(response => {
                if(response.ok) {
                    console.log("Successful POST");
                    this.$router.push({
                        path: "/",
                        query: { preferenceUpdate: "success" }
                    });
                    console.log("Response Okay.")
                } else {
                    console.log("POST error")
                }
            })
            .then(err => console.error(err))
        },

        populateCuisines() {
            fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/cuisines`, {
                method: "GET",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            }
            })
            .then(response => {
                return response.json();
            })
            .then(response => {
                console.log(response);
                this.cuisineList = response;
            })
            .catch(err => console.error(err));
        },

        checkPrevPrefBoxes() {
            this.cuisineList.forEach(cuisine => {
                this.previousPreferences.preferences.forEach(preference => {
                    if(cuisine.id === preference) {
                        document.getElementById(cuisine.id).checked = true;
                    }
                })
            })
        },

        deleteAlert() {
            let element = document.getElementById("register-alert");
            element.parentNode.removeChild(element);
        }
    },
    mounted() {
        this.populateCuisines();
        this.getPreferences();
    }

};

</script>

<style scoped>
/* #form {
    color: green;
    
} */
.text-green{
    color: var(--theme-color);
    text-align: center;
    font-size: 25px;
}

:root {
  --theme-color: #7cfc00;
  --theme-font: 'Abhaya Libre', serif;
}

#quest-title{
    font-family: var(--theme-font);
    color: var(--theme-color);
    font-size: 20px;
}


#form {
    color: var(--theme-color);
    text-align: center;
    justify-content: center;
    font-family: var(--theme-font);

}
#form-div {
    justify-content: center;
    text-align: center;
      font-family: var(--theme-font);

}
#checkboxes{
    
    column-count: 3;   
    font-size: 20px;
    margin-left: 250px;
    margin-right: 250px;
    
}


#submit{
    text-align:center;
    justify-content: center;
    align-content: center;
    align-items: center;
    position:center;
  
}
.button{
    /* position: fixed; */
    bottom: 20px;
    top:20px;
    /* right: 720px;  
    left:720px; */
    align-items: center;
    text-align: center;
    align-content: center;
    justify-self: center;
    justify-content: center;
    justify-items: center;
    

}
#form{
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-gap: 10px;
    align-items: center;
   
    
}

input{
grid-column: 1;

justify-self: end;
}

label{

font-weight: 500px;
grid-column: 2;
justify-self: start;
}

.alert-transparent {
  background: white;
  opacity: 60%;
  color: black;
  font-size: 20px;
}


/* 
label {
  display:inline-block;
  border:solid 1px gray;
  line-height:40px;
  height:40px;
  width: 250px;
  border-radius: 5px;
  -webkit-font-smoothing: antialiased; 
  margin-top:10px;
  color:white;
  text-align:center;
  justify-content: center;
  font-family: var(--theme-font);
}

input[type=checkbox] {
  display: none;
}

input:checked + label {
  border: solid 1px red;
  color: #F00;
}

input:checked + label:before {
content: "✓ ";
}


/* new stuff */
/* .check {
  visibility: hidden;
}

input:checked + label .check {
  visibility: visible;
}

input.checkbox:checked + label:before {
content: "";
} */


</style>