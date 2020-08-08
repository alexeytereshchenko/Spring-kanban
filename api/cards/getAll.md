# Get all cards

+ **URL** : `/boards/:boardId/columns/:columnId/cards`

+ **URL Parameters** : `boardId=[integer]` `columnId=[integer]`

+ **Auth required**: **YES**

+ **Method** : `Get`

+ **Successful response** :
    
    + **Condition** : If everything is OK
    
    + **Code** : `200 OK`
    
    + **Content example** :
    
        
            [
                {
                    "id": 55,
                    "name": "card-1"
                },
                {
                    "id": 56,
                    "name": "card-2"
                },
                {
                    "id": 57,
                    "name": "card-3"
                }
            ]
