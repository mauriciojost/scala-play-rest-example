# Play Framework RESTful API written in Scala

## Start the application

To start the application:

```
./activator run
```

And then:

```
http://localhost:9000
```

To use the REST API:

```
curl -X POST http://localhost:9000/users -H "Content-Type: application/json" -d "{\"email\": \"test@test.com\"}"
curl -X GET http://localhost:9000/users/1 
```
	
## Test validation rules

A malformed email

```
    curl -X POST http://localhost:9000/users -H "Content-Type: application/json" -d "{\"email\": \"test\"}"
```

An email that already exists for a user

```
    curl -X POST http://localhost:9000/users -H "Content-Type: application/json" -d "{\"email\": \"test@test.com\"}"
    curl -X POST http://localhost:9000/users -H "Content-Type: application/json" -d "{\"email\": \"test@test.com\"}"
```
