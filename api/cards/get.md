### Get a card

+ **URL** : `/boards/:boardId/columns/:columnId/cards/:cardId`

+ **URL Parameters** : `boardId=[integer]` `columnId=[integer]` `cardId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `Get`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 OK`
    
    + **Content example** :


             {
               "id": 55,
               "name": "card"
             }


+ **Error response** :

    + **Condition** :  If card not exist
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }
