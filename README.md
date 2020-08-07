# Spring-kanban
This is api for kanban board

## Try it with docker-compose

    docker-compose up
    
    
    localhost:8080/api

### Open Endpoints:

* [Login](#login)  : `POST /auth/login`

* [Signup](#signup) : `POST /auth/signup`

### Close Endpoints:

* [Boards](#boards) : `{ GET, POST, PUT, DELETE } /boards`

* [Columns](#columns) : `{ GET, POST, PUT, DELETE } /boards/:boardId/columns`

* [Cards](#cards) : `{ GET, POST, PUT, DELETE } /boards/:boardId/columns/:columnId/cards`

## Login:

Login into an Account

+ **URL** : `/auth/login`

+ **Method** : `POST`

+ **Data constraints** : 


        {
            "username": "[unicode 255 chars max]",
            "password": "[unicode 255 chars max]"
        }


+ **Data example** All fields must be sent


        {
            "username": "admin",
            "password": "admin"
        }    


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `201 CREATED`
    
    + **Content example** :
   
        
            {
                "username": "alex",
                "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4In0.kiwWuia3pvp11_u8ob3Nf5myJ8cxonSpPQXgBMb-P3I",
                "type": "Bearer "
            }
        
            
+ **Error response** :

    + **Condition** : If 'username' and 'password' combination is wrong.
    
    + **Code** : `401 UNAUTHORIZED`


## Signup:

Create an Account

+ **URL** : `/auth/signup`

+ **Method** : `POST`

+ **Data constraints** : 


        {
            "username": "[unicode 255 chars max]",
            "password": "[unicode 255 chars max]"
        }


+ **Data example** All fields must be sent


        {
            "username": "admin",
            "password": "admin"
        }    


+ **Successful response** :
    
    + **Condition** : If everything is OK and an Account didn't exist for this User
    
    + **Code** : `201 CREATED`
    
    + **Content example** :
    
        
            {
                "username": "alex",
                "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4In0.kiwWuia3pvp11_u8ob3Nf5myJ8cxonSpPQXgBMb-P3I",
                "type": "Bearer "
            }


+ **Error response** :

    + **Condition** :  If Account already exists for User.
    
    + **Code** : `401 UNAUTHORIZED`



## Boards:

### Get all boards

+ **URL** : `/boards`

+ **Method** : `Get`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 OK`
    
    + **Content example** :
    
        
            [
                {
                    "id": 55,
                    "name": "board"
                },
                {
                    "id": 56,
                    "name": "board"
                },
                {
                    "id": 57,
                    "name": "board"
                }
            ]

### Get 1 board

+ **URL** : `/boards/:boardId/`

+ **URL Parameters** : `boardId=[integer]` where `pk` is the ID of the board on the server

+ **Method** : `Get`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 OK`
    
    + **Content example** :


             {
               "id": 55,
               "name": "board"
             }


+ **Error response** :

    + **Condition** :  If board not created
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }


### Create a board

+ **URL** : `/boards`

+ **Method** : `POST`

+ **Body** :


        {
            "name": "[unicode 255 chars max]"
        }


+ **Body Example** :


        {
            "name": "board-1"
        }


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `201 CREATED`
    
    + **Content example** :


             {
               "id": 55,
               "name": "board-1"
             }


+ **Error response** :

    + **Condition** :  If board not valid
    
    + **Code** : `400 Bad request`
    
    + **Content** :
    
    
            {
                "status": "BAD_REQUEST",
                "code": 400,
                "time": "2020-08-04 11:20:05"
            }


### Update a board

+ **URL** : `/boards/:boardId`

+ **URL Parameters** : `boardId=[integer]` where `pk` is the ID of the board on the server

+ **Method** : `PUT`

+ **Content** :


        {
            "name": "[unicode 255 chars max]"
        }


+ **Content Example** :


        {
            "name": "board-2"
        }


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 Ok`

+ **Error response** :

    + **Condition** :  If board not valid
    
    + **Code** : `400 Bad request`
    
    + **Content** :
    
    
            {
                "status": "BAD_REQUEST",
                "code": 400,
                "time": "2020-08-04 11:20:05"
            }


+ **Or** :

    + **Condition** :  If board not created
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }


### Delete a board

+ **URL** : `/boards/:boardId`

+ **URL Parameters** : `boardId=[integer]` where `pk` is the ID of the board on the server

+ **Method** : `DELETE`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 Ok`

+ **Error response**

    + **Condition** :  If board not created
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }


## Columns:

## Cards:

