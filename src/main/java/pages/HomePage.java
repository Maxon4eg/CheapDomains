package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public final String PAGE_URL = "http://www.cheapdomains.com.au/index.html";

    @FindBy(id = "register_box_form")
    public WebElement regBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }


}
