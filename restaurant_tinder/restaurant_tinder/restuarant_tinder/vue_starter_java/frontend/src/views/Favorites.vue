<template>


<div id="favorites">
        <div id="logo" class="logo">
            <img src="https://i.ibb.co/5j0YdfQ/CapLogo.png" alt="CapstoneLogo" border="0">
        </div>
        <br>
    <section>
        <div id="fav-title">
        My Favorite Restaurants:
       
        <br>
        <br>
        <ul>
        
         <li id="fav-list" v-for="restaurant in favArray" :key="restaurant.id">
          {{ restaurant.name }} <br>
          <!-- {{ restaurant.cuisines }} <br> -->
          {{ restaurant.phone_numbers }} <br>
          <!-- {{ restaurant.timings}} <br>-->
          </li>
          
        </ul>
            <!-- <div id="fav-btn">
                <i></i>
                <span>liked!</span>
            </div> -->
          </div>
        
    </section>



</div>
</template>


<script>
import auth from '../auth';

export default {
  name: 'favorites',
   components: {
    }, 
   data() {
     return {
       favRestaurants:[],
       favArray:[]
     };
   }, 

   methods: {
     getFavoriteRestaurants(){
     fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/favourites`, {
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
   .then(results => {
          this.favRestaurants = results;
          console.log(results);
          
          console.log(this.favRestaurants);
          console.log(this.favRestaurants[0].restaurantId);
        this.getFavArray();
        })
        .catch(err => console.error(err));
      },

    sendFavApiCall(favString) {
      console.log("garbage code");
       
        const apiQuery = 'https://developers.zomato.com/api/v2.1/restaurant?apikey=' + process.env.VUE_APP_ZOMATO_API_KEY + '&res_id=' +favString;
        console.log("API" + apiQuery);
        fetch(apiQuery, {
          method: "GET",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json" 
          }
        })
        .then(response => {
          return response.json();
        })
        .then(results => {
          this.favRestaurant = results;
          console.log(this.favRestaurant);
         
          this.favArray.push(this.favRestaurant);
         
          console.log(this.favRestaurant)
        })
        .catch(err => console.error(err));
      },

    getFavArray(){
      console.log("anyting, please");
     
        this.favRestaurants.forEach ((fave) =>{
           this.sendFavApiCall(fave.restaurantId);
        })

       }

    },

  

  mounted () {
    this.getFavoriteRestaurants();
    
    // console.log(this.favArray);
    // console.log(this.favRestaurants);
    console.log("loaded again");
    
  }

  }


</script>



<style lang="postcss" scoped>

@import url(https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@500&family=Enriqueta:wght@500&display=swap);
@import url("https://www.w3schools.com/w3css/4/w3.css");

/* @import url(https://fonts.googleapis.com/css2?family=Enriqueta:wght@500&display=swap);
@import url(https://fonts.googleapis.com/css?family=Exo:100,200,400);
@import url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300);
@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css);
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap'); */

:root {
  --theme-color: #7cfc00;
  --theme-font: 'Abhaya Libre', serif;

}
.outline {
  border-color: var(--theme-color);

}
#fav-title{
    font-family: var(--theme-font);
    color: var(--theme-color);
    font-size: 25px;
    justify-content: center;
    text-align: center;

}

.row{
    width:90%;
    margin: 0 auto;
    padding: 30px 0;
    display: flex;
    fiex-flow: row nowrap;
    box-sizing: border-box;
    /* background-color: rgba(255, 255, 255, 0.5); */
    box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba (0,0,0,0.22);
    

}

#fav-list{
    background-color: rgba(255,255,255,.25);
    width: 50%;
    margin:auto;
    display: center;
    margin-top:10px;
}

#row-content{
    padding-left: 28px;
    font-family: var(--theme-font)

}

.theme-font {
    font-family: var(--theme-font);

}


#hoodSelect {
  font-family: var(--theme-font);
  /* background: white; */
  justify-content: center;
  align-content: center;
  background: transparent;
  color: var(--theme-color);
  text-align: center;
  font-size: 20px;

}
#hoodChoice {
  padding-bottom: 10px;

}

#restaurant-name {
  align-content: center;
  text-align: center;
  justify-content: center;
}

html{
  width: 100%;
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow-x: hidden;
}

html, body {
  width: 100%;
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow-x: hidden;
}
submit{
  padding-top: 25px;
}

.body{
    margin:0;
    text-align:center;
    padding-top:120px;
    font-family: var(--theme-font);
    background: #ddd;
}

#fav-btn {
  height:100px;
  margin:0 auto;
  position: relative;
}

i {
  cursor:pointer;
  padding:10px 12px 8px;
  /* background:#fff; */
  border-radius:50%;
  display:inline-block;
  margin:0 0 15px;
  /* color:#aaa; */
  transition:.2s;
}

i:hover {
  color:#666;
}

i:before {
  font-family:fontawesome;
  content:'\f004';
  font-style:normal;
}

span {
  position: absolute;
  bottom:70px;
  left:0;
  right:0;
  visibility: hidden;
  transition:.6s;
  z-index:-2;
  font-size:2px;
  color:transparent;
  font-weight:400;
}

i.press {
  animation: size .4s;
  color:#e23b3b;
}

span.press {
  bottom:120px;
  font-size:14px;
  visibility:visible;
  animation: fade 1s;
}

@keyframes fade {
  0% {color:#transparent;}
  50% {color:#e23b3b;}
  100% {color:#transparent;}
}

@keyframes size {
  0% {padding:10px 12px 8px;}
  50% {padding:14px 16px 12px;  
    margin-top:-4px;}
  100% {padding:10px 12px 8px;}
}





</style>
