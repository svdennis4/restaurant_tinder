BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cuisines;
DROP TABLE IF EXISTS users_cuisines;
DROP TABLE IF EXISTS favourites;
DROP TABLE IF EXISTS dislikes;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user'),
  email varchar(100)
);

CREATE TABLE cuisines (cuisine_id int PRIMARY KEY,
cuisine_name varchar(100) UNIQUE);

CREATE TABLE users_cuisines (user_id bigint , cuisine_id int,
CONSTRAINT pk_usercuisine PRIMARY KEY (user_id,cuisine_id),
CONSTRAINT fk_usercuisine_users FOREIGN KEY (user_id) REFERENCES users(id),
CONSTRAINT fk_usercuisine_cuisine FOREIGN KEY (cuisine_id) REFERENCES cuisines(cuisine_id)
);

CREATE TABLE favourites (
  user_id bigint,
  restaurant_id bigint,

  CONSTRAINT  pk_favourites PRIMARY KEY (user_id,restaurant_id),
  CONSTRAINT  fk_favourites FOREIGN KEY(user_id)  REFERENCES  users(id)
);

CREATE TABLE dislikes (
  user_id bigint,
  restaurant_id bigint,

  CONSTRAINT pk_dislikes PRIMARY KEY (user_id, restaurant_id),
  CONSTRAINT fk_dislikes FOREIGN KEY(user_id) REFERENCES users(id)
);

INSERT INTO cuisines (cuisine_id,cuisine_name) VALUES (152,	'African'),(1	,'American'),(5	,'Bakery'),(193,'BBQ'),(25,	'Chinese'),(161,	'Coffee and Tea'),
(881,	'Crepes'),(153,	'Cuban'),(192,	'Deli'),(268,	'Drinks Only'),(45,	'French'),(156,	'Greek'),(233	,'Ice Cream'),(148,	'Indian'),
(55,	'Italian'),(60,	'Japanese'),(136,	'Latin American'),(70	,'Mediterranean'),(73,	'Mexican'),(137	,'Middle Eastern'),(82,	'Pizza'),
(320,	'Ramen'),(83,	'Seafood'),(461,	'Soul Food'),(141	,'Steak'),(177,	'Sushi'),(997,	'Taco'),(179,	'Tapas'),(95,	'Thai'),(308,	'Vegetarian');





COMMIT TRANSACTION;
