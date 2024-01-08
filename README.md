# Rest Api For Student Management System

## Features
### ADMIN :
. Register & Login <br>
. Admit Students <br>
. Add Courses <br>
. Assign Courses To Students <br>
. Check Students In Particular Course <br>
. Search Student <br>

### STUDENT :
. Register & Login <br>
. Update Profile
. Leave Course

&nbsp;
### APIS FOR ADMIN
#### Register Admin 
`POST`
```
http://localhost:8080/app/admin/register

```
#### Login Admin
`POST`
```
http://localhost:8080/app/admin/signIn

```
#### Add Course
`POST`
```
http://localhost:8080/app/admin/course

```
#### Admit Student
`POST`
```
http://localhost:8080/app/admin/admit/{studentCode}

```
#### Assign Course 
`POST`
```
http://localhost:8080/app/admin/course/{courseId}/{studentCode}

```
#### Search Student By Course
`GET`
```
http://localhost:8080/app/admin/course/{courseId}

```
#### Search Student By Name
`GET`
```
http://localhost:8080/app/admin/students/{name}

```

&nbsp;
### APIS FOR STUDENT
#### Register Student 
`POST`
```
http://localhost:8080/app/student/register

```
#### Login Student
`POST`
```
http://localhost:8080/app/student/signIn

```
#### Update Student Profile
`PUT`
```
http://localhost:8080/app/student/update

```
#### Get Student Courses
`GET`
```
http://localhost:8080/app/student/course

```
#### Leave Course
`DELETE`
```
http://localhost:8080/app/student/leave/{courseId}

```







