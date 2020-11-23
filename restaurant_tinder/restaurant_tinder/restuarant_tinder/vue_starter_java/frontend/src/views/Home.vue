<template>
<body>
  <div id="home" class="text-center">
    <div id="logo" class="logo">
            <img src="https://i.ibb.co/5j0YdfQ/CapLogo.png" alt="CapstoneLogo" border="0">
    </div>
      <br>

      <div id="hoodSelect">
      <label for="hoodChoice">Where are you dining?   </label>
      
      <select class="select-dropdown" id="hoodChoice" placeholder="Downtown" v-model="latLong">
        <option value="" disabled selected hidden>Please Choose...</option>
        <option value='&lat=40.447&lon=-79.993' >Downtown</option>
        <option value='&lat=40.461&lon=-80.011' >North Side</option>
        <option value='&lat=40.455&lon=-79.897' >East End</option>
        <option value='&lat=40.37&lon=-80.018' >South Side</option>
        <option value='&lat=40.472&lon=-80.077' >West End</option>
      </select>
      <br>
      
      <button id="refreshButton" class="button theme-font" type="submit" @click.prevent="getRestList">See Restaurants</button>
      </div>

  <div id="card-container" class="theme-font">
    
    <!-- Card #1 - BULMA -->
    <div id="card1" class="card theme-font" v-if="this.searchRun">
      <header class="card-header">
        <p id="restaurant-name0" class="card-header-title w3-black">
          NAME<br>
        </p>
        <!-- <a href="#" class="card-header-icon" aria-label="more-options">
          <span class="icon">
            <i class="fas fa-angle-down" aria-hidden="true"></i>
          </span>
        </a> -->
      </header>
      <div class="card-content">
        
        <div id="locality0">LOCALITY</div>
        <div class="content">
          <div id="rating0">RATING</div>
          <div id="cuisines0">CUISINES</div>
          <!-- <a href="#">@TwitterHandle </a><br> -->
          <br>          
          <div id="info-container" class="w3-container">
          <button id="modal-button1" onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black theme-font">More Information</button>
          <br>
          <div id="icons">
          <img id="fave-icon0" class="image is-32x32 is-hidden" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavIcon" border="0">
          <img id="dislike-icon0" class="image is-32x32 is-hidden" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="dislike-removebg-preview" border="0">
        
          </div> 
            <div id="id01" class="w3-modal">
              <div class="w3-modal-content w3-card-4w 3-card-* ">
                <header class="w3-container w3-black"> 
                  <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright" @click="deleteAlerts">&times;</span>
                  <h2 class="w3-text-white theme-font" id="detail1-0">More Restaurant Details</h2>
                </header>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.dislikeAdded == this.restIds[0]">
                  <center>You have successfully disliked this restaurant.</center>
                </div>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.favouriteAdded == this.restIds[0]">
                  <center>You have added this restaurant to your favorites!</center>
                </div>

                <figure id="card-image" class="image is-128x128 card-image center">
                    <img id="modul-image0" src="" alt="testimg">
                </figure>
                <div id="modal-text" class="w3-container w3-white">
                  <a href="#" id="menu-link0">Menu Link</a>
                  <p class="w3-white" id="phone0">PHONE</p>
                  <p class="w3-white" id="hours0">HOURS</p>
                </div>
    
                <footer id="icon-container" class="w3-container w3-black">
                <a id="list-icon" href="#" v-on:click="addFavourite(restIds[0])"><img class="image is-32x32" id="fav-btn" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavoriteBtn" border="0"></a>
                <a id="list-icon" href="#" v-on:click="addDislike(restIds[0])"><img class="image is-32x32" id="dislike-brn" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="RejectBtn" border="0"></a>
                </footer>
              </div>
            </div>
                </div>

             </div>
      </div>

    </div>
 
      <!-- Card #2 - BULMA -->
  <div id="card2" class="card theme-font" v-if="this.searchRun">
      <header class="card-header">
        <p id="restaurant-name1" class="card-header-title w3-black">
          NAME<br>
        </p>
        <!-- <a href="#" class="card-header-icon" aria-label="more-options">
          <span class="icon">
            <i class="fas fa-angle-down" aria-hidden="true"></i>
          </span>
        </a> -->
      </header>
      <div class="card-content">
        <div id="locality1">LOCALITY</div>
        <div class="content">
          <div id="rating1">RATING</div>
          <div id="cuisines1">CUISINES</div>
          <!-- <a href="#">@TwitterHandle </a><br> -->
          <br>
          <div class="w3-container">
          <!-- <button id="modal-button2" onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-black theme-font">Open Modal</button> -->
          <button id="modal-button2" onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-black theme-font">More Information</button>
          <br>
          <img id="fave-icon1" class="image is-32x32 is-hidden" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavIcon" border="0">
          <img id="dislike-icon1" class="image is-32x32 is-hidden" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="dislike-removebg-preview" border="0">
            <div id="id02" class="w3-modal">
              <div class="w3-modal-content w3-card-4w 3-card-* ">
                <header class="w3-container w3-black">
                  <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-display-topright" @click="deleteAlerts">&times;</span>
                  <h2 class="w3-text-white theme-font" id="detail1-1">More Restaurant Details</h2>
                </header>

                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.dislikeAdded == this.restIds[1]">
                  <center>You have successfully disliked this restaurant.</center>
                </div>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.favouriteAdded == this.restIds[1]">
                  <center>You have added this restaurant to your favorites!</center>
                </div>

                <figure id="card-image" class="image is-128x128 center">
                    <img id="modul-image1" src="" alt="testimg">
                </figure>
                <div class="w3-container w3-white">
                  <a href="#" id="menu-link1">Menu Link</a>
                  <p class="w3-white" id="phone1">PHONE</p>
                  <p class="w3-white" id="hours1">HOURS</p>
                </div>
                <footer id="icon-container" class="w3-container w3-black">
              
                <a href="#" v-on:click="addFavourite(restIds[1])"><img class="image is-32x32" id="fav-btn" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavoriteBtn" border="0"></a>
                <a href="#" v-on:click="addDislike(restIds[1])"><img class="image is-32x32" id="dislike-brn" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="RejectBtn" border="0"></a>
               </footer>
              </div>
            </div>
                </div>

             </div>
      </div>

    </div>
