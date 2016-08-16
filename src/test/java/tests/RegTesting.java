package tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.RegPage;

public class RegTesting extends BaseTest {

    @Test
    public void testPosRegistration() throws Exception {
        RegPage regPage = PageFactory.initElements(driver,RegPage.class);

        regPage.usernameField.sendKeys();

    }
}
