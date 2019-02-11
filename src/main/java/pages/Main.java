package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class Main {
    private By signInAndProfileButton1 = By.cssSelector("a.header-topline__user-link.link-dashed");
    private By signInAndProfileButton2 = By.name("signin");
    private By logInfield1 = By.id("auth_email");
    private By logInfield2 = By.name("login");
    private By passwordField1 = By.id("auth_pass");
    private By passwordField2 = By.name("password");
    private By submitLogin1 = By.cssSelector("button.auth-modal__login-button > span.button-inner");
    private By submitLogin2 = By.name("auth_submit");
    private By userPanel = By.cssSelector("div#user_menu");
    private By registrationUpButton = By.cssSelector("div.auth-f-signup");
    private By profileButton1 = By.cssSelector("a[href*='personal-information']");
    private By profileButton2 = By.name("profile");
    private By personalInformation = By.name("personal-information");


    private void signInClick(){
        if($(signInAndProfileButton1).isDisplayed()) { $(signInAndProfileButton1).click(); }
        else { $(signInAndProfileButton2).click(); }
    }

    private void enterLogin(String user){
        if($(logInfield1).isDisplayed()) { $(logInfield1).sendKeys(user); }
        else { $(logInfield2).sendKeys(user);}
    }

    private void enterPassword(String password) {
        if($(passwordField1).isDisplayed()) { $(passwordField1).sendKeys(password); }
        else { $(passwordField2).sendKeys(password);}
    }

    private void submitLogin(){
        if($(submitLogin1).isDisplayed()) { $(submitLogin1).click(); }
        else { $(submitLogin2).click(); }
    }

    private void checkOfAuthorization(String username){
        if ($(signInAndProfileButton1).isDisplayed()){
            $(signInAndProfileButton1).shouldHave(Condition.text(username)); }
        else{$(userPanel).isDisplayed();} // if we login user to signInAndProfileButton2 selector
    }

    public void gotoProfile(){
        if ($(signInAndProfileButton1).isDisplayed()) {
            $(signInAndProfileButton1).click();
            $(profileButton1).click();}
        else { $(profileButton2).click();}
    }

    public void authorizationOfGenaTest (){
        signInClick();
        enterLogin("genarozetka@meta.ua");
        enterPassword("Pass12345");
        submitLogin();
        //checkOfAuthorization("Test Gena");
    }

    public void clickRegistrationUpButton(){
        $(registrationUpButton).click();
    }

    public void assertMainPage(){
        Assert.assertTrue(title().contains("Интернет-магазин ROZETKA™: фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие"));
    }

    public void registration(){
        assertMainPage();
        signInClick();
        $(registrationUpButton).click();
    }


}
