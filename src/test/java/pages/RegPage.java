package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RegPage extends BasePage {

    @FindBy(tagName = "username")
    @CacheLookup
    public WebElement usernameField;


    public RegPage(WebDriver driver) {
        super(driver);
    }
}