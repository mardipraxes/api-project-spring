#NEWS-API-PROJECT

---link hirokumata---

###Description
This project is a final group exercise as part of Mindera's Mindswap Academy back-end course.
Our target MVP is a Java 17 based app following the MVC architecture.
Specifically, an API whose RMDB (MariaDB) contains news articles from both independent journalists and established
publications.
The main purpose of which is to allow for users to rate said articles
on the basis of their written quality, bias or lack thereof and "truthfulness", on a centralised repository.



###General Overview
* Users must register before interacting with the app.
* Users can search news articles by title, author, publication, published date and category.
* Users can also post their articles after asking to be validated as journalists.
* This is accomplished through HTTP requests.
* News articles are then returned as a JSON file through an HTTP response.
* For demonstrative purposes, the mediastack API was used as the callback API.


## Table of Contents
1.[Software used] (#software-used)
* [Dependencies] (#dependencies)

2.[HTTP requests] (#http-requests)
* [Homepage] (#homepage)
* [Users] (#users)
    * [Registration] (#registration)
    * [Others] (#others)
* [News] (#news)
    * [Post] (#post)
    * [Rate] (#rate)
    * [Edit] (#edit)
    * [Search] (#search)


## Software Used
* IntelliJ IDEA Community Edition 2021.3.3
* MySQL Workbench 8.0.28
* Git 2.25.1
* Docker Desktop 4.6.1


###Dependencies
* Maven
* Spring Boot
* Jwt
* JavaDoc

For more information on dependencies, you can read the [pom.xml](https://github.com/mardipraxes/api-project-spring/blob/main/pom.xml) file.
You can also find more information in the [HELP.md](https://github.com/mardipraxes/api-project-spring/blob/main/HELP.md) file.
##HTTP requests
List of currently possible HTTP requests followed by examples.


###_Homepage_
http://localhost:8081/api/homepage

###_Users_
####1.Registration

**User registration:** http://localhost:8081/api/register

**Login:** http://localhost:8081/api/login

**Logout:** http://localhost:8081/api/logout

**Change password:** http://localhost:8081/api/change-password

**Change e-mail:** http://localhost:8081/api/change-email

**Change country:** http://localhost:8081/api/change-country

**Ask to be validated as a journalist:** http://localhost:8081/api/apply-journalist


####2.Others

**Get all users:** http://localhost:8081/api/users
(admin only)

**Search user by ID** http://localhost:8081/api/user/4
(admin only)

**Get user in current session:** http://localhost:8081/api/me

**Refresh authentication token:** http://localhost:8081/api/news/refresh-token


###_News_

####1.Post
http://localhost:8081/api/news/post
(must be validated as journalists)

####2.Rate
http://localhost:8081/api/news/rate/russia-nukes-portugal-again-wtf
(news article retrieved from Fallout Wiki)

####3.Edit
**Edit news:** http://localhost:8081/api/news/edit
(news article must be owned by user in session. Choice of article is made at front-end through body)

**Delete a news article by ID:** http://localhost:8081/api/news/delete/5
(admin only)

####4.Search
**Load news to database from mediastack API:** http://localhost:8081/api/getnewsfromapi?categories=sports

**Search news posted by users:** http://localhost:8081/api/news/search?categories=sports

**Search news from users and external sources:** http://localhost:8081/api/news/searchall?categories=business




###SPECIAL THANKS
Diogo Velho, Coffee, Our pets


__________________________________________________________________________________________



                ;'-. 
     ;-._        )  '---.._
      >  `-.__.-'          `'.__
     /_.-'-._         _,    ^ ---)
             `'------/_.'----```


Contributors: Amadis Alves, David Millasseau, Diogo Bozan, João Gonçalves