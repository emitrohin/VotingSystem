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
 
* to see list of menus of restaurants with dishes and prices for today
* to vote for restaurant if menu meets any requirements
* to change their mind before 11:00
* to see result during and after voting

### Admins

* You must have credentials to access admin features. This pair is for admin:
    * E_Mitrohin / admin

Admin has full control of this service:

* CRUD operation with restaurant
* CRUD operation with dishes and prices
* CRUD operation with users
* CRUD operation with menus
* Operation that are allowed to ordinary users