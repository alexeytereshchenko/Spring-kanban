# Delete a board

+ **URL** : `/boards/:boardId`

+ **URL Parameters** : `boardId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `DELETE`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 Ok`

+ **Error response**

    + **Condition** :  If board not exist
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }
