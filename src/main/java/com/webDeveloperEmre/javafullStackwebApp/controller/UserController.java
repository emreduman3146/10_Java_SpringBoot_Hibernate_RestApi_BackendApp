package com.webDeveloperEmre.javafullStackwebApp.controller;

import com.webDeveloperEmre.javafullStackwebApp.exceptions.RequestBodyIsNotInFormatException;
import com.webDeveloperEmre.javafullStackwebApp.exceptions.UserNotFoundException;
import com.webDeveloperEmre.javafullStackwebApp.model.User;
import com.webDeveloperEmre.javafullStackwebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.lang.String.format;
import static com.webDeveloperEmre.javafullStackwebApp.model.ReusableObjects.*;

@RestController  //used to define a RESTful web service. It's a combination of @Controller and @ResponseBody, and it's used to simplify the creation of RESTful web services in Spring.
@CrossOrigin("http://localhost:3000")//react app URL
@RequestMapping("/userManagement")//BURADA BASE URL BELIRLENIR
public class UserController {

    /*
    annotation that simplifies dependency injection in Java Spring, and it allows you to easily inject dependencies into your classes without having to manually create instances of them.
     */
    @Autowired
    private UserRepository userRepository;



    //@GetMapping("")                //localhost:8080/userManagement
    @GetMapping("/getAllUsers")      //localhost:8080/userManagement/getAllUsers
    public List<User> getAllUsers()
    {
        //SELECT * FROM USER;
        return  userRepository.findAll();
        //RESTAPI get request yapar -> JAVA -> SQL Query -> JAVA -> JSON DATA
    }


    //BIZIM ENDPOINTIMIZE GET REQUEST YAPILDIGI AN
    //ASAGIDAKI METHOD CALISIR
    //DB'DEKI VERILERI ALIR  SQL->JAVA OBJECT->JSON DATA ISLEMLERINI YAPAR
    //  pathParam-> 1
    @GetMapping("/getUser/{id}")//localhost:8080/userManagement/1
    public User getUserById(@PathVariable Long id)
    {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        //SELECT * FROM table_name WHERE id = 4; ELSE throw exception

    }

    @PostMapping("/postUser")
    private List<Object> addAUser(@RequestBody User user)
    {
        userRepository.save(user);//INSERT INTO user (id,name, username, email) VALUES (19,'John Doe', 'johndoe', 'johndoe@example.com');

        List<Object> requestBody_Message=new ArrayList<>();
        requestBody_Message.add(userRepository.findById(user.getId()));
        requestBody_Message.add(format("A new User are posted/inserted with id:%d successfully into the %s table in the %s DB!!!",user.getId(),tableName_databaseName.get("tableName"),tableName_databaseName.get("databaseName")));
        return requestBody_Message;
    }

    //EGER PATHPARAM(ID) VARSA VE REQUEST BODY TUM FIELDLARI ICERIYORSA RECORDU GUNCELLE
    //EGER PATHPARAM(ID) YOKSA VE REQUEST BODY TUM FIELDLARI ICERIYORSA YENI BIR RECORD OLUSTUR

    //EGER PATHPARAM(ID) VARSA VE REQUEST BODY TUM FIELDLARI ICERMIYORSA 404 HATASI VER.
    //EGER PATHPARAM(ID) YOKSA VE REQUEST BODY TUM FIELDLARI ICERMIYORSA 404 HATASI VER.
    @PutMapping(value="/updateUser/{id}", consumes = "application/json" )
    private User updateUser(@RequestBody User newUser,@PathVariable Long id)
    {
        Optional.of(newUser)
        .filter(user -> user.getName() != null && user.getUsername() != null && user.getEmail() != null)
        .orElseThrow(() -> new RequestBodyIsNotInFormatException(newUser.createUserObjectWithoutId()));

        return userRepository.findById(id).
                map(user->
                    {
                        user.setName(newUser.getName());
                        user.setUsername(newUser.getUsername());
                        user.setEmail(newUser.getEmail());
                        return userRepository.save(user);
                    }).orElseGet(() -> {
                        newUser.setId(id);
                        return userRepository.save(newUser);
                    });

        //.orElseThrow(()->new UserNotFoundException(id));
    }


    @DeleteMapping(value = "/deleteUser/{id}")
    private String deleteUserByID(@PathVariable Long id)
    {
        //if(!userRepository.existsById(id))
        //    throw new UserNotFoundException(id);

        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException(id));

        userRepository.deleteById(id);

        //response body icersinde doner
        return format("A User record with id:%d has been deleted successfully in the %s table in the %s DB!!!",id,tableName_databaseName.get("tableName"),tableName_databaseName.get("databaseName"));
    }

    @DeleteMapping(value = "/deleteAllUsers")
    private String deleteAllUsers()
    {

        userRepository.deleteAll();//DELETE FROM table_name;

        //response body icersinde doner
        return format("All record in the %s table in the %s DB were deleted!!! ",tableName_databaseName.get("tableName"),tableName_databaseName.get("databaseName"));
    }

}

