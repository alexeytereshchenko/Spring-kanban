# Signup:

Create an Account

+ **URL** : `/auth/signup`

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
    
    + **Condition** : If everything is OK and an Account didn't exist for this User
    
    + **Code** : `201 CREATED`
    
    + **Content example** :
    
        
            {
                "username": "alex",
                "access_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4In0.kiwWuia3pvp11_u8ob3Nf5myJ8cxonSpPQXgBMb-P3I",
                "type": "Bearer "
            }


+ **Error response** :

    + **Condition** :  If Account already exists for User.
    
    + **Code** : `401 UNAUTHORIZED`
