package tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegPage;

public class RegTesting extends BaseTest {
    RegPage regPage;

    @BeforeMethod
    public void setUp() {
        regPage = PageFactory.initElements(driver, RegPage.class);
        regPage.openPage();
    }

    @AfterMethod
    public void testTearDown() {
        regPage = null;
    }

    @Test
    public void testPosRegistration() throws Exception {
        String username = "user" + (int) (Math.random() * 100);
        String pass = "pass" + (int) (Math.random() * 100);

        regPage.firstName.sendKeys(username);
        regPage.lastName.sendKeys(username + "last name");
        regPage.address.sendKeys("some address");
        regPage.city.sendKeys("Kyiv");
        regPage.postCode.sendKeys("some_post");
        regPage.country.sendKeys("k");
        regPage.state.sendKeys("kazahkstan");
        regPage.phone.sendKeys("1234567");
        regPage.email.sendKeys("valid@mail.com");
        regPage.personalAcc.click();//set as personal account
        regPage.username.sendKeys(username);
        regPage.password.sendKeys(pass);
        regPage.submit.click();
        Assert.assertEquals(regPage.getCurURL(), "http://www.cheapdomains.com.au/index.html", " redirect after succes registration");
    }

    @Test
    public void testOnAlert() throws Exception {
        regPage.submit.click();
        Assert.assertTrue(regPage.isAlertPresent(), "Alert is'nt present");
    }

    @Test
    public void testOnBusinesContatctAppearence() throws Exception {

        if (regPage.businessAcc.isSelected()) {
            Assert.assertTrue(regPage.businessName.isDisplayed(), "visibility of the field ");
            Assert.assertTrue(regPage.businessNumber.isDisplayed(), " visibility of the field ");
        } else {
            regPage.businessAcc.click();
            Assert.assertTrue(regPage.businessName.isDisplayed(), "visibility of the field ");
            Assert.assertTrue(regPage.businessNumber.isDisplayed(), " visibility of the field ");
        }
    }

    @Test
    public void testAlertText() throws Exception {
        String alertTxt = "Please complete the following required fields:\n" +
                " - First Name\n" +
                " - Last Name\n" +
                " - Address\n" +
                " - City\n" +
                " - Country\n" +
                " - State\n" +
                " - Post Code\n" +
                " - Phone\n" +
                " - Email\n" +
                " - Business Name\n" +
                " - Business Number\n" +
                " - Username\n" +
                " - Password\n";

        regPage.submit.click();
        Assert.assertEquals(regPage.getAlertTxt(), alertTxt);

    }
}
