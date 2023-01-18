### Project original source:
https://guides.micronaut.io/latest/micronaut-security-jwt-gradle-java.html

## Steps to reproduce:

**Step 1:** 

Create `War` file using `gradlew war`

**Step 2:**

Deploy war file in tomcat and start tomcat

**Step 3:**

Execute below curl command
```shell
curl --location --request POST 'http://localhost:8080/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"username":"sherlock",
"password":"password"
}'
```

**Step 4**

Copy token value(access_token) received in login response 
```json
{
"username": "sherlock",
"access_token": "",
"refresh_token": "",
"token_type": "Bearer",
"expires_in": 3600
}
```

**Step 5**

Replace [token received in login request] from **token value** in below command and execute 
```shell
curl --location --request GET 'http://localhost:8080' \
--header 'Authorization: Bearer [token received in login request]'
```