<!-- 
      Card #3 - BULMA -->

      <div id="card3" class="card" v-if="this.searchRun">
      <header class="card-header">
        <p id="restaurant-name2" class="card-header-title w3-black">
          NAME<br>
        </p>
        <!-- <a href="#" class="card-header-icon" aria-label="more-options">
          <span class="icon">
            <i class="fas fa-angle-down" aria-hidden="true"></i>
          </span>
        </a> -->
      </header>
      <div class="card-content">
        <div id="locality2">LOCALITY</div>
        <div class="content">
          <div id="rating2">RATING</div>
          <div id="cuisines2">CUISINES</div>
          <!-- <a href="#">@TwitterHandle </a><br> -->
          
          <br>
          <div class="w3-container">
          <button id="modal-button3" onclick="document.getElementById('id03').style.display='block'" class="w3-button w3-black theme-font">More Information</button>
          <br>
          <img id="fave-icon2" class="image is-32x32 is-hidden" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavIcon" border="0">
          <img id="dislike-icon2" class="image is-32x32 is-hidden" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="dislike-removebg-preview" border="0">
            <div id="id03" class="w3-modal">
              <div class="w3-modal-content w3-card-4w 3-card-* ">
                <header class="w3-container w3-black"> 
                  <span onclick="document.getElementById('id03').style.display='none'" class="w3-button w3-display-topright" @click="deleteAlerts">&times;</span>
                  <h2 class="w3-text-white theme-font" id="detail1-2">More Restaurant Details</h2>
                </header>

                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.dislikeAdded == this.restIds[2]">
                  <center>You have successfully disliked this restaurant.</center>
                </div>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.favouriteAdded == this.restIds[2]">
                  <center>You have added this restaurant to your favorites!</center>
                </div>

                <figure id="card-image" class="image is-128x128 center">
                    <img id="modul-image2" src="" alt="testimg">
                </figure>
                <div class="w3-container w3-white">
                  <a href="#" id="menu-link2">Menu Link</a>
                  <p class="w3-white" id="phone2">PHONE</p>
                  <p class="w3-white" id="hours2">HOURS</p>
                </div>
                <footer id="icon-container" class="w3-container w3-black">
                <a href="#" v-on:click="addFavourite(restIds[2])"><img class="image is-32x32" id="fav-btn" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavoriteBtn" border="0"></a>
                <a href="#" v-on:click="addDislike(restIds[2])"><img class="image is-32x32" id="dislike-brn" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="RejectBtn" border="0"></a>
              </footer>
              </div>
            </div>
                </div>
             </div>
      </div>
    </div>
