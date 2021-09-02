# Login:

Login into an Account

+ **URL** : `/auth/login`

+ **Token life** : `30 minutes`

+ **Auth required**: **NO**

+ **Method** : `POST`

+ **Data constraints** :


        {
            "username": "[unicode 255 chars max]",
            "password": "[unicode 255 chars max]"
        }


+ **Data example** All fields must be sent


        {
            "username": "admin",
            "password": "admin"
        }    

+ **Successful response** :

  + **Condition** : If everything is OK

  + **Code** : `201 CREATED`

  + **Content example** :


            {
              "username": "string",
              "type": "string",
              "access_token": "string",
              "refresh_token": "string",
              "expire_time": 0
            }        

+ **Error response** :

  + **Condition** : If 'username' or 'password' is wrong.

  + **Code** : `401 UNAUTHORIZED`

  + **Content** :


            {
              "time": 0,
              "code": 401,
              "message": "Bad credentials"
            }
