<h1 align="center">Corporate Booking Application</h1>

<p align="center">A Corporate Booking Application Project for Singapore Sports School that allows employees to book trips to different attractions.</p>
 
## Getting Started
 
- Install [NodeJS](https://nodejs.org/en/) before installing frontend project
- Install [Maven](https://maven.apache.org/)  before running backend project


## Backend 
### Run the application
1. Run database.sql in your preferred MySQL database system
2. Run the springboot application
```
mvn spring-boot:run
```
3. Run data.sql

<br>
This application is preset with 1 master admin, please use the following credentials to login

**Username**: master admin

**Password**: admin123

For regular users, they are required to register for an account first


## Frontend 
1. Download and unzip project
2. Run the following command on CMD in frontend project directory (group-project-g1t6\frontend\vue-app)
```
npm install --force
npm run serve
```


## Frameworks and Libraries
### Backend 
- Java Standard Libraries 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)


### Frontend
- [Vue.js](https://vuejs.org/)
- [ElementUI](https://element.eleme.io/#/en-US)


## Functionalities
### Employee
- **Register** - Register for an account
- **View Loans** - View all loans created
- **Create Loan** - Create a loan on a date to a chosen attraction.

 
### Admin
- **Create and Disable Memberships** - Create and Disable memberships
- **View all loans** - View all loans and mark them as loan created, collected or cancelled
- **Corporate Passes** - Create corporate passes for places of interest or mark them as lost.
- **View all Employees** - View all employees, make them admins or disable their accounts.
- **Configure maximum loans** - Configure maximum loans allowed per month.
