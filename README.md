# Spring-kanban
API for kanban board

https://kanban-backend.pythontop.xyz/swagger

## Try it with docker-compose

    sudo docker-compose up
     
    localhost:9999/api

    localhost:9999/swagger

## Open Endpoints:

* [Login](/api/auth/login.md)  : `POST /auth/login`

* [Signup](/api/auth/signup.md) : `POST /auth/signup`

* [Refresh Token](/api/auth/refresh-token.md) : `POST /auth/refresh`

* Swagger : `GET /swagger`

## Close Endpoints:

#### Boards endpoints

* [Get all boards](/api/boards/getAll.md) : `GET /boards`

* [Get a boards](/api/boards/get.md) : `GET /boards/:boardId`

* [Create a boards](/api/boards/post.md) : `POST /boards`

* [Update a boards](/api/boards/put.md) : `PUT /boards/:boardId`

* [Delete a boards](/api/boards/delete.md) : `DELETE /boards/:boardId`

#### Columns endpoints

* [Get all columns](/api/columns/getAll.md) : `GET /boards/:boardId/columns`

* [Get a columns](/api/columns/get.md) : `GET /boards/:boardId/columns/:columnId`

* [Create a columns](/api/columns/post.md) : `POST /boards/:boardId/columns`

* [Update a columns](/api/columns/put.md) : `PUT /boards/:boardId/columns/:columnsId`

* [Delete a columns](/api/columns/delete.md) : `DELETE /boards/:boardId/columns/:columnsId`

#### Cards endpoints

* [Get all cards](/api/cards/getAll.md) : `GET /boards/:boardId/columns/:columnId/cards`

* [Get a cards](/api/cards/get.md) : `GET /boards/:boardId/columns/:columnId/cards/:cardId`

* [Create a cards](/api/cards/post.md) : `POST /boards/:boardId/columns/:columnId/cards`

* [Update a cards](/api/cards/put.md) : `PUT /boards/:boardId/columns/:columnId/cards/:cardId`

* [Delete a cards](/api/cards/delete.md) : `DELETE /boards/:boardId/columns/:columnId/cards/:cardId`

#### Error response


    {
        "time": [unixtime],
        "code": [integer],
        "message": [description of error]
    }
