i make class InfixToPostfixHandler to convert infix to postfix expression and you can call a controller to hit post api 

success request response are following
request:
{
"equation":"4*(5-(7+2))+"
}
response:

{
"postfix": "4 5 7 2 + - *",
"result": -16
}

failure request response are following
request:
{
"equation":"4*(5-(7+2))+"
}
response:
{
"status": "BAD_REQUEST",
"errorMessage": "Not enough operands",
"timestamp": "2022-02-19T19:40:46.367"
}


Also written a unit test cases for controller and service class you can find in test folder

postman file in also added
.