package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegPage extends BasePage {

    public final String PAGE_URL = "http://www.cheapdomains.com.au/register/member_register_test.php";

    @FindBy(name = "username_login")
    @CacheLookup
    public WebElement username_login;

    @FindBy(name = "password_login")
    @CacheLookup
    public WebElement password_login;

    @FindBy(name = "first_name")
    @CacheLookup
    public WebElement firstName;

    @FindBy(name = "last_name")
    @CacheLookup
    public WebElement lastName;

    @FindBy(name = "address")
    @CacheLookup
    public WebElement address;

    @FindBy(name = "city")
    @CacheLookup
    public WebElement city;

    @FindBy(name = "post_code")
    @CacheLookup
    public WebElement postCode;

    @FindBy(name = "country")
    @CacheLookup
    public WebElement country;

    @FindBy(name = "state")
    @CacheLookup
    public WebElement state;

    @FindBy(name = "phone")
    @CacheLookup
    public WebElement phone;

    @FindBy(name = "email")
    @CacheLookup
    public WebElement email;

    @FindBy(id = "account_type_personal")
    @CacheLookup
    public WebElement personalAcc;

    @FindBy(id = "account_type_business")
    @CacheLookup
    public WebElement businessAcc;

    @FindBy(name = "business_name")
    @CacheLookup
    public WebElement businessName;

    @FindBy(name = "business_number")
    @CacheLookup
    public WebElement businessNumber;

    //login Details
    @FindBy(name = "username")
    @CacheLookup
    public WebElement username;

    @FindBy(name = "password")
    @CacheLookup
    public WebElement password;

    @FindBy(xpath = ".//*/*[@value='Login Now']")
    @CacheLookup
    public WebElement loginBtn;

    @FindBy(xpath = ".//*/*[@value='Continue Order']")
    @CacheLookup
    public WebElement submit;


    public RegPage(WebDriver driver) {
        super(driver);
    }

    public RegPage openPage() {
        //handling "Unhandlen_Alert_Exception while using chrome

        try {
            driver.get(PAGE_URL);

        } catch (UnhandledAlertException e) {
            System.out.println(alert().getText());
            driver.switchTo().alert().accept();
        }
        return this;
    }

    public Alert alert() {
        try {
            return new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());

        } catch (TimeoutException e) {
            System.out.println("Alert is not appeared");
            return null;
        }
    }

    public Select countrySelector() { //The Select class is not work properly with last version of firefox
        return new Select(country);
    }

}