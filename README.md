# Exam Analysis App
### Introduction
##### The aim of the project is a desktop application that shows the information and statistics of students and exams. 
#
##### --- You can add exams and students to the system using the REST Services or the Swing interface.
##### --- The students registered in the system are listed in the table. This list is updated as student registration is added to the system.
##### --- Student details can be accessed by clicking on any student from the table. On the student detail page, there are general information about the student and the exam information the student has attended.
##### --- Exam can be assigned to any student.
##### --- Exam results of more than one student can be read from the file and registered in the system.
##### --- Reports can be created for students registered in the system. These files are produced separately for each student in Json format. Multi thread structure was used while generating reports.
##### --- The application.properties file contains information such as the paths to be read while the report is written, how many records will be kept on a page.
##### --- Exams can be added to the system and information such as how many people have taken these exams and the average scores of the exam can be seen.
##### --- In the exam details, general exam information and the information of the students taking this exam are shown.
### application.properties configuration
```sh
spring.datasource.url=jdbc:postgresql://{yourIpAddess}:5432/{yourDatabaseName}
spring.datasource.username={yourUsername}
spring.datasource.password={yourPassword}
```
### Systems Used
#### Backend: SpringBoot
#### Database: PostgreSql
#### UI: Swing
#### ORM: Hibernate

###Important Notes
######If you do not have lombok plugins you have to install it. If you use IntelliJ, you can file >> plugins searc lombok from marketplace and install it.
######If you want to build project you can use the command : 
```sh
$ mvn clean install -Djava.awt.headless=false
```

###Build Docker Image
```sh
$ docker build -t {your image name} .
```

###Run Docker Image
```sh
$ docker run -d --name yourContainerName -p 8080:8080 yourImageName
```