</div>


    
  <div id="other-card-container" class="theme-font">

    <!-- Card 4 -->
    <div id="card4" class="card theme-font" v-if="this.searchRun">
   <header class="card-header">
        <p id="restaurant-name3" class="card-header-title w3-black">
          NAME<br>
        </p>
        <!-- <a href="#" class="card-header-icon" aria-label="more-options">
          <span class="icon">
            <i class="fas fa-angle-down" aria-hidden="true"></i>
          </span>
        </a> -->
      </header>
      <div class="card-content">
        <div id="locality3">LOCALITY</div>
        <div class="content">
          <div id="rating3">RATING</div>
          <div id="cuisines3">CUISINES</div>
          <!-- <a href="#">@TwitterHandle </a><br> -->
          
          <br>
          <div class="w3-container">
          <!-- <button id="modal-button2" onclick="document.getElementById('id04').style.display='block'" class="w3-button w3-black theme-font">Open Modal</button> -->
          <button id="modal-button3" onclick="document.getElementById('id04').style.display='block'" class="w3-button w3-black theme-font">More Information</button>
<br>
          <img id="fave-icon3" class="image is-32x32 is-hidden" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavIcon" border="0">
          <img id="dislike-icon3" class="image is-32x32 is-hidden" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="dislike-removebg-preview" border="0">
            <div id="id04" class="w3-modal">
              <div class="w3-modal-content w3-card-4w 3-card-* ">
                <header class="w3-container w3-black"> 
                  <span onclick="document.getElementById('id04').style.display='none'" class="w3-button w3-display-topright" @click="deleteAlerts">&times;</span>
                  <h2 class="w3-text-white theme-font" id="detail1-3">More Restaurant Details</h2>
                </header>

                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.dislikeAdded == this.restIds[3]">
                  <center>You have successfully disliked this restaurant.</center>
                </div>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.favouriteAdded == this.restIds[3]">
                  <center>You have added this restaurant to your favorites!</center>
                </div>

                <figure id="card-image" class="image is-128x128 center">
                    <img id="modul-image3" src="" alt="testimg">
                </figure>
                <div class="w3-container w3-white">
                  <a href="#" id="menu-link3">Menu Link</a>
                  <p class="w3-white" id="phone3">PHONE</p>
                  <p class="w3-white" id="hours3">HOURS</p>
                </div>
                <footer id="icon-container" class="w3-container w3-black">
                <a href="#" v-on:click="addFavourite(restIds[3])"><img class="image is-32x32" id="fav-btn" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavoriteBtn" border="0"></a>
                <a href="#" v-on:click="addDislike(restIds[3])"><img class="image is-32x32" id="dislike-brn" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="RejectBtn" border="0"></a>
                </footer>
              </div>
            </div>
                </div>

             </div>
      </div>

    </div>


    <!-- Card 5 -->
  <div id="card5" class="card theme-font" v-if="this.searchRun">
      <header class="card-header">
        <p id="restaurant-name4" class="card-header-title w3-black">
          NAME<br>
        </p>
        <!-- <a href="#" class="card-header-icon" aria-label="more-options">
          <span class="icon">
            <i class="fas fa-angle-down" aria-hidden="true"></i>
          </span>
        </a> -->
      </header>
      <div class="card-content">
        <div id="locality4">LOCALITY</div>
        <div class="content">
          <div id="rating4">RATING</div>
          <div id="cuisines4">CUISINES</div>
          <!-- <a href="#">@TwitterHandle </a><br> -->
          
          <br>
          <div class="w3-container">
          <!-- <button id="modal-button2" onclick="document.getElementById('id05').style.display='block'" class="w3-button w3-black theme-font">Open Modal</button> -->
          <button id="modal-button5" onclick="document.getElementById('id05').style.display='block'" class="w3-button w3-black theme-font">More Information</button>
          <br>
          <img id="fave-icon4" class="image is-32x32 is-hidden" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavIcon" border="0">
          <img id="dislike-icon4" class="image is-32x32 is-hidden" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="dislike-removebg-preview" border="0">
            <div id="id05" class="w3-modal">
              <div class="w3-modal-content w3-card-4w 3-card-* ">
                <header class="w3-container w3-black"> 
                  <span onclick="document.getElementById('id05').style.display='none'" class="w3-button w3-display-topright" @click="deleteAlerts">&times;</span>
                  <h2 class="w3-text-white theme-font" id="detail1-4">More Restaurant Details</h2>
                </header>

                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.dislikeAdded == this.restIds[4]">
                  <center>You have successfully disliked this restaurant.</center>
                </div>
                <div id="register-alert" class="notification is-danger is-light theme-font alert-transparent" role="alert" v-if="this.$route.query.favouriteAdded == this.restIds[4]">
                  <center>You have added this restaurant to your favorites!</center>
                </div>

                <figure id="card-image" class="image is-128x128 center">
                    <img id="modul-image4" src="" alt="testimg">
                </figure>
                <div class="w3-container w3-white">
                  <a href="#" id="menu-link4">Menu Link</a>
                  <p class="w3-white" id="phone4">PHONE</p>
                  <p class="w3-white" id="hours4">HOURS</p>
                </div>
                <footer id="icon-container" class="w3-container w3-black">
                <a href="#" v-on:click="addFavourite(restIds[4])"><img class="image is-32x32" id="fav-btn" src="https://i.ibb.co/mGBDf1N/download-removebg-preview.png" alt="FavoriteBtn" border="0"></a>
                <a href="#" v-on:click="addDislike(restIds[4])"><img class="image is-32x32" id="dislike-brn" src="https://i.ibb.co/c6Y3jKK/dislike-removebg-preview.png" alt="RejectBtn" border="0"></a>
             </footer>
              </div>
            </div>
                </div>

             </div>
      </div>

    </div>



  </div>

