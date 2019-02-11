package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.TimeZones;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class PersonalAccount {

    private By logoutButton = By.id("profile_signout");
    private By editPersonaldata = By.id("edit_profile");
    private By birthdayfield = By.name("66[day]");
    private By monthField = By.cssSelector("div.inline[name='select_block']");
    private By yearField = By.name("66[year]");
    private By buttonSave = By.xpath("//span[@class='btn-link btn-link-blue profile-save']//button[contains(@onclick, 'saveInfo')]");
    private By hobbiesDropDown = By.id("block_222");
    private By musicHobbyCheckbox = By.xpath("//div[contains(@data-detail-id, '276')]//img[@class='sprite input-check-icon']");
    private By checkboxMusic = By.xpath("//label[@class='input-check-label']//input[@name='276']");
    private By buttonSaveHobbies = By.xpath("//div[@id='block_222']//button[contains(@onclick, 'saveInfo')]");
    String all;
    String birthday;
    String year;


    public void assertPersonalPage(){Assert.assertTrue(title().contains("ROZETKA — Личные данные | Личный кабинет")); }

    // method for selecting of month from dropdown list
    public void selectMonth(){
        Random number = new Random();
        int dateMonth = number.nextInt(11)+1; //to not get a zero value of month, we add +1
        String month = String.valueOf(dateMonth);
        String forSelector = "[data-value='"+month+"']";
        SelenideElement setMonth= $(By.cssSelector("a.addit-f-select-l-i-link"+forSelector));
        setMonth.click();
    }

    public void clickLogout(){
        assertPersonalPage();
        $(logoutButton).shouldBe(Condition.visible);
        $(logoutButton).click();
    }

    public void generateDayAndYear(){
        Random number = new Random();
        int dateRandom = number.nextInt(30+1);
        int yearRandom = number.nextInt(98+1);
        birthday = String.valueOf(dateRandom);
        year = "19" + String.valueOf(yearRandom);

        assertPersonalPage();
        $(editPersonaldata).click();
        $(birthdayfield).setValue(birthday);
        $(monthField).click();
        selectMonth();
        $(yearField).setValue(year);
        takeBirthdayValue();
        $(buttonSave).click();
        System.out.println(all);
    }

    public void takeBirthdayValue(){
        SelenideElement day = $(birthdayfield);
        String takeDay = day.getValue();
        SelenideElement month = $(monthField);
        String takeMonth = month.getText();
        SelenideElement year = $(yearField);
        String takeYear = year.getValue();
        all =takeDay+" "+takeMonth+" "+takeYear;
    }

    public void addMusicHobby(){
        //assertPersonalPage();
        $(hobbiesDropDown).click();
        $(musicHobbyCheckbox).click();
        $(buttonSaveHobbies).click();
    }

    public void deleteMusicHobby() {
        //assertPersonalPage();
        $(editPersonaldata).click();
        $(checkboxMusic).click();
        $(hobbiesDropDown).click();
        $(checkboxMusic).isDisplayed();
    }
}
