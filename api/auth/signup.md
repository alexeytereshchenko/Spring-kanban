# Signup:

Create an Account

+ **URL** : `/auth/signup`

+ **Auth required** : **NO**

+ **Token life** : `30 minutes`

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

  + **Condition** : If everything is OK and an Account didn't exist for this User

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

  + **Condition** :  If Account already exists.

  + **Code** : `401 UNAUTHORIZED`

  + **Content** :


            {
              "time": 0,
              "code": 401,
              "message": "User `username` already exists!"
            }
