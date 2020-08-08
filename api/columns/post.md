# Create a column

+ **URL** : `/boards/:boardId/columns`

+ **URL Parameters** : `boardId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `POST`

+ **Body** :


        {
            "name": "[unicode 255 chars max]"
        }


+ **Body Example** :


        {
            "name": "tasks"
        }


+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `201 CREATED`
    
    + **Content example** :


             {
               "id": 55,
               "name": "tasks"
             }


+ **Error response** :

    + **Condition** :  If column not valid
    
    + **Code** : `400 Bad request`
    
    + **Content** :
    
    
            {
                "status": "BAD_REQUEST",
                "code": 400,
                "time": "2020-08-04 11:20:05"
            }
