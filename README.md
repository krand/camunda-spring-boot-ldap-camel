To launch infrastructure (mariadb/ldap/ldap admin):
```
cd ./tools/docker
docker-compose up
```

To launch camunda platform:
```
mvn spring-boot:run
```
http://localhost:8080/, user cred: test:test



TODO

- try camel process

PROBLEM

- to allow create user groups and store them in DB, custom WritableIdentityProvider on base of LdapIdentityProviderSession should be written
