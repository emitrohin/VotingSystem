## Restaurant voting system 2016
### JSON API using Spring (MVC, Security, Data) / Hibernate / REST(Jackson) / HSQLDB

* Codacy: [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ff9b4f0e7af349348df178c133107c5d)](https://www.codacy.com/app/emitrohin/VotingSystem?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=emitrohin/VotingSystem&amp;utm_campaign=Badge_Grade)
* Dependency CI: [![Dependency Status](https://dependencyci.com/github/emitrohin/VotingSystem/badge)](https://dependencyci.com/github/emitrohin/VotingSystem)
* Travis CI: [![Build Status](https://travis-ci.org/emitrohin/VotingSystem.svg?branch=master)](https://travis-ci.org/emitrohin/VotingSystem)



Voting system for deciding where to have lunch:

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    * If it is before 11:00 we asume that he changed his mind.
    * If it is after 11:00 then it is too late, vote can't be changed
    
## How to play round?
### Users

* Anonymous access is prohibited 
* You must have credentials to access voting system. You can take these login/password pair for tests:
    * L_Lapteva / lapteva
    * A_Ustumov / ustimov
    * N_Gimaldinova / gimaldinona

Ordinary users are allowed:

* to see list restaurants for today with active menus
 
`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/"`
 
* to see list of menus of restaurants with dishes and prices for today

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/menus/"`

* to vote for restaurant if menu meets any requirements and to change their mind before 11:00

`curl -X POST -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/votes/{restaurant_id}"`

* to see result during and after voting

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/votes/"`


### Admins

* You must have credentials to access admin features. Admins create menus for current day, dishes, and set price for them. This pair is for admin:
    * E_Mitrohin / admin

Admin has full control of this service:

* CRUD operation with restaurant

**get restaurant**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}"`

**get all restaurant**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/all"`

**create new restaurant**

`curl -X POST -H "Authorization: Basic {your_encrypted_basic_auth}" -d '  {
     "name": "Restaurant name",
     "imageLink": "Restaurant image link (url)"
   }' "http://{hostname}/api/v1.0/restaurants/"`

**update restaurant**

`curl -X PUT -H "Authorization: Basic {your_encrypted_basic_auth}" -d '  {
     "name": "Changed restaurant name",
     "imageLink": "Changed restaurant image link (url)"
   }' "http://{hostname}/api/v1.0/restaurants/{id_to_update}"`

**delete restaurant**

`curl -X DELETE -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}"`

* CRUD operation with dishes and prices

**get dish to menu**

**remove dish from menu**

**update dish in menu**

**delete dish on menu**

**get dish**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/dishes/{dish_id}"`

**get dishes**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/dishes/"`

**create dish**

`curl -X POST -H "Authorization: Basic {your_encrypted_basic_auth}" -d '{"name": "name", "imageLink": "link"}' "http://{hostname}/api/v1.0/dishes/"`

**update dishes**

`curl -X PUT -H "Authorization: Basic {your_encrypted_basic_auth}" -d '{"name": "name", "imageLink": "link"}' "http://{hostname}/api/v1.0/dishes/{dish_id}"`

**delete dishes**

`curl -X DELETE -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/dishes/{dish_id}"`

* CRUD operation with users

**get user**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/users/{user_id}"`

**get all users**

`curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/users/"`

**create new user**

`curl -X POST -H "Authorization: Basic {your_encrypted_basic_auth}" -d '  {
     "login": "login",
     "password": "password",
     "password": "password",
     "email": "email",
     "firstName": "firstName",
     "lastName": "lastName",
     "enabled": "true",
   }' "http://{hostname}/api/v1.0/users/"`

**update user**

`curl -X PUT -H "Authorization: Basic {your_encrypted_basic_auth}" -d '  {
     "login": "login",
     "password": "password",
     "password": "password",
     "email": "email",
     "firstName": "firstName",
     "lastName": "lastName",
     "enabled": "true",
   }' "http://{hostname}/api/v1.0/users/{user_id}"`

**delete user**

`curl -X DELETE -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/users/{user_id}"`

* CRUD operation with menus

* Operation that are allowed to ordinary users (see above)