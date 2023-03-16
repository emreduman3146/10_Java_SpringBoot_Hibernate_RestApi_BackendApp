package com.webDeveloperEmre.javafullStackwebApp.bootstrap;

import com.webDeveloperEmre.javafullStackwebApp.model.User;
import com.webDeveloperEmre.javafullStackwebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//When the application starts up, Spring will automatically detect and initialize any beans
// that are annotated with @Component

//run methodu otomatik ilk olarak calisicak. @BeforeSuite gibi dusunebiliriz

//BIREBIR H2 ISIMLI DB'YE CRUD YAPAN CLASSTIR(Yazdigimiz kodlar o yonde oldugu icin diyorum)
//BACKEND ILE DB BAGLANTISINI KURAR
//PEKI NEDEN H2 DATABASE'I KULLANIYORUZ DA MARIADB, MYSQL, ORACLESQL KULLANMIYORUZ
//SEBEBI->SPRING OGRENMEK ISTEYEN JAVA BILEN BIR OGRENCI KALICI ISLEMLER DEGIL DE
//SADECE ANLIK DB ISLEMLERI YAPMAK ISTER DIYE SPRING-HIBERNATE  BIZE H2 DB'si hizmeti sunar.
//INTELLIJ'DE YESIL OKA BASINCA DB OLUSUR
//KIRMIZI KARAYE BASIP LOCAL SERVERIMIZDE(LOCALHOST) SPRING TOMCAT alt serverindeki h2 db'si silinir

//PEKI H2 DATABASE'ININ YAPILANDIRILMASINI NASIL YAPIYORUZ.
//application.properties file ina bazi ayarlari yaziyoruz


//NOTE
//BU CLASSTA YAPILAN ISLEMLER SADECE BACKEND-DATABASE ISLEMLERIDIR
//API  ISLEMRINE DAHA GIRMEDIK
//API ISLEMLERI ICIN UserController CLASSI KULLANILACAK

@Component//Bunu yazmazsak db sorgusunu h2 isimli db' ye eklemiyordu
public class UserBootstrap implements CommandLineRunner
{

    @Autowired
    UserRepository userRepository;

    //SPRING PROJESI RUN EDILIR EDILMEZ BURASI CALISIYOR
    //VE
    //DATABASE IMIZE 7 TANE KAYIT INSERT EDILIYOR HIBERNATE ILE
    @Override//CommandLineRunner interface'indeki abstract method
    public void run(String... args) {

       // User user1 = new User("John Doe");
       // user1.setEmail("John@gmail.com");
       // userRepository.save(user1);

        //@Builder ile asagidaki hazir methodlari kullanip kayit ekleyebiliriz
        userRepository.save(User.builder().username("emreduman").name("emre").email("duman@gmail.com").build());
        //userRepository.save(User.builder().username("emreduman2").build());

    }
}
