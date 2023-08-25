# Carsharing API REST in Spring Boot
This project is a REST API of Carsharing developed in Spring boot. Carsharing is a project about vehicle rental.
Provides functions like:
- Login, register and send email to confirm users.
- Documentation with Swagger.
- **Notifications:** Scheduled tasks to send an email when a reservation is almost finished.
- Authorization and Authentication with JWT using roles.
- Design Pattern DTO.
- Validated endpoints.
- Upload and get images.
- Vehicle rating system.

### Technologies used:
- Java JDK 17
- Spring boot 3.1
- Postgres
- Spring Data JPA
- Spring Security 6.1
- Json Web Token (JWT)
- Java Mail
- Swagger 2.2
- Quartz Scheduler
- Lombok

### Database Diagram:
![Carsharing Diagram drawio](https://github.com/JoseJulian25/Carsharing/assets/105135341/a02e3fef-d24d-4e04-84b8-f9be84facc0f)

## ⚠ IMPORTANT:
#### Before run the application, you must go to ```src/main/resources/application.properties``` and make this changes:
**Environment variables:**
- EMAIL_MAIL: Here you need to put your email which will send to users confirm the registration and notifications about the reservation.

- PASSWORD_MAIL: This is not your password account. It's your application password. Next i'll give you a tutorial link about how to create this application password.
  https://knowledge.workspace.google.com/kb/how-to-generate-an-app-passwords-000009237.
  After generate this one, you need to put it here.

- SECRET_KEY: This is a confidential string used to generate and verify JWT. To create this key you can go to this link: https://seanwasere.com/generate-random-hex/.
  If you don't want to create a environment variable on this, you will have to go to ```src/main/com.rd/jwt/JwtService.java```, eliminate the annotation ```@Value``` and set the key on the variable.

## Recommendations
The notifications about reservations work as follows:
- Every hour it finds reservations of the database.
- Every ten minutes it checks if reservations are almost finished (Lower than 2 hours), if they are true, it will send an email.

If you want to test it and put lower minutes to repeat the tasks, you will need to go to ```src/main/java/com.rd/email/ReservationManager.java```. Then you can see the constants ```EVERY_HOUR``` and ```EVERY_TEN_MINUTES```, the values of these are "cron".
In the next link you can generate a cron with your times preferences: https://crontab.cronhub.io/.

The Swagger Documentation is in this link: ```http://localhost:{Your port}/swagger-ui/index.html#/```. There you can test the API, see all controllers, responses and put the token in all the queries.

### PD: 
The endpoint about logout users doesn't appear in swagger documentation, but it exists. The request mapping is ```/api/auth/logout```. You can see it on ```src/main/java/com.rd/config/SecurityConfig.java```.



