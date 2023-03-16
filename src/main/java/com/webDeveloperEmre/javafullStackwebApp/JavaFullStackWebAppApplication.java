package com.webDeveloperEmre.javafullStackwebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication      //This annotation is used to annotate the main class of a Spring Boot application
							// It combines three other annotations: "@Configuration", "@EnableAutoConfiguration", and "@ComponentScan".
public class JavaFullStackWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaFullStackWebAppApplication.class, args);
	}

}

/*
TOMCAT EMBEDDED WEBSERVER IN LOCAL

The "main" keyword in the log message refers to the main thread of the Java Spring application
that started the Tomcat web server.
The log message is indicating that the Tomcat web server has successfully started
and is ready to serve incoming HTTP requests on port 8080.
 */