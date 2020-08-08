# Get all columns

+ **URL** : `/boards/:boardId/columns`

+ **URL Parameters** : `boardId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `Get`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 OK`
    
    + **Content example** :

        
            [
                {
                    "id": 55,
                    "name": "column-1"
                },
                {
                    "id": 56,
                    "name": "column-2"
                },
                {
                    "id": 57,
                    "name": "column-3"
                }
            ]
