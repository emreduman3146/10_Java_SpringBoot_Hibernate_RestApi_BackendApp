package com.webDeveloperEmre.javafullStackwebApp.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

import static com.webDeveloperEmre.javafullStackwebApp.model.User.getDatabaseAndTableNames;

public class ReusableObjects
{
    //USING PERSISTENCE.XML FILE TO GET DATABASE_METADATA
    protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit-name");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    //USING CLASSES/METHOD IN SPRING HIBRNATE LIBRARY TO GET DATABASE-TABLE NAME
    public static HashMap<String,String> tableName_databaseName =getDatabaseAndTableNames(User.class, ReusableObjects.entityManager);

}
