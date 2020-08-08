# Create a cards

+ **URL** : `/boards/:boardId/columns/:columnId/cards`

+ **URL Parameters** : `boardId=[integer]` `columnId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `POST`

+ **Body** :


        {
            "name": "[unicode 255 chars max]"
        }


+ **Body Example** :


        {
            "name": "card-1"
        }


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `201 CREATED`
    
    + **Content example** :


             {
               "id": 55,
               "name": "card-1"
             }


+ **Error response** :

    + **Condition** :  If card not valid
    
    + **Code** : `400 Bad request`
    
    + **Content** :
    
    
            {
                "status": "BAD_REQUEST",
                "code": 400,
                "time": "2020-08-04 11:20:05"
            }
