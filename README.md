# Банковский аккаунт
## Принцип работы
### Вход и регистрация
Чтобы зарегестрироваться необходимо ввести свои данные в формате json по эндпоинту:
```
/api/v1/auth/register

{
"username": "kirill13",
"firstName": kirill,
"lastName": zubkov,
"password": "kirill",
"balance": 1000,
"email": kirill@mail.ru,
}
```
Чтобы войти необходимо ввести имя пользователя и пароль:

/api/v1/auth/login

{
"username": "kirill",
"password": "kirill"
}

В ответ придёт json web token с которым можно будет выполнять все операции со счетом.

### Снятие, перевод и пополнение счета

Снятие денег:
```
/api/v1/users/take
{
"id": "1",
"balance": "100"
}
```
Пополнение:
```
/api/v1/users/add
{
"id": "1",
"balance": "100"
}
```
Перевод:
```
/api/v1/users/transfer
{
"id1": "1",
"id2": "2",
"balance": "100"
}
```
