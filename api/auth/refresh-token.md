# Refresh token:

Get new tokens

+ **URL** : `/auth/refresh`

+ **Token life** : `30 minutes`

+ **Auth required**: **NO**

+ **Method** : `POST`

+ **Data constraints** :


        {
            "token": "[unicode 255 chars max]"
        }

+ **Successful response** :

  + **Condition** : If everything is OK

  + **Code** : `200 OK`

  + **Content example** :


    {
      "username": "string",
      "type": "string",
      "access_token": "string",
      "refresh_token": "string",
      "expire_time": 0
    }        

+ **Error response** :

  + **Condition** : If token is expired.

  + **Code** : `401 UNAUTHORIZED`

  + **Content** :


    {
      "time": 0,
      "code": 401,
      "message": "Incorrect refresh token"
    }
