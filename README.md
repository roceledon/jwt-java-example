# # jwt-java-example
Example of implementation of jwt with java spring boot.

## API
### Auth

Authenticate user and password, get a token.

> **Path:** /v1/login/auth

> **Method:** POST

> **Produces:** json/application

> **Body:** 
	
	{
		"username": "test",
		"password":"test"
	}


### Verify

Check if the token is valid

> **Path:** /v1/dividend/status

> **Method:** POST

> **Produces:** json/application


### Refresh

Refresh a token before expiring time.

> **Path:** /v1/login/refresh

> **Method:** GET

> **Produces:** json/application

> **Head:** Authorization: token


### Status Example

A example API to test.

> **Path:** /v1/dividend/status

> **Method:** GET

> **Produces:** json/application

> **Head:** Authorization: token




## Configure
You can change the parameters in application.properties

> \src\main\resources\application

	
	# Path properties  
	server.port = 8080  
	server.servlet.contextPath=/v1  
	debug=true  
	  
	# JWT  
	jwt.header=Authorization  
	jwt.secret=mySecret  
	jwt.expiration=604800  
	  
	# routes  
	route.authentication.prefix=/login  
	route.authentication.path=/auth  
	route.authentication.refresh=/refresh  
	route.authentication.verify=/verify  
	route.path.dividend=/dividend  
	route.path.status=/status  
	  
	# User  
	user.username=test  
	user.password=test  
	  
	# Log  
	logging.level.cl.bennu.security=DEBUG  
	logging.level.cl.bennu.mapper=TRACE  
	logging.level.cl.bennu.root=WARN
