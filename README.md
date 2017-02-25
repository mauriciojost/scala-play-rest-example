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
curl -X POST -H Content-Type=application/x-www-form-urlencoded http://localhost:9000/dev/0/target -d "c2p0=0"
```
