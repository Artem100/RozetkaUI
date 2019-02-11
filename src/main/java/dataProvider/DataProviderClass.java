package dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "invalidPasswords")
    public static Object[][] credentials() {
        Object[][] elementArray = new Object[][]{
                { "Test@mail.com", "Test@1", "la1L"},
                { "Test@mail.com", "Test@2", "lal1a"},
                { "Test@mail.com", "Test@3", "lalala"},
                { "Test@mail.com", "Test@mail.com", "Test@mail.com"}};
        return elementArray;
    }
}
