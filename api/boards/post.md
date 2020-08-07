# Create a board

+ **URL** : `/boards`

+ **Auth required**: **Yes**

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
