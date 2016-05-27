=========

## REST Example Project with Spring Security

### Use the REST Service
```
#### Authentication
curl -v -X POST -d ashim:ashim http://localhost:8080/spring-rest-security/login

#### Call API
curl http://localhost:8080/spring-rest-security/users -H "Cookie:JSESSIONID=0234A1D6C55AD3DBF516BF7BD622388F"

```
