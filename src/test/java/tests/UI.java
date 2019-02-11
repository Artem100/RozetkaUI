package tests;

import com.codeborne.selenide.Configuration;
import com.sun.org.glassfish.gmbal.Description;
import dataProvider.DataProviderClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Main;
import pages.PersonalAccount;
import pages.Registration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class UI {

    private static Main main;
    private static Registration registration;
    private static PersonalAccount personalAccount;

    @BeforeMethod
    public void setup (){
        main = new Main();
        registration = new Registration();
        personalAccount = new PersonalAccount();
        Configuration.browser="chrome";
        Configuration.timeout = 4000;
        open("https://rozetka.com.ua/ua/");
    }

    @Description("Positive registration")
    @Test
    public void firstTest(){
       main.registration();
       registration.positiveRegistration();
       personalAccount.clickLogout();
    }

    @Description("Checking the response of required fields in registration")
    @Test
    public void secondTest(){
        main.registration();
        registration.checkRequiredFields();
    }

    @Description("Checking the response of the password field for invalid data in registration")
    @Test(dataProvider = "invalidPasswords", dataProviderClass = DataProviderClass.class)
    public void thirdTest(String name, String login, String password){
        main.registration();
        registration.checkRequiredPasswordField(name,login,password);
    }

    @Description("Change birthday in profile")
    @Test
    public void fourthTest() throws InterruptedException{
        main.authorizationOfGenaTest();
        main.gotoProfile();
        personalAccount.generateDayAndYear();
        Thread.sleep(10000);
        //Придумать как проверять введенное значение дня рождения
    }


    @Description("Add music hobby")
    @Test
    public void addMusicHobby() throws InterruptedException{
        main.authorizationOfGenaTest();
        main.gotoProfile();
        personalAccount.addMusicHobby();
        Thread.sleep(10000);
    }

    @Description("Delete the music hobby from personal data")
    @Test
    public void deleteMisucHobby() throws InterruptedException{
        main.authorizationOfGenaTest();
        Thread.sleep(10000);
        main.gotoProfile();
        personalAccount.deleteMusicHobby();

    }

    @AfterMethod
    public void closeBrowser(){
        //close();
    }




}
