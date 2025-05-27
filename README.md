# Description
## What was used and the thinking behind it
### Lombok
Lombok was used in this scenario to get rid of the repetitive boilerplate code such as the need for constructors and getters and setter methods. It could be implemented simply by annotations such as @AllArgsConstructor and @Data.

### Request and Response DTOs
Exposing all the internal working and details to the frontend or the createUserRequest might cause security risks. Also the chances of issues arising if the implementation in the frontend system is not the exact same as we expected. To help with safely maintaining communications, request and response dtos were used.

### Mapstruct
Mapping entities to DTOs and also writing additional code to handle edge cases of having to ignore a target parameter or handle it being null comes with unnecessary hassle and its own boilerplate code. Mapstruct was used to make these implementations efficient.

### MySQL driver
Since a MySQL database was used, the corresponding MySQL driver was necessary to ensure the communication between JDBC and the database worked.

### Hibernate
Hibernate is built on top of JDBC and helped create a database independent code.
