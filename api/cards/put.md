# Update a card

+ **URL** : `/boards/:boardId/columns/:columnId/cards/:cardId`

+ **URL Parameters** : `boardId=[integer]` `columnId=[integer]` `cardId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `PUT`

+ **Content** :


        {
            "name": "[unicode 255 chars max]"
        }


+ **Content Example** :


        {
            "name": "card-2"
        }


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 Ok`

+ **Error response** :

    + **Condition** :  If card not valid
    
    + **Code** : `400 Bad request`
    
    + **Content** :
    
    
            {
                "status": "BAD_REQUEST",
                "code": 400,
                "time": "2020-08-04 11:20:05"
            }


+ **Or** :

    + **Condition** :  If card not exist
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }
