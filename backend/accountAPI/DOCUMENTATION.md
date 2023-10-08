## Account API Documentation
This describes the usage of the Account API

### Create Account
URL: v1/account/create

JSON BODY INPUT:{
                "email": {email},
                "firstName": {firstName},
                "lastName": {lastName},
                "password": {password},
                "address": {address},
                "phone": {phone},
                "isNotified": {isNotified}
                }
JSON BODY INPUT Example:{
                        "email": "testemail@example.com",
                        "firstName": "Jane",
                        "lastName": "Doe",
                        "password": "password123",
                        "address": "123 Main St",
                        "phone": "0000-000-000",
                        "isNotified":true
                        }

OUTPUT: {status code}
OUTPUT Example: 201 Created

### Get Account
URL: v1/account/{email}/{password}
URL Example: v1/account/testemail@example.com/password123

JSON BODY OUTPUT:{
                "email": {email},
                "firstName": {firstName},
                "lastName": {lastName},
                "password": {password},
                "address": {address},
                "phone": {phone},
                "isNotified": {isNotified}
                }
JSON BODY OUTPUT Example:{
                        "email": "testemail@example.com",
                        "firstName": "Jane",
                        "lastName": "Doe",
                        "password": "password123",
                        "address": "123 Main St",
                        "phone": "0000-000-000",
                        "isNotified":true
                        }

### Update Account
URL: v1/account/update/{email}/{password}
URL Example: v1/account/update/testemail@example.com/password123

JSON BODY INPUT:{
                "email": {email},
                "firstName": {firstName},
                "lastName": {lastName},
                "password": {password},
                "address": {address},
                "phone": {phone},
                "isNotified": {isNotified}
                }
JSON BODY INPUT Example:{
                        "email": "testemail@example.com",
                        "firstName": "Jane",
                        "lastName": "Doe",
                        "password": "password123",
                        "address": "123 Main St",
                        "phone": "0000-000-000",
                        "isNotified":true
                        }

OUTPUT: {status code}
OUTPUT Example: 202 Accepted

### Delete Account
URL: v1/account/delete/{email}/{password}
URL Example: v1/account/delete/testemail@example.com/password123

OUTPUT: {status code}
OUTPUT Example: 200 OK
