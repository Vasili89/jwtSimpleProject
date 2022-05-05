Простое Web приложение с применением Spring Security и Jwt Token auth

Старт приложения:

Конфигурация БД: Создаём базу данных myjwtapp spring.datasource.driver-class-name=org.postgresql.Driver spring.datasource.url=jdbc:postgresql://localhost:5432/myjwtapp

git clone https://github.com/Vasili89/jwtSimpleProject

в application.yaml заполняем поля spring.datasource.(username и password) для доступа к БД

cd jwtSimpleProject

mvn spring-boot:run

Регистрация пользователя: 
  метод POST
  localhost:8081/jwtapp/signup
  Content-Type: application/json
  {
    "login": "AnyLogin",
    "password": "anyPassword",
    "fullName": "Ivan Ivanov"
  }
  
Получение Jwt токена для зарегистрированных пользователей:
  метод POST
  localhost:8081/jwtapp/login
  Content-Type: application/json
  {
    "login": "AnyLogin",
    "password": "anyPassword"
  }
