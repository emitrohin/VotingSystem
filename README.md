# Restaurant voting system 2016
### JSON API using Spring (MVC, Security, Data) / Hibernate / REST(Jackson) / HSQLDB (in-memory)

* [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ff9b4f0e7af349348df178c133107c5d)](https://www.codacy.com/app/emitrohin/VotingSystem?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=emitrohin/VotingSystem&amp;utm_campaign=Badge_Grade)
* [![Dependency Status](https://dependencyci.com/github/emitrohin/VotingSystem/badge)](https://dependencyci.com/github/emitrohin/VotingSystem)
* [![Build Status](https://travis-ci.org/emitrohin/VotingSystem.svg?branch=master)](https://travis-ci.org/emitrohin/VotingSystem)

----------
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
   
     User login: user2 
     password: lapteva
     Authorization: Basic dXNlcjI6bGFwdGV2YQ==
    
Ordinary users are allowed:

* to see list restaurants for today with menus fo today
 
    curl -X GET -H "Authorization: Basic dXNlcjI6bGFwdGV2YQ== "http://{hostname}/api/v1.0/restaurants/"
 
* to vote for restaurant if menu meets any requirements and to change their mind before 11:00

    curl -X POST -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}/vote"

* to see results

    curl -X GET -H "Authorization: Basic {your_encrypted_basic_auth}" "http://{hostname}/api/v1.0/restaurants/votes/"

----------

### Admins

* You must have credentials to access admin features. Admins create menus for current day, dishes, and set price for them. 

     Admin login: admin 
     password: admin
     Authorization: Basic YWRtaW46YWRtaW4=
     
**Operations with restaurants**

Create new restaurant

    curl -X POST -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '  {
         "name": "Restaurant name",
         "imageLink": "Restaurant image link (url)"
       }' "http://{hostname}/api/v1.0/restaurants/"

Update restaurant

    curl -X PUT -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '  {
         "name": "Changed restaurant name",
         "imageLink": "Changed restaurant image link (url)"
       }' "http://{hostname}/api/v1.0/restaurants/{id_to_update}"

Delete restaurant

    curl -X DELETE -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}"

**Operations with menus**

Create new menu (only for current day)

    curl -X POST -H "Authorization: Basic YWRtaW46YWRtaW4" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}/menu"

Delete menu

    curl -X DELETE -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/restaurants/{restaurant_id}/menu"

**Operations with dishes**

Create dish

    curl -X POST -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '{"name": "name", "imageLink": "link", "price": double value}' "http://{hostname}/api/v1.0/dishes/{menuId}"

Update dishes

    curl -X PUT -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '{"name": "name", "imageLink": "link", "price": double value}' "http://{hostname}/api/v1.0/dishes/{dish_id}"

Delete dishes

    curl -X DELETE -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/dishes/{dish_id}"

**Operations with users**

Get user

    curl -X GET -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/users/{user_id}"

Get all users

    curl -X GET -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/users/"

Create new user

    curl -X POST -H "Authorization: Basic YWRtaW46YWRtaW4=" -d '  {
         "login": "login",
         "password": "password",
         "password": "password",
         "email": "email",
         "firstName": "firstName",
         "lastName": "lastName",
         "enabled": "true",
       }' "http://{hostname}/api/v1.0/users/"

Update user

    curl -X PUT -H "Authorization: Basic YWRtaW46YWRtaW4= -d '  {
         "login": "login",
         "password": "password",
         "password": "password",
         "email": "email",
         "firstName": "firstName",
         "lastName": "lastName",
         "enabled": "true",
       }' "http://{hostname}/api/v1.0/users/{user_id}"

Delete user

    curl -X DELETE -H "Authorization: Basic YWRtaW46YWRtaW4=" "http://{hostname}/api/v1.0/users/{user_id}"

