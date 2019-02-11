package pages;

import com.codeborne.selenide.Condition;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class Registration {
    private By nameField = By.name("title");
    private By loginField = By.name("login");
    private By passwordField = By.name("password");
    private By signUpButton = By.className("btn-link-sign");
    private By errorNameField = By.cssSelector(".input-invalid[name='title']");
    private By errorLoginField = By.cssSelector(".input-invalid[name='login']");
    private By messageErrorPassword = By.name("password-hint");
    private String textPasswordError = "Пароль должен быть не менее 6 символов, содержать цифры и заглавные буквы и не должен совпадать с именем и эл.почтой";


    public void assertRegisrationPage(){
        Assert.assertTrue(title().contains("ROZETKA — Регистрация"));
    }

    public void positiveRegistration(){
        assertRegisrationPage();
        $(nameField).sendKeys(RandomStringUtils.randomAlphabetic(4));
        $(loginField).sendKeys(RandomStringUtils.randomAlphabetic(4)+"@mail.com");
        $(passwordField).sendKeys(RandomStringUtils.randomAlphanumeric(7));
        $(signUpButton).click();
    }


    public void checkRequiredPasswordField(String name, String login, String password){
        assertRegisrationPage();
        $(nameField).sendKeys(name);
        $(loginField).sendKeys(login);
        $(passwordField).sendKeys(password);
        $(signUpButton).click();
        $(messageErrorPassword).shouldBe(Condition.visible);
        $(messageErrorPassword).shouldHave(Condition.text(textPasswordError));
    }

    public void checkRequiredFields(){
        assertRegisrationPage();
        $(signUpButton).click();
        $(errorNameField).shouldBe(Condition.visible);
        $(errorLoginField).shouldBe(Condition.visible);
    }

}
