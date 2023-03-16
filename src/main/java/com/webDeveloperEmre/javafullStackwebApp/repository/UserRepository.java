package com.webDeveloperEmre.javafullStackwebApp.repository;

import com.webDeveloperEmre.javafullStackwebApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>
{

 //User Model class-> Database Entity Class
 //Long data type  -> Primary key of this user table in mysql
 //JpaRepository Interface has lots of database query utility methods instead of writing SQL Queries!!!

}
