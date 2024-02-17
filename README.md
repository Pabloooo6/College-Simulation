# College Simulation

It is a project done in the college that shows a backend simulation of the basic functionalities of a college.


## How to use it

You can try the functionalities of the project following these steps:

1. Download or clone the project.

2. Download _Postman_ [here](https://www.postman.com/downloads/).
   
3. Execute `GroupPabloApplication.java`.

4. Don't forget this application has authentification _token_, it will be explained later.

5. Try in _Postman_ to call some functions.



### Get the Authentification Token

1. Open _Postman_.

2. Do a _POST_ call with the next URL:

```
http://localhost:8080/authenticate
```

3. Select a role from one user by the _username_. You will found the users and roles in `src/main/resources/data.sql`. All the passwords are `passowrd123`.

4. In the body of the _Postman_ call go to the _Body_ part and write:

```
{
 "username": "admin",
 "password": "password123"
}
```

5. Copy the _token_ that the application sends you.



### Example of calling a GET function

1. Open _Postman_.
  
2. Select _GET_ in the type of call in _Postman.

3. Write this URL to get all the avaible courses:

```
http://localhost:8080/courses
```

4. Go to the _Postman_'s label of `Authoritzation`.

5. Select the _Type_ `Bearer Token`.

6. Copy the previous authentification _token_ and paste it there.

7. Send the call and see the results!
