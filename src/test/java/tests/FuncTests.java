package tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegPage;

public class FuncTests extends BaseTest {
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
        regPage.countrySelector().selectByIndex(15);
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
        Assert.assertTrue(regPage.alert() != null, "Alert is'nt present");
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
    public void testPersonalValidationTest() throws Exception {
        regPage.personalAcc.click();
        regPage.submit.click();
        Assert.assertFalse(regPage.alert().getText().contains(" - Business Name\n" + " - Business Number\n"), "Business number/text in Alert");

    }

    @Test
    public void testNegBusinessValidationTest() throws Exception {
        regPage.businessAcc.click();
        regPage.businessName.sendKeys("ValidName");
        regPage.businessNumber.sendKeys("123456");
        regPage.submit.click();
        Assert.assertTrue(regPage.alert().getText().contains(" - Business Name\n" + " - Business Number\n"), "Business number/text in Alert");

    }

    @Test
    public void testPosBusinessValidationTest() throws Exception {
        regPage.businessAcc.click();
        regPage.businessName.sendKeys("ValidName");
        regPage.businessNumber.sendKeys("123456");
        regPage.submit.click();
        Assert.assertFalse(regPage.alert().getText().contains(" - Business Name\n" + " - Business Number\n"), "Business number/text in Alert");

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
        Assert.assertEquals(regPage.alert().getText(), alertTxt);

    }

    @Test
    public void testRegAfterAlert() throws Exception {
        String username = "user" + (int) (Math.random() * 100);
        String pass = "pass" + (int) (Math.random() * 100);
        String alertTxt = "Please complete the following required fields:\n" +
                " - Country\n" +
                " - State\n";
        regPage.firstName.sendKeys(username);
        regPage.lastName.sendKeys(username + "last name");
        regPage.address.sendKeys("some address");
        regPage.city.sendKeys("Kyiv");
        regPage.postCode.sendKeys("some_post");
        regPage.phone.sendKeys("1234567");
        regPage.email.sendKeys("valid@mail.com");
        regPage.personalAcc.click();//set as personal account
        regPage.username.sendKeys(username);
        regPage.password.sendKeys(pass);
        regPage.submit.click();
        Assert.assertEquals(regPage.alert().getText(), alertTxt, " alert text");
        regPage.alert().accept();
        regPage.countrySelector().selectByIndex(15);
        regPage.state.sendKeys("myState");
        regPage.submit.click();
        Assert.assertEquals(regPage.getCurURL(), "http://www.cheapdomains.com.au/index.html", " redirect after succes registration");
    }

    @Test
    public void testRedBorderInputs() throws Exception {
        regPage.businessAcc.click();
        regPage.submit.click();
        regPage.alert().accept();
        Assert.assertEquals(regPage.firstName.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.lastName.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.address.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.city.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.postCode.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.country.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.state.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.businessName.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.businessNumber.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.username.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
        Assert.assertEquals(regPage.password.getCssValue("border-color"), "rgb(255, 0, 0)", "border color ");
    }

    @Test
    public void testPosMailVailidation() throws Exception {
        regPage.email.sendKeys("valid@mail.com");
        regPage.submit.click();
        Assert.assertFalse(regPage.alert().getText().contains("- Email"), " -Email Alert text");
    }

    @Test
    public void testNegMailValidation() throws Exception {
        regPage.email.sendKeys("notValidMail");
        regPage.submit.click();
        Assert.assertTrue(regPage.alert().getText().contains("- Email"), " -Email Alert text");
    }

    @Test
    public void testUsernamePasswordValidation() throws Exception{
        regPage.username.sendKeys("123");
        regPage.password.sendKeys("123");
        Assert.assertTrue(regPage.alert()!=null,"Alert presence");
        Assert.assertTrue(regPage.alert().getText().contains(" - Username\n" +" - Password")," - Username - Password text in Alert ");
    }


}