</div> 
</body>
</template>


<script>
import auth from '../auth';

export default {
  name: 'home',
  components: {
  },
  data() {
    return {
      returnedRestaurants: [],
      userPreferences: [],
      latLong: "",
      userDislikes: [],
      userFavourites: [],
      searchRun: false,
      restIds: [],
      dislikes: [false, false, false, false, false]
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
          this.userPreferences = response;
        })
        .catch(err => console.error(err));
        },

    getDislikes() {
        fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/dislikes`, {
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
          this.userDislikes = response;
          console.log(this.userDislikes);
        })
        .catch(err => console.error(err));
        },

    getFavourites() {
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
        .then(response => {
          console.log(response);
          this.userFavourites = response;
          console.log(this.userFavourites);
        })
        .catch(err => console.error(err));
        },
      
      addDislike: function(restId) {
         fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/addDislike`, {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            },
            body: restId,
            },
            console.log("Fetch reached.")
            )
            .then(response => {
                if(response.ok) {
                    console.log("Successful POST");
                    this.$router.push({
                        path: "/",
                        query: { dislikeAdded: restId }
                    });
                    console.log("Response Okay.")
                } else {
                    console.log("POST error")
                }
            })
            .then(err => console.error(err))

      },

      addFavourite: function(restId) {
         fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/addFavourite`, {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            },
            body: restId,
            },
            console.log("Fetch reached.")
            )
            .then(response => {
                if(response.ok) {
                    console.log("Successful POST");
                    this.$router.push({
                        path: "/",
                        query: { favouriteAdded: restId }
                    });
                    console.log("Response Okay.")
                } else {
                    console.log("POST error")
                }
            })
            .then(err => console.error(err))

      },

      removeDislike(restId) {
         fetch(`${process.env.VUE_APP_BACKEND_BASE_URL}/removeDislike`, {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + auth.getToken(),
                    Accept: "application/json",
                    "Content-Type": "application/json"
            },
            body: JSON.stringify(restId),
            },
            console.log("Fetch reached.")
            )
            .then(response => {
                if(response.ok) {
                    console.log("Successful POST");
                    this.$router.push({
                        path: "/",
                        query: { dislikeRemoved: restId }
                    });
                    console.log("Response Okay.")
                } else {
                    console.log("POST error")
                }
            })
            .then(err => console.error(err))

      },
      
      getRestList() {
        if(this.latLong != "") {
          this.searchRun = true;
        }

        let cuisineString = "";
        for(let i = 0; i < this.userPreferences.preferences.length; i++) {
          cuisineString += this.userPreferences.preferences[i] + "%2C";
        }
        let radius = "2000";
        if (this.latLong === '&lat=40.447&lon=-79.993') {
          radius = "1000";
        }
        const apiQuery = 'https://developers.zomato.com/api/v2.1/search?apikey=' + process.env.VUE_APP_ZOMATO_API_KEY + '&count=5' + this.latLong + '&radius=' + radius + '&cuisines=' + cuisineString + '&sort=real_distance&order=asc';
        console.log(apiQuery);

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
          
          this.returnedRestaurants = results;
          this.cardData(this.returnedRestaurants.restaurants);
        })
        .catch(err => console.error(err));
      },

      cardData(object) {
      object.forEach((r, i) => {

        let restaurantNameId = "restaurant-name" + i;
        let cardLocalityId = "locality" + i;
        let cardCuisinesId = "cuisines" + i;
        let cardRatingId = "rating" + i;
        let cardMenuId = "menu-link" + i;

        let modalName = "detail1-" + i;
        let modalImageId = "modul-image" + i;
        let modalPhoneId = "phone" + i;
        let hoursId = "hours" + i;

        let dislikeIconId = "dislike-icon" + i;
        let faveIconId = "fave-icon" + i;

        document.getElementById(restaurantNameId).innerText = r.restaurant.name;
        document.getElementById(cardLocalityId).innerText = r.restaurant.location.locality;
        document.getElementById(cardCuisinesId).innerText = r.restaurant.cuisines;
        document.getElementById(cardRatingId).innerText = "Rating:  " + r.restaurant.user_rating.aggregate_rating + "/5";
        document.getElementById(cardMenuId).href = r.restaurant.menu_url;

        document.getElementById(modalName).innerText = r.restaurant.name;
        document.getElementById(modalImageId).src = r.restaurant.thumb;
        document.getElementById(modalPhoneId).innerText = r.restaurant.phone_numbers;
        document.getElementById(hoursId).innerText = r.restaurant.timings;

        this.restIds.push(r.restaurant.id);
        

        this.userDislikes.forEach(dislike => {
            if(r.restaurant.id == dislike.restaurantId) {
              console.log("Found a match");
              document.getElementById(dislikeIconId).classList.remove("is-hidden");

            
            }
        })

        this.userFavourites.forEach(fave => {
            if(r.restaurant.id == fave.restaurantId) {
              console.log("Found a fave match");
              document.getElementById(faveIconId).classList.remove("is-hidden");

            
            }
        })
      
      });
      },

      deleteAlerts() {
        let x = document.getElementsByClassName("notification");
        var i;
        for (i = 0; i < x.length; i++) {
          x[i].parentNode.removeChild(x[i]);
        }
      }
  },

  mounted() {
    console.log("Hello mounted");
    this.getPreferences();
    this.getDislikes();
    this.getFavourites();
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

.is-hidden {
  display: none;
}

#main-card{
  background-color: rgba(255,255,255,.25);
}

.outline {
  border-color: var(--theme-color);

}

.theme-font {
    font-family: var(--theme-font);

}

#icon-container{
  display: flex;
  flex-direction: row;
  align-items: center;
  text-align:center;
  justify-content: center;
  align-content: center;
  position:center;
  
}

#hoodSelect {
  font-family: var(--theme-font);
  background: white;
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

#restaurant-name0 {
  align-content: center;
  text-align: center;
  justify-content: center;
}
#restaurant-name1 {
  align-content: center;
  text-align: center;
  justify-content: center;
}
#restaurant-name2 {
  align-content: center;
  text-align: center;
  justify-content: center;
}
#restaurant-name3 {
  align-content: center;
  text-align: center;
  justify-content: center;
}
#restaurant-name4 {
  align-content: center;
  text-align: center;
  justify-content: center;
}

#fav-btn{

    background-color: transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
}
#dislike-btn{
  background:transparent;
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
#card-container {
  display:flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 25px;
  
}

/* #card-image {
  justify-content: center;
  align-content: center;
  text-align: center;
  align-items: center;
}

.card-image {
  justify-content: center;
  align-content: center;
  text-align: center;
  align-items: center;
} */

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
}

#other-card-container {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-evenly;
    padding-left: 20px;
    padding-right: 20px;
    padding-top: 25px;
    padding-bottom: 30px;

}

#modal-button1{
    background-color: white;
    border-color: #dbdbdb;
    border-width: 1px;
    color: #363636;
    cursor: pointer;
    justify-content: center;
    padding-bottom: calc(0.5em - 1px);
    padding-left: 1em;
    padding-right: 1em;
    padding-top: calc(0.5em - 1px);
    text-align: center;
    white-space: nowrap;
}

#modal-button2{
    background-color: white;
    border-color: #dbdbdb;
    border-width: 1px;
    color: #363636;
    cursor: pointer;
    justify-content: center;
    padding-bottom: calc(0.5em - 1px);
    padding-left: 1em;
    padding-right: 1em;
    padding-top: calc(0.5em - 1px);
    text-align: center;
    white-space: nowrap;
}
#modal-button3{
    background-color: white;
    border-color: #dbdbdb;
    border-width: 1px;
    color: #363636;
    cursor: pointer;
    justify-content: center;
    padding-bottom: calc(0.5em - 1px);
    padding-left: 1em;
    padding-right: 1em;
    padding-top: calc(0.5em - 1px);
    text-align: center;
    white-space: nowrap;
}

#modal-text {
  color: black;

}
.card-content{
  align-content: center;
  text-align: center;
  justify-content: center;

}

.card-header{
  font-family: var(--theme-font);
  
}


#card1 {
  position: relative;
  width: 30%;
  height: 75%;
  margin: 5px;
  justify-content: space-between;
  align-content: center;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  background-color: rgba(255,255,255,.50);
  color:black;

}
#card2 {
  position: relative;
  width: 30%;
  height: 75%;
  margin: 5px;
  justify-content: space-between;
  align-content: center;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  background-color: rgba(255,255,255,.50);
  color:black;

}

#card3{
  position: relative;
  width: 30%;
  height: 75%;
  margin: 5px;
  justify-content: space-between;
  align-content: center;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  background-color: rgba(255,255,255,.50);
  color:black;
}

#card4 {
  position: relative;
  width: 30%;
  height: 75%;
  margin: 5px;
  justify-content: space-around;  
  align-content: center;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  background-color: rgba(255,255,255,.50);
  color:black;
}

#card5 {
  position: relative;
  width: 30%;
  height: 75%;
  margin: 5px;
  justify-content: space-around;
  -webkit-box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  background-color: rgba(255,255,255,.50);
  color:black;

}

body {
  background-image: url('https://i.ibb.co/TDXZm4N/italian-food-background-1-shaded.jpg');
  background-position: center center;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
  margin: 0;
  padding: 0;

}

.register-page {
  width: 360px;
  padding: 10% 0 0;
  margin: auto;
  background: transparent;

}

#restaurant-name {
  color: black;
}

.home-title {
  color: white;
  font-family: var(--theme-font);
  font-size: 48px;
  font-weight: bold;
  text-align: center;
}
.home-subtitle {
  color: white;
  font-family: var(--theme-font);
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
p {
  color: white;
  font-family: var(--theme-font);
  font-size: 18px;
  text-align: center;
}

.select-dropdown {
 font-family: var(--theme-font);
 select {
    font-family: var(--theme-font)
}

}

</style>


