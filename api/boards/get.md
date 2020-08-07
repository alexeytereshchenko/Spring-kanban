### Get 1 board

+ **URL** : `/boards/:boardId/`

+ **URL Parameters** : `boardId=[integer]` where `pk` is the ID of the board on the server

+ **Auth required**: **Yes**

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
