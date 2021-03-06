=========

## REST Example Project with Spring Security

### Use the REST Service

#### API Authentication
```
curl -v -X POST -d ashim:ashim http://localhost:8080/spring-rest-security/login
```
#### Call API
```
curl http://localhost:8080/spring-rest-security/users -H "Cookie:JSESSIONID=0234A1D6C"
```
### Maven Command
#### Generate War File:
```
mvn clean install
```
#### Unit Test:
```
mvn clean test -P unit-test
```
#### Integration Test:
```
mvn clean verify -P integration-test
```
### Liquibase Command

#### Apply all database changes:
```
mvn liquibase:update
```
#### Generate SQL script for applying all database changes:
```
mvn liquibase:updateSQL
```
#### Generate change log from database:
```
mvn liquibase:generateChangeLog
```
#### Generate log from JPA Entity:
```
mvn liquibase:diff
```
#### Rollback:
```
mvn -Dliquibase.rollbackCount=1000 liquibase:rollback
mvn -Dliquibase.rollbackCount=1000 liquibase:rollback -P integration-test
```
