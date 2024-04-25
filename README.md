#  Рабочее веб-приложение с базовой аутентификацией и авторизацией на основе Spring Security и JWT, сопровождаемое модульными тестами и краткой документацией к API.

Запуск
 ```shell
docker-compose up --build
```

* БД Postgress -> таблица users.
* Примеры запросов:
 
Регистрация 
```shell
curl -i -X POST http://127.0.0.1:8080/api/auth/register -H 'Content-Type: application/json' -d '{"username":"name","email":"name@mail.ru","password":"222324"}'
```
Авторизация
```shell
curl -i -X POST http://127.0.0.1:8080/api/auth/login -H 'Content-Type: application/json' -d '{"username":"name" , "password":"222324"}'
```
* Далее уже авторизованым пользователям
С ролью user
```shell
curl -i -X GET http://localhost:8080/user
```
С ролью admin
 ```shell
curl -i -X GET http://localhost:8080/admin
``` 
С ролью admin или user
```shell
curl -i -X GET http://localhost:8080/example
```
