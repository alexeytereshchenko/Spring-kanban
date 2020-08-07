# Update a board

+ **URL** : `/boards/:boardId`

+ **URL Parameters** : `boardId=[integer]` where `pk` is the ID of the board on the server

+ **Auth required**: **Yes**

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
