package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.PropertyLoader;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    protected WebDriver driver;
    enum WAIT {}
    @BeforeMethod
    public void initWebDriver () throws Exception {
        driver = getDriver(PropertyLoader.getKey("browser"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        if (this.driver != null){
            this.driver.quit();
            this.driver = null;
        }
    }

    private WebDriver getDriver (String driver) throws Exception {
        switch (driver){
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
            case "IE":
                return new InternetExplorerDriver();
            default:
                throw new Exception("Unsupported driver ");
        }
    }
}
