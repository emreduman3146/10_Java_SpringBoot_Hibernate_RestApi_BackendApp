package com.webDeveloperEmre.javafullStackwebApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/*
The @ControllerAdvice annotation is used to indicate that this class is a global exception handler for all controllers.
It allows you to write a common code for handling exceptions across all controllers in a single class.
 */
@ControllerAdvice
public class ExceptionAdvice
{


    @ResponseBody  //The @ResponseBody annotation is used to indicate that the return value of the method (return errorMap;)should be written directly to the HTTP response body as JSON.
    @ExceptionHandler(UserNotFoundException.class)//Herhangi bir Spring Controller classinda UserNotFoundException throw edilirse bu method calissin demektir
    @ResponseStatus(HttpStatus.NOT_FOUND)//The @ResponseStatus(HttpStatus.NOT_FOUND) annotation is used to set the HTTP status code for the response to 404 Not Found.
    public Map<String, String> userNotFoundExceptionHandler(UserNotFoundException exception) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }



    @ResponseBody
    @ExceptionHandler(RequestBodyIsNotInFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    public Map<String, String> requestBodyIsNotInFormatExceptionHandler(RequestBodyIsNotInFormatException exception) {

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }

}