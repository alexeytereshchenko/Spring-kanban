# Delete a column

+ **URL** : `/boards/:boardId/columns/columnId`

+ **URL Parameters** : `boardId=[integer]` `columnId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `DELETE`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 Ok`

+ **Error response**

    + **Condition** :  If column not exist
    
    + **Code** : `404 NOT FOUND`
    
    + **Content** :
    
    
            {
                "status": "NOT_FOUND",
                "code": 404,
                "time": "2020-08-04 11:14:22"
            }
