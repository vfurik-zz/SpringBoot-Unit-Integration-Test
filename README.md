# General information
SpringBoot Unit/Integration tests.

Example of Unit/Integration tests for:
- JPA lvl
- Service lvl
- MVC lvl

# Launching tests, app
For running test:
```bash
    ./gradlew clean test 
 ```
 
 For running app:
 ```bash
     ./gradlew clean bootRun 
  ```
  
  Available endpoints(http://localhost:8080):
   - GET /message
   - GET|DELETE|PUT /message/{id}
   - POST /message