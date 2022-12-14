# File System REST API

[![Build Status](https://app.travis-ci.com/maksimt58/FileSystem-REST-api.svg?branch=master)](https://app.travis-ci.com/maksimt58/FileSystem-REST-api)

## Project Information
Приложение (REST API), которое позволяет хранить историю работы с файлами.  

Сущности приложения:  
- User  
- File  
- Event  

В качестве хранилища данных используется база данных MySQL.

Пользователь через Postman имеет возможность создания, получения, редактирования и удаления данных.

Слои:  

- model - POJO классы  
- repository - классы, реализующие доступ к БД  
- service - классы, для обработки запросов контроллера и вызова методов БД  
- controller - сервлеты для обработки запросов GET, POST, PUT, DELETE  

Требования:
- Все CRUD операции для каждой из сущностей  
- Придерживаться подхода MVC  
- Для сборки проекта использовать Maven  
- Для взаимодействия с БД - Hibernate  
- Для конфигурирования Hibernate - аннотации  
- нициализация БД должна быть реализована с помощью flyway  
- Взаимодействие с пользователем необходимо реализовать с помощью Postman (https://www.getpostman.com/)  
- Репозиторий должен иметь бейдж сборки travis (https://travis-ci.com/)  
- Рабочее приложение должно быть развернуто на heroku (https://www.heroku.com/)  

Технологии: 
Java, MySQL, Hibernate, HTTP, Servlets, Maven, Flyway  
