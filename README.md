# quick-and-dirty-spring-boot-jwt

Provides an very simple Spring-Boot application that protects its api using JWTs.

Run: `./gradlew bootRun`

Application listens on `localhost:8080`

JWTs expire after 30 seconds.

Credentials to obtain JWT: 

      username: letme
      password: in


There are three endpoints configured:

1. `/token-auth` - Returns JWT for valid credentials

        curl  localhost:8080/api/token-auth -H "Content-Type: application/json" -d '{"username":"letme","password":"in"}'
      
        ----> {"token":"eyJhbGciOiJIUzUxMiJ9......"}

2. `/token-refresh` - refreshes a given JWT

        curl  localhost:8080/api/token-refresh -H "Content-Type: application/json" -d '{"token":"eyJhbGciOiJIUzUxMiJ9......"}'
        
        ----> {"token":"eyJhbGciOiJIUzUxMiJ9......"}
        
3. `/secured` - A protected resource that is accessible if a request contains a valid token.

        curl  localhost:8080/api/secret-stuff -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9......"
        
        ----> I like trains


This example does not contain Spring Security. It only focusses on how JWTs are generated and veryfied.
